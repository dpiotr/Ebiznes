package models

import play.api.libs.json.Json

case class Product(
                    id: Int,
                    id_producer: Int,
                    id_category: Int,
                    id_photo: Int,
                    name: String,
                    description: String,
                    price: Int
                  )

object Product {
  implicit val productFormat = Json.format[Product]
}