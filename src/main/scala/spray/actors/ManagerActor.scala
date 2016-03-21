package spray.actors

import akka.actor.Actor.Receive
import akka.actor.SupervisorStrategy.{Escalate, Restart, Stop}
import akka.actor.{Actor, ActorKilledException, OneForOneStrategy}
import akka.event.LoggingReceive
import akka.pattern.CircuitBreaker

import scala.concurrent.duration._

class ManagerActor extends Actor with akka.actor.ActorLogging {

  import context.dispatcher

  val circuitBreaker = new CircuitBreaker(context.system.scheduler,
    maxFailures = 10,
    callTimeout = 100.milliseconds,
    resetTimeout = 1.seconds).
    onOpen(logCircuitBreakerOpen())

  def logCircuitBreakerOpen() = log.info("CircuitBreaker is open")


  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1.minute) {

    // case _ : DBException => Restart
    case _ : ActorKilledException => Stop
    // case _ : ServiceDownException => Escalate
  }

  override def receive: Receive = ???
}
