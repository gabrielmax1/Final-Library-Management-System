package coursework.model

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.controller.Controller
import coursework.database.*
import coursework.database.Database
import coursework.database.AUTHOR
import coursework.database.BOOK
import coursework.database.PUBLISHER


object DDBB {


    val path = "src/main/resources/library.sqlite"

    fun booksByAuthor( id: Long): List<Book_by_Author> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.book_by_Author(id).executeAsList()
    }
//    fun FindBookbyTitle(word: String): List<Search_Book_by_Title> {
//        val database = Database(getSqlDriver(path))
//        val searchQueries = database.cWQueries
//        return searchQueries.Search_Book_by_Title(word).executeAsList()
//    }

    fun getBooks(): List<BOOK> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }

    fun getSearchBooks(word: String): List<BOOK> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.Search_Book_by_Title(TITLE = word).executeAsList()
    }

    fun addBook(title: String, author: String, year_of_publication: Long, publisher: String, subject: String, author_id: Long?=null, publisher_id: Long?=null) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertBook(title, author, year_of_publication, publisher, subject, author_id, publisher_id)
    }


    private fun getSqlDriver(path: String ): SqlDriver {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:sqlite:" + path
        ds.driverClassName = "org.sqlite.JDBC"
        ds.username = ""
        ds.password = ""
        return ds.asJdbcDriver()
    }

    fun addAuthor(firstname: String, surname: String) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertAuthors(firstname, surname)
    }

    fun getAuthors(): List<AUTHOR> {
        val database = Database(getSqlDriver(path))
        val authorsQueries = database.cWQueries
        return authorsQueries.allAuthors().executeAsList()
    }

    fun getPublishers(): List<PUBLISHER> {
        val database = Database(getSqlDriver(path))
        val publishersQueries = database.cWQueries
        return publishersQueries.allPublisher().executeAsList()
    }

    fun addPublisher(name: String) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertPublisher(name)
    }
}


// this is for testing.
fun main() {
    val books = DDBB.getBooks()
    for (book in books) {
        println(book)
    }
    println(books)

    val authors = DDBB.getAuthors()
    for (author in authors) {
        println(author)

    }
}

