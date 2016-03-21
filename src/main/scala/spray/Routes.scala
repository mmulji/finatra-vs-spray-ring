package spray

import scala.concurrent.duration._
import akka.util.Timeout
import domain.{DB, WeatherReading, WeatherReadingRequest}
import org.json4s._
import spray.httpx.Json4sSupport
import spray.routing._
import spray.http.StatusCodes._


// Used to mix in Spray's Marshalling Support with json4s
object Json4sProtocol extends Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats
}

// Our case class, used for request and responses
case class Foo(bar: String)

// Our route directives, the heart of the service.
// Note you can mix-in dependencies should you so chose
trait IoTService extends HttpService {

  import Json4sProtocol._
  import spray.actors.WorkerActor

  //These implicit values allow us to use futures
  //in this trait.
  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout = Timeout(5 seconds)

  // Our worker Actor handles the work of the request.
  // val worker = actorRefFactory.actorOf(Props[WorkerActor], "worker")

  val routes = {
    path("env") {

      get { entity(as[WeatherReadingRequest]) { req =>
        complete { DB.get(req)} }
      } ~
      post { respondWithStatus(Created) {
        entity(as[WeatherReading]) { reading =>
          complete { DB.put(reading) }
        }
      }
    }
  }}

  def doCreate[T](foo: Foo) = {
    complete {
      //We use the Ask pattern to return
      //a future from our worker Actor,
      //which then gets passed to the complete
      //directive to finish the request.

      "OK"

      //      (worker ? Create(foo))
      //        .mapTo[Ok]
      //        .map(result => s"I got a response: ${result}")
      //        .recover { case _ => "error" }
    }
  }

}


//val routes = {
//  path("entity") {
//  get {
//  complete(List(Foo("foo1"), Foo("foo2")))
//} ~
//  post {
//  respondWithStatus(Created) {
//  entity(as[Foo]) { someObject =>
//  doCreate(someObject)
//}
//}
//}
//} ~
//  path ("entity" / Segment) { id =>
//  get {
//  complete(s"detail ${id}")
//} ~
//  post {
//  complete(s"update ${id}")
//}
//} ~
//  path("stats") {
//  complete {
//  //This is another way to use the Akka ask pattern
//  //with Spray.
//  actorRefFactory.actorSelection("/user/IO-HTTP/listener-0")
//  .ask(Http.GetStats)(1.second)
//  .mapTo[Stats]
//}
//}
//}
