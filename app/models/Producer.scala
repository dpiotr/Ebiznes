package models

import play.api.libs.json.Json

case class Producer(id: Int, name: String)

object Producer {
  implicit val producerFormat = Json.format[Producer]
}
