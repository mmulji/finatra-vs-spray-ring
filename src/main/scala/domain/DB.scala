package domain

import org.joda.time.DateTime

import scala.collection.mutable.HashMap

object DB {

  private val readings = HashMap.empty[DateTime, WeatherReading];

  // TODO: Add until criteria to query
  def get(request: WeatherReadingRequest) =
    if (request.from.isEmpty) readings
      else readings.filter(_._1.isAfter(request.from.getOrElse(DateTime.now())))

  def put(req: WeatherReading) = readings += (req.timestamp -> req)

}
