package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ClientRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class ClientTable(tag: Tag) extends Table[Client](tag, "Client") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def surname = column[String]("surname")

    def email = column[String]("email")

    def telephone = column[String]("telephone")

    def * = (id, name, surname, email, telephone) <> ((Client.apply _).tupled, Client.unapply)
  }

  val client = TableQuery[ClientTable]

  def create(name: String, surname: String, email: String, telephone: String): Future[Client] = db.run {
    (client.map(c => (c.name, c.surname, c.email, c.telephone))
      returning client.map(_.id)
      into { case ((name, surname, email, telephone), id) => Client(id, name, surname, email, telephone) }
      ) += (name, surname, email, telephone)
  }

  def list(): Future[Seq[Client]] = db.run {
    client
      .result
  }

  def remove(id: Int) = db.run {
    client
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, name: String, surname: String, email: String, telephone: String) = db.run {
    val updateClient = Client(id, name, surname, email, telephone)

    client
      .filter(_.id === id)
      .update(updateClient)
  }
}