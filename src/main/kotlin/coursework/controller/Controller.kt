package coursework.controller

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.database.Database
import coursework.database.AUTHOR
import coursework.database.BOOK
import coursework.database.PUBLISHER
import coursework.model.DDBB
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

 // Model–view–controller (MVC) is a software architectural pattern that separates an application into three main logical components: the model / the view / and the controller.
 // The model represents the data and the business logic of the application,
 // the view represents the user interface, and the controller manages the flow of data between the model and the view.
 // This allows for a clear separation of responsibilities, which can make the development and maintenance of an application easier.
 // For example, the view and the controller can be changed without affecting the model, and the model can be developed and tested independently of the view and the controller.
object Controller {

    val path = "src/main/resources/library.sqlite"

    private val pcs = PropertyChangeSupport(this)

    // ============== LISTS ===============
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
    // ============ Book Controller ==============================
    private fun getBooks(): List<BOOK> { // Called in the List, then List is get in Panel
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }
    fun addBook(title: String, author: String, year_of_publication: Long, publisher: String,
                subject: String, author_id: Long?=null, publisher_id: Long?=null) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertBook(title, author, year_of_publication, publisher, subject, author_id, publisher_id)
        bookList = getBooks()
    }

//     fun add_book(title: String, author: String, year_of_publication: Long, publisher: String, subject: String,
//                  author_id: Long?=null, publisher_id: Long?=null) {
//         bookList = DDBB.addBook(title, author, year_of_publication, publisher, subject, author_id, publisher_id).executeAsList()
//         return bookList
//     }

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

    fun deleteBooks(id: Long)
    {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.DeleteBookByID(id)
        bookList = getBooks()
    }


    // ============ Author Controller ==============================
    private fun getAuthors(): List<AUTHOR> { // Called in the List, then List is get in Panel
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

    fun searchAuthor(name: String?, surname: String?){
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        authorList = sqlQueries.Search_Author_by_Name(FIRSTNAME= "%$name%", SURNAME= "%$surname%").executeAsList() // Only FIRSTNAME for now
    }

    fun editAuthor(firstname: String, surname: String, id: Long)
    {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.EditAuthorEntry(firstname, surname, id)
        authorList = getAuthors()
    }

    fun deleteAuthor(id: Long)
    {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.DeleteAuthorByID(id)
        authorList = getAuthors()
    }

    // ============ Publisher Controller ==============================
    private fun getPublisher(): List<PUBLISHER> { // Called in the List, then List is get in Panel
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

    fun searchPublisher(name: String?) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        publisherList = sqlQueries.Search_Publisher_by_Name(NAME = "%$name%").executeAsList()
    }

    fun editPublisher(name: String, id: Long) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.EditPublisherEntry(name, id)
        publisherList = getPublisher()
    }

    fun deletePublisher(id: Long) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.DeletePublisherByID(id)
        publisherList = getPublisher()
    }


    // ======================== Property Change listener =======================
    fun addPropertyChangeListener(pcl: PropertyChangeListener?) {
        pcs.addPropertyChangeListener(pcl)
    }

    fun removePropertyChangeListener(pcl: PropertyChangeListener?) {
        pcs.removePropertyChangeListener(pcl)
    }


}

fun main() {
    Controller.bookList.forEach { it ->
        println(it)
    }
    Controller.addBook("Percy Jackson","Rick Riordan",2011,"Mondadori","Fantasy", null, null)
    Controller.bookList.forEach { it ->
        println(it)
    }
    Controller.searchBooks("Prague")
//    Controller.searchList.forEach {
//        println(it)
//    }
}