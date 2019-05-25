package controllers

import javax.inject._
import models.CategoryRepository
import play.api.mvc._
import play.api.libs.json.{JsObject, Json}

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

  def add = Action.async { implicit request =>
    val category: JsObject = request.body.asJson.get("category").as[JsObject]

    val name = category.value("name").as[String]

    categoryRepo
      .create(name)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def remove(id: Int) = Action.async { implicit request =>
    categoryRepo
      .remove(id)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]

    categoryRepo
      .edit(id, name)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }
}