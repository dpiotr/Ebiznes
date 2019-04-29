package controllers

import javax.inject._
import models.{CategoryRepository, ClientRepository, OrderRepository}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ClientController @Inject()(clientRepo: ClientRepository, orderRepo: OrderRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def clients = Action.async { implicit request =>
    clientRepo
      .list()
      .map { clients =>
        Ok(Json.toJson(clients))
      }
  }

  def client = Action.async { implicit request =>
    clientRepo //Filter client
      .list()
      .map { clients =>
        Ok(Json.toJson(clients))
      }
  }

  def edit = Action {
    Ok(views.html.index("client/edit"))
  }

  def addresses = Action {
    Ok(views.html.index("client/addresses"))
  }

  def addressAdd = Action {
    Ok(views.html.index("client/address/add"))
  }

  def addressRemove = Action {
    Ok(views.html.index("client/address/remove"))
  }

  def addressEdit = Action {
    Ok(views.html.index("client/address/remove"))
  }

  def orders = Action.async { implicit request =>
    orderRepo
      .list()
      .map { orders =>
        Ok(Json.toJson(orders))
      }
  }
}