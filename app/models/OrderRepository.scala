package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderRepository @Inject()(val dbConfigProvider: DatabaseConfigProvider, val clientRepository: ClientRepository,
                                val addressRepository: AddressRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class OrderTable(tag: Tag) extends Table[Order](tag, "Order") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def id_client = column[Int]("id_client")

    def id_address = column[Int]("id_address")

    def date = column[String]("date")

    def is_done = column[Int]("is_done")

    def fk_client = foreignKey("fk_client", id_client, clientTable)(_.id)

    def fk_address = foreignKey("fk_address", id_address, addressTable)(_.id)

    def * = (id, id_client, id_address, date, is_done) <> ((Order.apply _).tupled, Order.unapply)
  }

  import clientRepository.ClientTable
  import addressRepository.AddressTable

  val clientTable = TableQuery[ClientTable]
  val addressTable = TableQuery[AddressTable]

  val order = TableQuery[OrderTable]

  def create(id_client: Int, id_address: Int, date: String, is_done: Int): Future[Order] = db.run {
    (order.map(o => (o.id_client, o.id_address, o.date, o.is_done))
      returning order.map(_.id)
      into { case ((id_client, id_address, date, is_done), id) => Order(id, id_client, id_address, date, is_done) }
      ) += (id_client, id_address, date, is_done)
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

  def edit(id: Int, id_client: Int, id_address: Int, date: String, is_done: Int) = db.run {
    val updateOrder = Order(id, id_client, id_address, date, is_done)

    order
      .filter(_.id === id)
      .update(updateOrder)
  }
}