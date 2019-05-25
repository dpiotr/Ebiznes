package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DepotRepository @Inject()(val dbConfigProvider: DatabaseConfigProvider, val productRepository: ProductRepository)
                               (implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class DepotTable(tag: Tag) extends Table[Depot](tag, "Depot") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def id_product = column[Int]("id_product")

    def quantity = column[Int]("quantity")

    def fk_product = foreignKey("fk_product", id_product, productTable)(_.id)

    def * = (id, id_product, quantity) <> ((Depot.apply _).tupled, Depot.unapply)
  }

  import productRepository.ProductTable

  val productTable = TableQuery[ProductTable]

  val depot = TableQuery[DepotTable]

  def create(id_product: Int, quantity: Int): Future[Depot] = db.run {
    (depot.map(d => (d.id_product, d.quantity))
      returning depot.map(_.id)
      into { case ((id_product, quantity), id) => Depot(id, id_product, quantity) }
      ) += (id_product, quantity)
  }

  def list(): Future[Seq[Depot]] = db.run {
    depot.result
  }

  def remove(id: Int) = db.run {
    depot
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, id_product: Int, quantity: Int) = db.run {
    val updateDepot = Depot(id, id_product, quantity)

    depot
      .filter(_.id === id)
      .update(updateDepot)
  }
}