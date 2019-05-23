package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class OrderTable(tag: Tag) extends Table[Order](tag, "Order") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def id_client = column[Int]("id_client")

    def id_address = column[Int]("id_address")

    def is_done = column[Int]("is_done")

    def * = (id, id_client, id_address, is_done) <> ((Order.apply _).tupled, Order.unapply)
  }

  val order = TableQuery[OrderTable]

  def create(id_client: Int, id_address: Int, is_done: Int): Future[Order] = db.run {
    (order.map(o => (o.id_client, o.id_address, o.is_done))
      returning order.map(_.id)
      into { case ((id_client, id_address, is_done), id) => Order(id, id_client, id_address, is_done) }
      ) += (id_client, id_address, is_done)
  }

  def list(): Future[Seq[Order]] = db.run {
    order
      .result
  }

  def getByClientId(clientId: Int): Future[Seq[Order]] = db.run {
    order
      .filter(_.id_client === clientId)
      .result
  }

  def remove(id: Int) = db.run {
    order
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, id_client: Int, id_address: Int, is_done: Int) = db.run {
    val updateOrder = Order(id, id_client, id_address, is_done)

    order
      .filter(_.id === id)
      .update(updateOrder)
  }
}