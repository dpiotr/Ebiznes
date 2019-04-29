package controllers

import javax.inject._
import models.AccountRepository
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class AccountsController @Inject()(accountRepo: AccountRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def accounts = Action.async { implicit request =>
    accountRepo
      .list()
      .map { accounts =>
        Ok(Json.toJson(accounts))
      }
  }

  def add = Action {
    Ok(views.html.index("administration/accounts/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/accounts/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/accounts/edit"))
  }
}
