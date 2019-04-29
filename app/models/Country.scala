package models

import play.api.libs.json._

case class Country(id: Int, name: String)

object Country {
  implicit val countryFormat = Json.format[Country]
}
