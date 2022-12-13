
package coursework.sorting

import coursework.database.BOOK
import kotlin.math.max

object Radix_kt {


    private fun findMaxLengthTitle(arr: ArrayList<BOOK>) : Int {

        var maxLen = 0

        for(i in arr.indices){
            if(arr[i].TITLE.filter { it.isLetter() }.length > maxLen) {
                maxLen = arr[i].AUTHOR.length
            }
        }
        return maxLen
    }private fun findMaxLengthAuthor(arr: ArrayList<BOOK>) : Int {

        var maxLen = 0

        for(i in arr.indices){
            if(arr[i].AUTHOR.filter { it.isLetter() }.length > maxLen) {
                maxLen = arr[i].AUTHOR.length
            }
        }
        return maxLen
    }

    fun sortByAuthor(arr: ArrayList<BOOK>) : Int {
        var ticks = 1;

        val buckets : MutableList<ArrayList<BOOK>> = mutableListOf()


        var maxLen = findMaxLengthAuthor(arr)
        var place = 1

        while(place < maxLen){

            for(i in 0 until 28){
                buckets.add( java.util.ArrayList())
            }

            for(book in arr){

                val author = book.TITLE.filter { it.isLetter() }

                if(author.length > place){


                    buckets[author[author.length-place].uppercaseChar().code -65].add(book)
                }else{
                    buckets[author[0].uppercaseChar().code-65].add(book)
                }
                ticks++
            }
            arr.clear()
            println(buckets)
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

        while(place < maxLen){

            for(i in 0 until 28){
                buckets.add( java.util.ArrayList())
            }

            for(book in arr){

                val author = book.TITLE.filter { it.isLetter() }

                if(author.length > place){

                    println(
                        author[author.length-place].uppercaseChar())
                    buckets[author[author.length-place].uppercaseChar().code -65].add(book)
                }else{
                    buckets[author[0].uppercaseChar().code-65].add(book)
                }
                ticks++
            }
            arr.clear()
            println(buckets)
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
}
