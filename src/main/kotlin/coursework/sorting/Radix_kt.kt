
package coursework.sorting

import coursework.database.BOOK
import kotlin.math.max

object Radix_kt {


    private fun findMaxLengthTitle(arr: ArrayList<BOOK>) : Int {

        var maxLen = 0

        for(i in arr.indices){
            if(arr[i].TITLE.filter { it.isLetter() }.length > maxLen) {
                maxLen = arr[i].TITLE.filter { it.isLetter() }.length
            }
        }
        return maxLen
    }

    private fun findMaxLengthAuthor(arr: ArrayList<BOOK>) : Int {

        var maxLen = 0

        for(i in arr.indices){
            if(arr[i].AUTHOR.filter { it.isLetter() }.length > maxLen) {
                maxLen = arr[i].AUTHOR.filter { it.isLetter() }.length
            }
        }
        return maxLen
    }

    fun sortByAuthor(arr: ArrayList<BOOK>) : Int {
        var ticks = 1;

        val buckets : MutableList<ArrayList<BOOK>> = mutableListOf()


        val maxLen = findMaxLengthAuthor(arr)
        var place = 1

        val re = Regex("[^A-Za-z]")

        while(place < maxLen){

            for(i in 0 until 28){
                buckets.add( java.util.ArrayList())
            }

            for(book in arr){

                val author = re.replace(book.AUTHOR, "")

                if(author.length > place){

                    buckets[author[author.length-place].uppercaseChar().code -65].add(book)
                }else{
                    buckets[author[0].uppercaseChar().code-65].add(book)
                }
                ticks++
            }
            arr.clear()
            for(list in buckets){
                if(list.isNotEmpty()){
                    arr.addAll(list)
                }
            }

            buckets.clear()
            place++

        }




        return ticks
    }

    fun sortByTitle(arr: ArrayList<BOOK>): Int {
        var ticks = 1;

        val buckets : MutableList<ArrayList<BOOK>> = mutableListOf()


        var maxLen = findMaxLengthTitle(arr)
        var place = 1
        val re = Regex("[^A-Za-z]")

        while(place < maxLen){

            for(i in 0 until 28){
                buckets.add( java.util.ArrayList())
            }

            for(book in arr){

                val author = re.replace(book.TITLE, "")

                if(author.length > place){
                    buckets[author[author.length-place].uppercaseChar().code -65].add(book)
                }else{
                    buckets[author[0].uppercaseChar().code-65].add(book)
                }
                ticks++
            }
            arr.clear()
            for(list in buckets){
                if(list.isNotEmpty()){
                    arr.addAll(list)
                }
            }

            buckets.clear()
            place++

        }




        return ticks
    }



    fun sortRecursiveAuthor(arr: ArrayList<BOOK>, place:Int = 1, ticks:Int = 0): Int {

        val maxLen = findMaxLengthAuthor(arr)

        if (place >= maxLen ) {
            return ticks
        }

        val buckets: MutableList<ArrayList<BOOK>> = mutableListOf()
        for (i in 0 until 28) {
            buckets.add(java.util.ArrayList())
        }

        val re = Regex("[^A-Za-z]")


        for (book in arr) {
            val author = re.replace(book.AUTHOR, "")

            if (author.length > place) {
               buckets[author[author.length - place].uppercaseChar().code - 65].add(book)
            } else {
               buckets[author[0].uppercaseChar().code - 65].add(book)
            }
        }


        arr.clear()
        for (list in buckets) {
            if (list.isNotEmpty()) {
                arr.addAll(list)
            }
        }
        println(arr)

        return ticks + sortRecursiveAuthor(arr, place + 1, ticks + 1)
    }


    fun sortRecursiveTitle(arr: ArrayList<BOOK>, place:Int = 1, ticks:Int = 0): Int {

        val maxLen = findMaxLengthTitle(arr)

        if (place >= maxLen ) {
            return ticks
        }

        val buckets: MutableList<ArrayList<BOOK>> = mutableListOf()
        for (i in 0 until 28) {
            buckets.add(java.util.ArrayList())
        }

        val re = Regex("[^A-Za-z]")


        for (book in arr) {
            val title = re.replace(book.TITLE, "")

            if (title.length > place) {
                buckets[title[title.length - place].uppercaseChar().code - 65].add(book)
            } else {
                buckets[title[0].uppercaseChar().code - 65].add(book)
            }
        }

        arr.clear()
        for (list in buckets) {
            if (list.isNotEmpty()) {
                arr.addAll(list)
            }
        }
        println(arr)

        return ticks + sortRecursiveTitle(arr, place + 1, ticks + 1)
    }


}

