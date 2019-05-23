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

  def create(id_country: Int, id_client: Int, name: String, street: String, city: String, state: String, postal_code: String): Future[Address] = db.run {
    (address.map(a => (a.id_country, a.id_client, a.name, a.street, a.city, a.state, a.postal_code))
      returning address.map(_.id)
      into { case ((id_country, id_client, name, street, city, state, postal_code), id) => Address(id, id_country, id_client, name, street, city, state, postal_code)
    }
      ) += (id_country, id_client, name, street, city, state, postal_code)
  }

  def list(): Future[Seq[Address]] = db.run {
    address
      .result
  }

  def getByClientId(id: Int): Future[Seq[Address]] = db.run {
    address
      .filter(_.id_client === id)
      .result
  }

  def remove(id: Int) = db.run {
    address
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, id_country: Int, id_client: Int, name: String, street: String, city: String, state: String, postal_code: String) = db.run {
    val updateAddress = Address(id, id_country, id_client, name, street, city, state, postal_code)

    address
      .filter(_.id === id)
      .update(updateAddress)
  }
}