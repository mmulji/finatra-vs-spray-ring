package domain

import com.twitter.finatra.request.QueryParam
import org.joda.time.DateTime


// Sample json packets from http://dweet.io/

/*

http://dweet.io/follow/tense-tree:

  {
    "mouse_x": 384,
    "mouse_y": 610,
    "tilt_x": 0,
    "tilt_y": 0,
    "tilt_z": 0,
    "your_latitude": 51.004932,
    "your_longitude": 5.849955
  }

 **/

case class DweetMouseCoords(mouseX: Int, mouseY: Int,
  tiltX: Int, tiltY: Int, tiltZ: Int,
  lat: Option[Double], long: Option[Double])


/*

http://dweet.io/follow/weather_station_gg:

{
  "timestamp" : "20121001-12:32:45"
  "temperature": 30.07,
  "mintemp": 24.34,
  "maxtemp": 30.5,
  "humidity": 43,
  "pressure": 758,
  "light": 93,
  "your_latitude": 51.004932,
  "your_longitude": 5.849955
}

 **/

case class WeatherReading(
  timestamp: DateTime,
  deviceId: String,
  temp: Double, minTemp: Double, maxTemp: Double,
  humidity: Int,
  pressure: Int,
  light: Int,
  latitude: Option[Double], longitude: Option[Double])


/*

  {"from" :  "", "until" : ""}

 */
case class WeatherReadingRequest(@QueryParam from: Option[DateTime], @QueryParam until: Option[DateTime])

