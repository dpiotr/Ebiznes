package controllers

import javax.inject._
import models.PhotoRepository
import play.api.mvc._
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext

@Singleton
class PhotosController @Inject()(photoRepo: PhotoRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def photos = Action.async { implicit request =>
    photoRepo
      .list()
      .map { photos =>
        Ok(Json.toJson(photos))
      }
  }

  def add = Action {
    Ok(views.html.index("administration/photos/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/photos/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/photos/edit"))
  }
}
