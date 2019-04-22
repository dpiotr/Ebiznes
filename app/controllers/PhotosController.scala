package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class PhotosController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def photos = Action {
    Ok(views.html.index("administration/photos"))
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
