package coursework.IO


// This is the "Menu that should Display the information regarding Interpreter Commands for the Model (DDBB) testing of data"
object Utilities {

    fun prompt_Int(message:String="DDBB>> "): Int {
        print(message)
        return readLine()!!.toInt() // Kind of assert.
    }

    fun prompt_String(message:String="DDBB>> "): String {
        print(message)
        return readLine()!! // Kind of assert.
    }

    fun help() {
        val banner = listOf<String>(
            "0 - Exit",
            "1 - Show books",
            "2 - Show authors",
            "3 - Add Author",
            "4 - Add Book",
            "5 - Add Publisher",
            "6 - Sort Book [Year_Of_Publication]",   // Needs to be double-checked.
            "7 - Sort Book by Author ",
            "10 - Help"
        )
        for (i in banner) {
            println(i)
        }
    }

}