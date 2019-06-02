package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PhotoRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class PhotoTable(tag: Tag) extends Table[Photo](tag, "Photo") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("data")

    def * = (id, name) <> ((Photo.apply _).tupled, Photo.unapply)
  }

  val photo = TableQuery[PhotoTable]

  def create(name: String): Future[Photo] = db.run {
    (photo.map(p => (p.name))
      returning photo.map(_.id)
      into { case ((name), id) => Photo(id, name) }
      ) += (name)
  }

  def list(): Future[Seq[Photo]] = db.run {
    photo
      .result
  }

  def remove(id: Int) = db.run {
    photo
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, name: String) = db.run {
    val updatePhoto = Photo(id, name)

    photo
      .filter(_.id === id)
      .update(updatePhoto)
  }
}