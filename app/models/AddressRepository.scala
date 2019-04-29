package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AddressRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class AddressTable(tag: Tag) extends Table[Address](tag, "Address") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def id_country = column[Int]("id_country")

    def id_client = column[Int]("id_client")

    def name = column[String]("name")

    def street = column[String]("street")

    def city = column[String]("city")

    def state = column[String]("state")

    def postal_code = column[String]("postal_code")

    def * = (id, id_country, id_client, name, street, city, state, postal_code) <> ((Address.apply _).tupled, Address.unapply)
  }

  val address = TableQuery[AddressTable]

  def list(): Future[Seq[Address]] = db.run {
    address.result
  }
}
