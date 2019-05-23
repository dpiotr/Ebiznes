package controllers

import javax.inject._
import models.ProducerRepository
import play.api.mvc._
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext

@Singleton
class ProducersController @Inject()(producerRepo: ProducerRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def producers = Action.async { implicit request =>
    producerRepo
      .list()
      .map { producers =>
        Ok(Json.toJson(producers))
      }
  }

  def add = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]

    producerRepo
      .create(name)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def remove(id: Int) = Action.async { implicit request =>
    producerRepo
      .remove(id)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]

    producerRepo
      .edit(id, name)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }
}
