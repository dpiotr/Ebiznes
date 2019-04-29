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

  def add = Action {
    Ok(views.html.index("administration/producers/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/producers/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/producers/edit"))
  }
}
