package controllers

import javax.inject._
import models._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext}

@Singleton
class AccountsController @Inject()(accountRepo: AccountRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def accounts = Action.async { implicit request =>
    accountRepo
      .list()
      .map { accounts =>
        Ok(Json.toJson(accounts))
      }
  }

  def add = Action.async { implicit request =>
    val login = request.body.asJson.get("login").as[String]
    val password = request.body.asJson.get("password").as[String]

    accountRepo
      .create(login, password)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def remove(id: Int) = Action.async { implicit request =>
    accountRepo
      .remove(id)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val login = request.body.asJson.get("login").as[String]
    val password = request.body.asJson.get("password").as[String]

    accountRepo
      .edit(id, login, password)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }
}