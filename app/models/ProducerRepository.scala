package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProducerRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class ProducerTable(tag: Tag) extends Table[Producer](tag, "Producer") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def * = (id, name) <> ((Producer.apply _).tupled, Producer.unapply)
  }

  val producer = TableQuery[ProducerTable]

  def create(name: String): Future[Producer] = db.run {
    (producer.map(p => (p.name))
      returning producer.map(_.id)
      into { case ((name), id) => Producer(id, name) }
      ) += (name)
  }

  def list(): Future[Seq[Producer]] = db.run {
    producer.result
  }

  def remove(id: Int) = db.run {
    producer
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, name: String) = db.run {
    val updateProducer = Producer(id, name)

    producer
      .filter(_.id === id)
      .update(updateProducer)
  }
}