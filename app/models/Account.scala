package models

import play.api.libs.json.Json

case class Account(id: Int, login: String, password: String)

object Account {
  implicit val accountFormat = Json.format[Account]
}