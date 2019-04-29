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

  def list(): Future[Seq[Order]] = db.run {
    order.result
  }
}
