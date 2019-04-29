package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class ProductTable(tag: Tag) extends Table[Product](tag, "Product") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def id_producer = column[Int]("id_producer")

    def id_category = column[Int]("id_category")

    def id_photo = column[Int]("id_photo")

    def name = column[String]("name")

    def description = column[String]("description")

    def price = column[Double]("price")

    def * = (id, id_producer, id_category, id_photo, name, description, price) <> ((Product.apply _).tupled, Product.unapply)
  }

  val product = TableQuery[ProductTable]

  def list(): Future[Seq[Product]] = db.run {
    product.result
  }
}
