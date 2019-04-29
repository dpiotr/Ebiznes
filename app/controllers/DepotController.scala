package controllers

import javax.inject._
import models.DepotRepository
import play.api.libs.json.Json
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

  def add = Action {
    Ok(views.html.index("administration/depot/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/depot/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/depot/edit"))
  }
}
