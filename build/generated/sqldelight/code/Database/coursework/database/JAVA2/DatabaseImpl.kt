package coursework.database.JAVA2

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import coursework.database.AUTHOR
import coursework.database.BOOK
import coursework.database.Book_by_Author
import coursework.database.Book_by_Publisher
import coursework.database.CWQueries
import coursework.database.Database
import coursework.database.PUBLISHER
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<Database>.schema: SqlDriver.Schema
  get() = DatabaseImpl.Schema

internal fun KClass<Database>.newInstance(driver: SqlDriver): Database = DatabaseImpl(driver)

private class DatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), Database {
  public override val cWQueries: CWQueriesImpl = CWQueriesImpl(this, driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE BOOK (
          | id INTEGER PRIMARY KEY AUTOINCREMENT,
          |TITLE TEXT NOT NULL,
          |AUTHOR TEXT NOT NULL,
          |YEAR_OF_PUBLICATION INTEGER NOT NULL,
          |PUBLISHER TEXT NOT NULL,
          |SUBJECT TEXT,
          |AUTHOR_ID INTEGER,
          |PUBLISHER_ID INTEGER,
          |FOREIGN KEY(AUTHOR_ID) REFERENCES AUTHOR(id),
          |FOREIGN KEY(PUBLISHER_ID) REFERENCES PUBLISHER(id)
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE AUTHOR (
          | id INTEGER PRIMARY KEY AUTOINCREMENT,
          | FIRSTNAME TEXT NOT NULL,
          | SURNAME TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE PUBLISHER (
          | id INTEGER PRIMARY KEY AUTOINCREMENT,
          | NAME TEXT NOT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class CWQueriesImpl(
  private val database: DatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), CWQueries {
  internal val allBooks: MutableList<Query<*>> = copyOnWriteList()

  internal val allAuthors: MutableList<Query<*>> = copyOnWriteList()

  internal val allPublisher: MutableList<Query<*>> = copyOnWriteList()

  internal val book_by_Author: MutableList<Query<*>> = copyOnWriteList()

  internal val book_by_Publisher: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> allBooks(mapper: (
    id: Long,
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?,
    AUTHOR_ID: Long?,
    PUBLISHER_ID: Long?
  ) -> T): Query<T> = Query(44674632, allBooks, driver, "CW.sq", "allBooks", """
  |SELECT *
  |FROM BOOK
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5),
      cursor.getLong(6),
      cursor.getLong(7)
    )
  }

  public override fun allBooks(): Query<BOOK> = allBooks { id, TITLE, AUTHOR, YEAR_OF_PUBLICATION,
      PUBLISHER, SUBJECT, AUTHOR_ID, PUBLISHER_ID ->
    BOOK(
      id,
      TITLE,
      AUTHOR,
      YEAR_OF_PUBLICATION,
      PUBLISHER,
      SUBJECT,
      AUTHOR_ID,
      PUBLISHER_ID
    )
  }

  public override fun <T : Any> allAuthors(mapper: (
    id: Long,
    FIRSTNAME: String,
    SURNAME: String
  ) -> T): Query<T> = Query(-728552346, allAuthors, driver, "CW.sq", "allAuthors", """
  |SELECT *
  |FROM AUTHOR
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!
    )
  }

  public override fun allAuthors(): Query<AUTHOR> = allAuthors { id, FIRSTNAME, SURNAME ->
    AUTHOR(
      id,
      FIRSTNAME,
      SURNAME
    )
  }

  public override fun <T : Any> allPublisher(mapper: (id: Long, NAME: String) -> T): Query<T> =
      Query(-87347558, allPublisher, driver, "CW.sq", "allPublisher", """
  |SELECT *
  |FROM PUBLISHER
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!
    )
  }

  public override fun allPublisher(): Query<PUBLISHER> = allPublisher { id, NAME ->
    PUBLISHER(
      id,
      NAME
    )
  }

  public override fun <T : Any> book_by_Author(AUTHOR_ID: Long?, mapper: (
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?
  ) -> T): Query<T> = Book_by_AuthorQuery(AUTHOR_ID) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)
    )
  }

  public override fun book_by_Author(AUTHOR_ID: Long?): Query<Book_by_Author> =
      book_by_Author(AUTHOR_ID) { TITLE, AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, SUBJECT ->
    Book_by_Author(
      TITLE,
      AUTHOR,
      YEAR_OF_PUBLICATION,
      PUBLISHER,
      SUBJECT
    )
  }

  public override fun <T : Any> book_by_Publisher(PUBLISHER_ID: Long?, mapper: (
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?
  ) -> T): Query<T> = Book_by_PublisherQuery(PUBLISHER_ID) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)
    )
  }

  public override fun book_by_Publisher(PUBLISHER_ID: Long?): Query<Book_by_Publisher> =
      book_by_Publisher(PUBLISHER_ID) { TITLE, AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, SUBJECT ->
    Book_by_Publisher(
      TITLE,
      AUTHOR,
      YEAR_OF_PUBLICATION,
      PUBLISHER,
      SUBJECT
    )
  }

  public override fun insertBook(
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?,
    AUTHOR_ID: Long?,
    PUBLISHER_ID: Long?
  ): Unit {
    driver.execute(-507489727,
        """INSERT INTO BOOK ( TITLE, AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, SUBJECT, AUTHOR_ID, PUBLISHER_ID) VALUES ( ?, ?, ?, ?,?, ?, ?)""",
        7) {
      bindString(1, TITLE)
      bindString(2, AUTHOR)
      bindLong(3, YEAR_OF_PUBLICATION)
      bindString(4, PUBLISHER)
      bindString(5, SUBJECT)
      bindLong(6, AUTHOR_ID)
      bindLong(7, PUBLISHER_ID)
    }
    notifyQueries(-507489727, {database.cWQueries.book_by_Author + database.cWQueries.allBooks +
        database.cWQueries.book_by_Publisher})
  }

  public override fun insertAuthors(FIRSTNAME: String, SURNAME: String): Unit {
    driver.execute(-1052665360, """INSERT INTO AUTHOR (FIRSTNAME, SURNAME) VALUES (?,?)""", 2) {
      bindString(1, FIRSTNAME)
      bindString(2, SURNAME)
    }
    notifyQueries(-1052665360, {database.cWQueries.book_by_Author + database.cWQueries.allAuthors})
  }

  public override fun insertPublisher(NAME: String): Unit {
    driver.execute(1972658596, """INSERT INTO PUBLISHER (NAME) VALUES (?)""", 1) {
      bindString(1, NAME)
    }
    notifyQueries(1972658596, {database.cWQueries.allPublisher +
        database.cWQueries.book_by_Publisher})
  }

  private inner class Book_by_AuthorQuery<out T : Any>(
    public val AUTHOR_ID: Long?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(book_by_Author, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null, """
    |SELECT
    |    BOOK.TITLE,
    |    BOOK.AUTHOR,
    |    BOOK.YEAR_OF_PUBLICATION,
    |    BOOK.PUBLISHER,
    |    BOOK.SUBJECT
    |FROM BOOK
    |INNER JOIN AUTHOR
    |ON BOOK.AUTHOR_ID = AUTHOR.id
    |WHERE BOOK.AUTHOR_ID ${ if (AUTHOR_ID == null) "IS" else "=" } ?
    """.trimMargin(), 1) {
      bindLong(1, AUTHOR_ID)
    }

    public override fun toString(): String = "CW.sq:book_by_Author"
  }

  private inner class Book_by_PublisherQuery<out T : Any>(
    public val PUBLISHER_ID: Long?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(book_by_Publisher, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null, """
    |SELECT
    |    BOOK.TITLE,
    |        BOOK.AUTHOR,
    |        BOOK.YEAR_OF_PUBLICATION,
    |        BOOK.PUBLISHER,
    |        BOOK.SUBJECT
    |    FROM BOOK
    |    INNER JOIN PUBLISHER
    |    ON BOOK.PUBLISHER_ID = PUBLISHER.id
    |    WHERE BOOK.PUBLISHER_ID ${ if (PUBLISHER_ID == null) "IS" else "=" } ?
    """.trimMargin(), 1) {
      bindLong(1, PUBLISHER_ID)
    }

    public override fun toString(): String = "CW.sq:book_by_Publisher"
  }
}
