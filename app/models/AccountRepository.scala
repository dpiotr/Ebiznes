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

  class AccountsTable(tag: Tag) extends Table[Account](tag, "Accounts") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def login = column[String]("login")

    def password = column[String]("password")

    def * = (id, login, password) <> ((Account.apply _).tupled, Account.unapply)
  }

  val account = TableQuery[AccountsTable]

  def create(login: String, password: String): Future[Account] = db.run {
    (account.map(a => (a.login, a.password))
      returning account.map(_.id)
      into { case ((login, password), id) => Account(id, login, password) }
      ) += (login, password)
  }

  def list(): Future[Seq[Account]] = db.run {
    account
      .result
  }

  def remove(id: Int) = db.run {
    account
      .filter(_.id === id)
      .delete
  }

  def edit(id: Int, login: String, password: String) = db.run {
    val updateAccount = Account(id, login, password)

    account
      .filter(_.id === id)
      .update(updateAccount)
  }
}