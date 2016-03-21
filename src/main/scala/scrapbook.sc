import domain.{DB, Utils, WeatherReading, WeatherReadingRequest}
import org.joda.time.DateTime

//timestamp: DateTime,
//temp: Double, minTemp: Double, maxTemp: Double,
//humidity: Int,
//pressure: Int,
//light: Int,
//lat: Option[Double], long: Option[Double])

println("Creating weather readings ...")
val wr1 = WeatherReading(DateTime.now(), Utils.uuid, -1.0, -1.0, 2.0, 10, 20, 30, Option(100.0), Option(200.0))
val wr2 = WeatherReading(DateTime.now(), Utils.uuid, -1.0, -1.0, 2.0, 10, 20, 30, Option(400.0), Option(300.0))

println("Adding to database ...")
DB.put(wr1)
DB.put(wr2)

println("Creating request ...")
val from = WeatherReadingRequest(Option(DateTime.now().minusDays(2)), None)

println("Extracting from database ...")
val items = DB.get(from)
println(s"Items retreived: ${items.size}")

