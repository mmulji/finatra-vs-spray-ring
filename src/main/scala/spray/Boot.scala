package spray

import scala.concurrent.duration._

import akka.actor.{ Actor, ActorLogging, ActorSystem, Props }
import akka.io.IO
import org.json4s.native.Serialization.{ read, write }
import spray.can.Http


object Boot extends App {

  implicit val system = ActorSystem("iot-server")

  val appInterface = system.settings.config.getString("app.interface")
  val appPort = system.settings.config.getInt("app.port")

  // Use Akka to create our Spray Service
  val service = system.actorOf(Props[IoTActor], "iot-server")

  // and bind to Akka's I/O interface
  IO(Http) ! Http.Bind(service, appInterface, appPort)

}

// Our Server Actor is pretty lightweight; simply mixing in our route trait and logging
class IoTActor extends Actor with IoTService with ActorLogging {
  def actorRefFactory = context
  def receive = runRoute(routes)
}
