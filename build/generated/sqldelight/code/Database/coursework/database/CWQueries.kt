package coursework.database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface CWQueries : Transacter {
  public fun <T : Any> allBooks(mapper: (
    id: Long,
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?,
    AUTHOR_ID: Long?,
    PUBLISHER_ID: Long?
  ) -> T): Query<T>

  public fun allBooks(): Query<BOOK>

  public fun <T : Any> allAuthors(mapper: (
    id: Long,
    FIRSTNAME: String,
    SURNAME: String
  ) -> T): Query<T>

  public fun allAuthors(): Query<AUTHOR>

  public fun <T : Any> allPublisher(mapper: (id: Long, NAME: String) -> T): Query<T>

  public fun allPublisher(): Query<PUBLISHER>

  public fun <T : Any> book_by_Author(AUTHOR_ID: Long?, mapper: (
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?
  ) -> T): Query<T>

  public fun book_by_Author(AUTHOR_ID: Long?): Query<Book_by_Author>

  public fun <T : Any> book_by_Publisher(PUBLISHER_ID: Long?, mapper: (
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?
  ) -> T): Query<T>

  public fun book_by_Publisher(PUBLISHER_ID: Long?): Query<Book_by_Publisher>

  public fun <T : Any> Search_Book_by_Title(TITLE: String, mapper: (
    id: Long,
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?,
    AUTHOR_ID: Long?,
    PUBLISHER_ID: Long?
  ) -> T): Query<T>

  public fun Search_Book_by_Title(TITLE: String): Query<BOOK>

  public fun insertBook(
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?,
    AUTHOR_ID: Long?,
    PUBLISHER_ID: Long?
  ): Unit

  public fun insertAuthors(FIRSTNAME: String, SURNAME: String): Unit

  public fun insertPublisher(NAME: String): Unit

  public fun EditBookbyEntry(
    TITLE: String,
    AUTHOR: String,
    YEAR_OF_PUBLICATION: Long,
    PUBLISHER: String,
    SUBJECT: String?,
    AUTHOR_ID: Long?
  ): Unit
}
