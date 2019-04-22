package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class CategoriesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def categories = Action {
    Ok(views.html.index("administration/categories"))
  }

  def add = Action {
    Ok(views.html.index("administration/categories/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/categories/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/categories/edit"))
  }
}
