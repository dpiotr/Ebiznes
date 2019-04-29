package controllers

import javax.inject._
import models._
import play.api.mvc._
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext

@Singleton
class CountriesController @Inject()(countryRepo: CountryRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def countries = Action.async { implicit request =>
    countryRepo
      .list()
      .map { countries =>
        Ok(Json.toJson(countries))
      }
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
