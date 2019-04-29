package controllers

import javax.inject._
import models.OrderRepository
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class OrderController @Inject()(orderRepo: OrderRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def order = Action {
    Ok(views.html.index("order"))
  }
}