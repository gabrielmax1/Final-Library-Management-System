
package coursework.sorting

import coursework.database.BOOK
import kotlin.math.max

object Radix_kt {


    private fun findMaxLength(arr: ArrayList<BOOK>) : Int {

        var maxLen = 0

        for(i in arr.indices){
            if(arr[i].AUTHOR.filter { it.isLetter() }.length > maxLen) {
                maxLen = arr[i].AUTHOR.length
            }
        }
        return maxLen
    }

    fun sortByAuthor(warr: ArrayList<BOOK>) : Int {
        var ticks = 1;

        val buckets : MutableList<ArrayList<BOOK>> = mutableListOf()


        var maxLen = findMaxLength(warr)
        var place = 1

        while(place < maxLen){

            for(i in 0 until 28){
                buckets.add( java.util.ArrayList())
            }

            for(book in warr){

                val author = book.AUTHOR.filter { it.isLetter() }

                if(author.length > place){

                    println(
                        author[author.length-place].uppercaseChar())
                    buckets[author[author.length-place].uppercaseChar().code -65].add(book)
                }else{
                    buckets[author[0].uppercaseChar().code-65].add(book)
                }
            }
            warr.clear()
            println(buckets)
            for(list in buckets){
                if(list.isNotEmpty()){
                    warr.addAll(list)
                }
            }

            buckets.clear()
            place++
        }




        return ticks
    }

}
