package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class AccountsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def accounts = Action {
    Ok(views.html.index("administration/accounts"))
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
