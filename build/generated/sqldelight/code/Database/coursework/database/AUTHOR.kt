package coursework.database

import kotlin.Long
import kotlin.String

public data class AUTHOR(
  public val id: Long,
  public val FIRSTNAME: String,
  public val SURNAME: String
) {
  public override fun toString(): String = """
  |AUTHOR [
  |  id: $id
  |  FIRSTNAME: $FIRSTNAME
  |  SURNAME: $SURNAME
  |]
  """.trimMargin()
}
