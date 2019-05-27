package controllers

import javax.inject._
import models.ProductRepository
import play.api.libs.json.{JsObject, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext}

@Singleton
class ProductsController @Inject()(productRepo: ProductRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def products = Action.async { implicit request =>
    productRepo
      .list()
      .map { products =>
        Ok(Json.toJson(products))
      }
  }

  def product(id: Int) = Action.async { implicit request =>
    productRepo
      .getById(id)
      .map { products =>
        Ok(Json.toJson(products))
      }
  }

  def add = Action.async { implicit request =>
    val product: JsObject = request.body.asJson.get("product").as[JsObject]

    Console.println(product)
    val id_producer = product.value("id_producer").as[Int]
    val id_category = product.value("id_category").as[Int]
    val id_photo = product.value("id_photo").as[Int]
    val name = product.value("name").as[String]
    val description = product.value("description").as[String]
    val price = product.value("price").as[Int]

    productRepo
      .create(id_producer, id_category, id_photo, name, description, price)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def remove(id: Int) = Action.async { implicit request =>
    productRepo
      .remove(id)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val id_producer = request.body.asJson.get("id_producer").as[Int]
    val id_category = request.body.asJson.get("id_category").as[Int]
    val id_photo = request.body.asJson.get("id_photo").as[Int]
    val name = request.body.asJson.get("name").as[String]
    val description = request.body.asJson.get("description").as[String]
    val price = request.body.asJson.get("price").as[Int]

    productRepo
      .edit(id, id_producer, id_category, id_photo, name, description, price)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }
}