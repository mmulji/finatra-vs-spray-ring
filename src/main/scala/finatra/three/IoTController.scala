package finatra.three

import javax.inject.Inject
import com.twitter.finatra.http.Controller
import domain.{DB, WeatherReading, WeatherReadingRequest}

class IoTController @Inject()() extends Controller {

  get("/env") { request: WeatherReadingRequest =>
    println(request)
    DB.get(request)
  }

  post("/env") { request: WeatherReading =>
    println(request)
    "bar"
  }
}
