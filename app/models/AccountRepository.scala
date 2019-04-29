package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AccountRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class AdministrationTable(tag: Tag) extends Table[Account](tag, "Administration") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def login = column[String]("login")

    def password = column[String]("password")

    def * = (id, login, password) <> ((Account.apply _).tupled, Account.unapply)
  }

  val account = TableQuery[AdministrationTable]

  def list(): Future[Seq[Account]] = db.run {
    account.result
  }
}
