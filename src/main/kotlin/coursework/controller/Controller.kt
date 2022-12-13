package coursework.controller

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.database.Database
import coursework.database.AUTHOR
import coursework.database.BOOK
import coursework.database.PUBLISHER
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

object Controller {

    val path = "src/main/resources/library.sqlite"

    private val pcs = PropertyChangeSupport(this)

    var bookList: List<BOOK> = getBooks()
        get() = field
        private set(value) {
            val old = field
            field = value
            pcs.firePropertyChange("bookList", old, field)
        }

    var authorList: List<AUTHOR> = getAuthors()
        get() = field
        private set(value) {
            val old = field
            field = value
            pcs.firePropertyChange("authorList", old, field)
        }

    var publisherList: List<PUBLISHER> = getPublisher()
        get() = field
        private set(value) {
            val old = field
            field = value
            pcs.firePropertyChange("publisherList", old, field)
        }

//    var searchList: List<BOOK> = getSearchBooks(null)
//        get() = field
//        private set(value) {
//            val old = field
//            field = value
//            pcs.firePropertyChange("searchList", old, field)
//        }
    // SQLDriver will be our "bridge" for the sql queries called as functions
    private fun getSqlDriver(path: String ): SqlDriver {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:sqlite:" + path
        ds.driverClassName = "org.sqlite.JDBC"
        ds.username = ""
        ds.password = ""
        return ds.asJdbcDriver()
    }

    private fun getBooks(): List<BOOK> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }

//    fun getSearchBooks(word: String?): List<BOOK> {
//        val database = Database(getSqlDriver(path))
//        val sqlQueries = database.cWQueries
//        return sqlQueries.Search_Book_by_Title(TITLE = "%$word%").executeAsList()
//    }

    fun addBook(title: String, author: String, year_of_publication: Long, publisher: String,
                subject: String, author_id: Long?=null, publisher_id: Long?=null)
    {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertBook(title, author, year_of_publication, publisher, subject, author_id, publisher_id)
        bookList = getBooks()
    }

    fun searchBooks(word: String?)
    {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        bookList = sqlQueries.Search_Book_by_Title(TITLE = "%$word%").executeAsList()
    }

    fun editBooks(title: String, author: String, year_of_publication: Long, publisher: String,
                  subject: String, id: Long)
    {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.EditBookbyEntry(title, author, year_of_publication, publisher, subject, id)
        bookList = getBooks()
    }
//
    fun deleteBooks(id: Long)
    {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.DeleteBookByID(id)
        bookList = getBooks()
    }

    private fun getAuthors(): List<AUTHOR> {
        val database = Database(getSqlDriver(path))
        val authorQueries = database.cWQueries
        return authorQueries.allAuthors().executeAsList()
    }

    fun addAuthor(firstname: String, surname: String) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertAuthors(firstname, surname)
        authorList = getAuthors()
    }

    fun getPublisher(): List<PUBLISHER> {
        val database = Database(getSqlDriver(path))
        val publisherQueries = database.cWQueries
        return publisherQueries.allPublisher().executeAsList()
    }

    fun addPublisher(name: String) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertPublisher(name)
        publisherList = getPublisher()
    }

    fun addPropertyChangeListener(pcl: PropertyChangeListener?) {
        pcs.addPropertyChangeListener(pcl)
    }

    fun removePropertyChangeListener(pcl: PropertyChangeListener?) {
        pcs.removePropertyChangeListener(pcl)
    }


}

//fun main() {
//////    Controller.bookList.forEach { it ->
//////        println(it)
//////    }
//////    Controller.add_book("Percy Jackson","Rick Riordan",2011,"Mondadori","Fantasy", null, null)
//////    Controller.bookList.forEach { it ->
//////        println(it)
//////    }
//    Controller.getSearchBooks("Prague")
//    Controller.searchList.forEach {
//        println(it)
//    }
//}