package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class ProducersController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def producers = Action {
    Ok(views.html.index("administration/producers"))
  }

  def add = Action {
    Ok(views.html.index("administration/producers/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/producers/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/producers/edit"))
  }
}
