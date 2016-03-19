import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import javax.inject.Inject
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.Controller
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter

class ExampleController @Inject()() extends Controller {

  get("/ping") { request: Request =>
    "pong"
  }

  get("/name") { request: Request =>
    response.ok.body("Bob")
  }

  post("/foo") { request: Request =>
    "bar"
  }
}


object ExampleServerMain extends ExampleServer

class ExampleServer extends HttpServer {

  override val modules = Seq()

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[ExampleController]
  }
}