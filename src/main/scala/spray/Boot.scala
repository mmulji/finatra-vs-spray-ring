package spray

import scala.concurrent.duration._

import akka.actor.{ Actor, ActorLogging, ActorSystem, Props }
import akka.io.IO
import org.json4s.native.Serialization.{ read, write }
import spray.can.Http


object Boot extends App {
  implicit val system = ActorSystem("iot-server")

  // Use Akka to create our Spray Service
  val service = system.actorOf(Props[SpraySampleActor], "iot-server")

  // and bind to Akka's I/O interface
  IO(Http) ! Http.Bind(service,
    system.settings.config.getString("app.interface"),
    system.settings.config.getInt("app.port"))

}

// Our Server Actor is pretty lightweight; simply mixing in our route trait and logging
class SpraySampleActor extends Actor with SpraySampleService with ActorLogging {
  def actorRefFactory = context
  def receive = runRoute(routes)
}
