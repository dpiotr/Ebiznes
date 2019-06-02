package models

import play.api.libs.json.Json

case class Photo(id: Int, data: String)

object Photo {
  implicit val photoFormat = Json.format[Photo]
}