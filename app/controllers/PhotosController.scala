package controllers

import javax.inject._
import models.PhotoRepository
import play.api.mvc._
import play.api.libs.json.{JsObject, Json}

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

  def add = Action.async { implicit request =>
    val photo: JsObject = request.body.asJson.get("photo").as[JsObject]

    val name = photo.value("name").as[String]

    photoRepo
      .create(name)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def remove(id: Int) = Action.async { implicit request =>
    photoRepo
      .remove(id)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]

    photoRepo
      .edit(id, name)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }
}