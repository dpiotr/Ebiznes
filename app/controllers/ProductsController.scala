package controllers

import javax.inject._
import models.ProductRepository
import play.api.libs.json.Json
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class ProductsController @Inject()(productRepo: ProductRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  val productForm: Form[CreateProductForm] = Form {
    mapping(
      "id_producer" -> number,
      "id_category" -> number,
      "id_photo" -> number,
      "name" -> nonEmptyText,
      "description" -> nonEmptyText,
      "price" -> number,
    )(CreateProductForm.apply)(CreateProductForm.unapply)
  }

  def products = Action.async { implicit request =>
    productRepo
      .list()
      .map { products =>
        Ok(Json.toJson(products))
      }
  }

  def product(id: Int) = Action.async { implicit request =>
    productRepo
      .getById(id)
      .map { products =>
        Ok(Json.toJson(products))
      }
  }

  def add = Action.async { implicit request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.

    productForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the person creation function returns a future.
      errorForm => {
        Future.successful(
          //          for (n <- errorForm.errors) {
          //            // imagine this requires several lines
          //            Console.println(n)
          //          }
          Ok(views.html.index(errorForm.errors.toString()))
        )
      },
      // There were no errors in the from, so create the person.
      product => {
        productRepo.create(product.id_producer, product.id_category, product.id_photo, product.name, product.description, product.price).map { _ =>
          // If successful, we simply redirect to the index page.
          Redirect(routes.ProductsController.products)
        }
      }
    )
  }

  def remove = Action {
    Ok(views.html.index("administration/products/remove"))
  }

  def edit = Action {
    Ok(views.html.index("administration/products/edit"))
  }

  def handlePost = Action.async { implicit request =>
    val id_producer = request.body.asJson.get("id_producer").as[Int]
    val id_category = request.body.asJson.get("id_category").as[Int]
    val id_photo = request.body.asJson.get("id_photo").as[Int]
    val name = request.body.asJson.get("name").as[String]
    val description = request.body.asJson.get("description").as[String]
    val price = request.body.asJson.get("price").as[Int]

    productRepo
      .create(id_producer, id_category, id_photo, name, description, price)
      .map { product =>
        Ok(Json.toJson(product))
      }
  }

  case class CreateProductForm(id_producer: Int, id_category: Int, id_photo: Int, name: String, description: String, price: Int)
}