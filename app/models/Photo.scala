package models

import play.api.libs.json.Json

case class Photo(id: Int, name: String)

object Photo {
  implicit val photoFormat = Json.format[Photo]
}