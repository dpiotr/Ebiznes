package controllers

import javax.inject._
import models.DepotRepository
import play.api.libs.json.{JsObject, Json}
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class DepotController @Inject()(depotRepo: DepotRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def depot = Action.async { implicit request =>
    depotRepo
      .list()
      .map { depot =>
        Ok(Json.toJson(depot))
      }
  }

  def add = Action.async { implicit request =>
    val depot: JsObject = request.body.asJson.get("depot").as[JsObject]

    val id_product = depot.value("id_product").as[Int]
    val quantity = depot.value("quantity").as[Int]

    depotRepo
      .create(id_product, quantity)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def remove(id: Int) = Action.async { implicit request =>
    depotRepo
      .remove(id)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val id_product = request.body.asJson.get("id_product").as[Int]
    val quantity = request.body.asJson.get("quantity").as[Int]

    depotRepo
      .edit(id, id_product, quantity)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }
}