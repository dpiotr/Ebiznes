package controllers

import javax.inject._
import models.ProductRepository
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ProductsController @Inject()(productRepo: ProductRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def products = Action.async { implicit request =>
    productRepo
      .list()
      .map { products =>
        Ok(Json.toJson(products))
      }
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
