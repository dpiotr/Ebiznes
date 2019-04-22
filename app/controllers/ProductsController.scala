package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class ProductsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def products = Action {
    Ok(views.html.index("administration/products"))
  }

  def add = Action {
    Ok(views.html.index("administration/products/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/products/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/products/edit"))
  }
}
