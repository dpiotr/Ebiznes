package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class CountriesController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def countries = Action {
    Ok(views.html.index("administration/countries"))
  }

  def add = Action {
    Ok(views.html.index("administration/countries/add"))
  }

  def remove = Action {
    Ok(views.html.index("administration/countries/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/countries/edit"))
  }
}
