package controllers

import javax.inject._
import models.{AddressRepository, CategoryRepository, ClientRepository, OrderRepository}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ClientController @Inject()(clientRepo: ClientRepository, addressRepo: AddressRepository, orderRepo: OrderRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def clients = Action.async { implicit request =>
    clientRepo
      .list()
      .map { clients =>
        Ok(Json.toJson(clients))
      }
  }

  def client(id: Int) = Action.async { implicit request =>
    clientRepo
      .getById(id)
      .map { client =>
        Ok(Json.toJson(client))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]
    val surname = request.body.asJson.get("surname").as[String]
    val email = request.body.asJson.get("email").as[String]
    val telephone = request.body.asJson.get("telephone").as[String]

    clientRepo
      .edit(id, name, surname, email, telephone)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def addresses(id: Int) = Action.async { implicit request =>
    addressRepo
      .getByClientId(id)
      .map { addresses =>
        Ok(Json.toJson(addresses))
      }
  }

  def addressAdd(id: Int) = Action.async { implicit request =>
    val id_country = request.body.asJson.get("id_country").as[Int]
    val name = request.body.asJson.get("name").as[String]
    val street = request.body.asJson.get("street").as[String]
    val city = request.body.asJson.get("city").as[String]
    val state = request.body.asJson.get("state").as[String]
    val postal_code = request.body.asJson.get("postal_code").as[String]

    addressRepo
      .create(id_country, id, name, street, city, state, postal_code)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def addressRemove(id: Int, addressId: Int) = Action.async { implicit request =>
    addressRepo
      .remove(addressId)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def addressEdit(id: Int, addressId: Int) = Action.async { implicit request =>
    val id_country = request.body.asJson.get("id_country").as[Int]
    val name = request.body.asJson.get("name").as[String]
    val street = request.body.asJson.get("street").as[String]
    val city = request.body.asJson.get("city").as[String]
    val state = request.body.asJson.get("state").as[String]
    val postal_code = request.body.asJson.get("postal_code").as[String]

    addressRepo
      .edit(addressId, id_country, id, name, street, city, state, postal_code)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def orders(id: Int) = Action.async { implicit request =>
    orderRepo
      .getByClientId(id)
      .map { orders =>
        Ok(Json.toJson(orders))
      }
  }
}