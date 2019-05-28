package models

import play.api.libs.json.Json

case class Order(
                  id: Int,
                  id_client: Int,
                  id_address: Int,
                  date: String,
                  is_done: Int
                )

object Order {
  implicit val orderFormat = Json.format[Order]
}