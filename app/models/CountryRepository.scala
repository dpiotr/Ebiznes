package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{Future, ExecutionContext}

@Singleton
class CountryRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CountryTable(tag: Tag) extends Table[Country](tag, "Country") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def * = (id, name) <> ((Country.apply _).tupled, Country.unapply)
  }

  val country = TableQuery[CountryTable]

  def create(name: String): Future[Country] = db.run {
    (country.map(c => (c.name))
      returning country.map(_.id)
      into { case ((name), id) => Country(id, name) }
      ) += (name)
  }

  def list(): Future[Seq[Country]] = db.run {
    country.result
  }

  def remove(id: Int) = db.run {
    country
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, name: String) = db.run {
    val updateCountry = Country(id, name)

    country
      .filter(_.id === id)
      .update(updateCountry)
  }
}
