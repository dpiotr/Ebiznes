package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DepotRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class DepotTable(tag: Tag) extends Table[Depot](tag, "Depot") {
    def id_product = column[Int]("id_product", O.PrimaryKey)

    def quantity = column[Int]("quantity")

    def * = (id_product, quantity) <> ((Depot.apply _).tupled, Depot.unapply)
  }

  val depot = TableQuery[DepotTable]

  def list(): Future[Seq[Depot]] = db.run {
    depot.result
  }
}
