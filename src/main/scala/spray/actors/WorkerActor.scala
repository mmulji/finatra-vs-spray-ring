package spray.actors

import akka.actor.Actor.Receive
import akka.actor.SupervisorStrategy.{Escalate, Restart, Stop}
import akka.actor.{Actor, ActorKilledException, OneForOneStrategy, Props}
import akka.event.LoggingReceive

import scala.concurrent.duration._


object WorkerActor {

  def props(counter: Int): Props = Props(new WorkerActor)

}

class WorkerActor extends Actor with akka.actor.ActorLogging {

  override def receive: Receive = ???

}
