package coursework.database

import kotlin.Long
import kotlin.String

public data class Book_by_Author(
  public val TITLE: String,
  public val AUTHOR: String,
  public val YEAR_OF_PUBLICATION: Long,
  public val PUBLISHER: String,
  public val SUBJECT: String?
) {
  public override fun toString(): String = """
  |Book_by_Author [
  |  TITLE: $TITLE
  |  AUTHOR: $AUTHOR
  |  YEAR_OF_PUBLICATION: $YEAR_OF_PUBLICATION
  |  PUBLISHER: $PUBLISHER
  |  SUBJECT: $SUBJECT
  |]
  """.trimMargin()
}
