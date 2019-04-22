package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class ClientController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def client = Action {
    Ok(views.html.index("client"))
  }

  def edit = Action {
    Ok(views.html.index("client/edit"))
  }

  def addresses = Action {
    Ok(views.html.index("client/addresses"))
  }

  def addressAdd = Action {
    Ok(views.html.index("client/address/add"))
  }

  def addressRemove = Action {
    Ok(views.html.index("client/address/remove"))
  }

  def addressEdit = Action {
    Ok(views.html.index("client/address/remove"))
  }

  def orders = Action {
    Ok(views.html.index("client/orders"))
  }
}