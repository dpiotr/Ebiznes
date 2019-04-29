package models

import play.api.libs.json.Json

case class Address(
                    id: Int,
                    id_country: Int,
                    id_client: Int,
                    name: String,
                    street: String,
                    city: String,
                    state: String,
                    postal_code: String
                  )

object Address {
  implicit val addressFormat = Json.format[Address]
}