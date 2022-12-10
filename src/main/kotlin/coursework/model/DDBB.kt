package coursework.model

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.controller.Controller
import coursework.database.*


object DDBB {


    val path = "src/main/resources/library.sqlite"

    fun booksByAuthor( id: Long): List<Book_by_Author> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.book_by_Author(id).executeAsList()
    }


    fun getBooks(): List<BOOK> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }

    fun add_book(title: String, author: String, year_of_publication: Long, publisher: String, subject: String, author_id: Long?=null, publisher_id: Long?=null) {
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
//fun main() {
//    val lectures = DDBB.getLecturers()
//    for (lecture in lectures) {
//        println(lecture)
//    }
//    println(lectures)
//
//    val faculties = DDBB.getFaculties()
//    for (faculty in faculties) {
//        println(faculty)
//
//    }
//}
