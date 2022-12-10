package coursework.database

import kotlin.Long
import kotlin.String

public data class BOOK(
  public val id: Long,
  public val TITLE: String,
  public val AUTHOR: String,
  public val YEAR_OF_PUBLICATION: Long,
  public val PUBLISHER: String,
  public val SUBJECT: String?,
  public val AUTHOR_ID: Long?,
  public val PUBLISHER_ID: Long?
) {
  public override fun toString(): String = """
  |BOOK [
  |  id: $id
  |  TITLE: $TITLE
  |  AUTHOR: $AUTHOR
  |  YEAR_OF_PUBLICATION: $YEAR_OF_PUBLICATION
  |  PUBLISHER: $PUBLISHER
  |  SUBJECT: $SUBJECT
  |  AUTHOR_ID: $AUTHOR_ID
  |  PUBLISHER_ID: $PUBLISHER_ID
  |]
  """.trimMargin()
}
