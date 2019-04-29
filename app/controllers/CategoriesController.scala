package controllers

import javax.inject._
import models.CategoryRepository
import play.api.mvc._
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext

@Singleton
class CategoriesController @Inject()(categoryRepo: CategoryRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def categories = Action.async { implicit request =>
    categoryRepo
      .list()
      .map { categories =>
        Ok(Json.toJson(categories))
      }
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
