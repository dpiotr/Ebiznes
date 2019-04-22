package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class AuthController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login = Action {
    Ok(views.html.index("auth/login"))
  }

  def register = Action {
    Ok(views.html.index("auth/register"))
  }
}
