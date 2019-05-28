package controllers

import javax.inject._
import models.OrderRepository
import play.api.libs.json.{JsObject, Json}
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class OrderController @Inject()(orderRepo: OrderRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def order = Action.async { implicit request =>
    val order: JsObject = request.body.asJson.get("order").as[JsObject]

    val id_client = order.value("id_client").as[Int]
    val id_address = order.value("id_address").as[Int]
    val date = order.value("date").as[String]
    val is_done = order.value("is_done").as[Int]

    orderRepo
      .create(id_client, id_address, date, is_done)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }
}