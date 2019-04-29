package models

import play.api.libs.json.Json

case class Depot(id_product: Int, quantity: Int)

object Depot {
  implicit val depotFormat = Json.format[Depot]
}