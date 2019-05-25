package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductRepository @Inject()(val dbConfigProvider: DatabaseConfigProvider, val producerRepository: ProducerRepository,
                                  val categoryRepository: CategoryRepository, val photoRepository: PhotoRepository)
                                 (implicit ec: ExecutionContext) {
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

    def price = column[Int]("price")

    def fk_producer = foreignKey("fk_producer", id_producer, producerTable)(_.id)

    def fk_category = foreignKey("fk_category", id_category, categoryTable)(_.id)

    def fk_photo = foreignKey("fk_photo", id_photo, photoTable)(_.id)

    def * = (id, id_producer, id_category, id_photo, name, description, price) <> ((Product.apply _).tupled, Product.unapply)
  }

  import producerRepository.ProducerTable
  import categoryRepository.CategoryTable
  import photoRepository.PhotoTable

  val producerTable = TableQuery[ProducerTable]
  val categoryTable = TableQuery[CategoryTable]
  val photoTable = TableQuery[PhotoTable]

  val product = TableQuery[ProductTable]

  def create(id_producer: Int, id_category: Int, id_photo: Int, name: String, description: String, price: Double): Future[Product] = db.run {
    (product.map(p => (p.id_producer, p.id_category, p.id_photo, p.name, p.description, p.price))
      returning product.map(_.id)
      into { case ((id_producer, id_category, id_photo, name, description, price), id) => Product(id, id_producer, id_category, id_photo, name, description, price) }
      ) += (id_producer, id_category, id_photo, name, description, price.toInt)
  }

  def list(): Future[Seq[Product]] = db.run {
    product
      .result
  }

  def getById(id: Int): Future[Seq[Product]] = db.run {
    product
      .filter(_.id === id)
      .result
  }

  def remove(id: Int) = db.run {
    product
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, id_producer: Int, id_category: Int, id_photo: Int, name: String, description: String, price: Int) = db.run {
    val updateProduct = Product(id, id_producer, id_category, id_photo, name, description, price)

    product
      .filter(_.id === id)
      .update(updateProduct)
  }
}