package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class DepotController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def depot = Action {
    Ok(views.html.index("administration/depot"))
  }

  def add = Action {
    Ok(views.html.index("administration/depot/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/depot/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/depot/edit"))
  }
}
