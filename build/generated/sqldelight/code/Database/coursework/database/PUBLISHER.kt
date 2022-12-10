package coursework.database

import kotlin.Long
import kotlin.String

public data class PUBLISHER(
  public val id: Long,
  public val NAME: String
) {
  public override fun toString(): String = """
  |PUBLISHER [
  |  id: $id
  |  NAME: $NAME
  |]
  """.trimMargin()
}
