package models

import play.api.libs.json.Json

case class Client(
                   id: Int,
                   name: String,
                   surname: String,
                   email: String,
                   telephone: String
                 )

object Client {
  implicit val clientFormat = Json.format[Client]
}