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

  def add = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]

    countryRepo
      .create(name)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  def remove(id: Int) = Action.async { implicit request =>
    countryRepo
      .remove(id)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }

  def edit(id: Int) = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]

    countryRepo
      .edit(id, name)
      .map { value =>
        Ok(Json.toJson(value))
      }
  }
}
