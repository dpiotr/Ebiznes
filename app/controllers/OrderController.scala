package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class OrderController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def order = Action {
    Ok(views.html.index("order"))
  }
}
