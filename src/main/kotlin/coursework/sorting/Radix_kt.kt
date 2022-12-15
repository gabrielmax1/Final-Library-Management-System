
package coursework.sorting

import coursework.database.BOOK
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max


//Singleton object used to sort book entries, implements both iterative and recursive implementations.

object Radix_kt {


    // Private function used to find the maximum length in TITLE or AUTHOR depending on value of entry variable
    // entry: AUTHOR or TITLE, chose the array to work onn
    private fun findMaxLengthEntry(arr: ArrayList<BOOK>, entry: String) : Int {

        var maxLen = 0
        var len : Int
        for(i in arr.indices){

            //Decide what list to use; entry is sanitized in order to lose special characters
            if (entry.uppercase(Locale.getDefault()) == "TITLE" ) {
                len = arr[i].TITLE.filter { it.isLetter() }.length
            }else{
                len = arr[i].AUTHOR.filter { it.isLetter() }.length
            }

            if(len > maxLen) {
                maxLen = len
            }
        }
        return maxLen
    }

    //Iterative implementation of Radix sort for list of Authors
    //arr: ArrayList<BOOK> array to be sorted
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //returns the number of ticks
    fun sortIterative(arr: ArrayList<BOOK>, byField: String) : Int {
        var ticks = 1;

        val buckets : MutableList<ArrayList<BOOK>> = mutableListOf()
        val maxLen = findMaxLengthEntry(arr, byField)
        var place = 1

        val re = Regex("[^A-Za-z]")
        var entry : String

        while(place < maxLen){

            //Bucket initialization
            for(i in 0 until 28){
                buckets.add( java.util.ArrayList())
            }

            for(book in arr){

                //Chose entry to sort
                if (byField.uppercase(Locale.getDefault()) == "AUTHOR"){
                    //Clean string of non-Alphabetical characters
                    entry = re.replace(book.AUTHOR, "")
                }
                else{
                    //Clean string of non-Alphabetical characters
                    entry = re.replace(book.TITLE, "")
                }

                //Add entry to bucket
                if(entry.length > place){
                    buckets[entry[entry.length-place].uppercaseChar().code -65].add(book)
                }else{
                    buckets[entry[0].uppercaseChar().code-65].add(book)
                }
                ticks++
            }

            //Recreate list with new order
            arr.clear()
            for(list in buckets){
                if(list.isNotEmpty()){
                    arr.addAll(list)
                }
            }
            //Clean buckets
            buckets.clear()
            place++

        }




        return ticks
    }


    //Recursive implementation of Radix sort for Array<B00K>
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //place: The position of characters to be sorted
    //returns the number of ticks
    fun sortRecursive(arr: ArrayList<BOOK>, byEntry: String, place:Int = 1, ticks:Int = 0): Int {

        val maxLen = findMaxLengthEntry(arr, byEntry)

        if (place >= maxLen ) {
            return ticks
        }

        val buckets: MutableList<ArrayList<BOOK>> = mutableListOf()
        for (i in 0 until 28) {
            buckets.add(java.util.ArrayList())
        }

        val re = Regex("[^A-Za-z]")
        var entry : String
        var tick = 0
        for (book in arr) {

            //Chose entry to sort
            if (byEntry.uppercase(Locale.getDefault()) == "AUTHOR"){
                //Clean string of non-Alphabetical characters
                entry = re.replace(book.AUTHOR, "")
            }
            else{
                //Clean string of non-Alphabetical characters
                entry = re.replace(book.TITLE, "")
            }

            if (entry.length > place) {
                buckets[entry[entry.length - place].uppercaseChar().code - 65].add(book)
            } else {
                buckets[entry[0].uppercaseChar().code - 65].add(book)
            }


        }
        tick++

        arr.clear()
        for (list in buckets) {
            if (list.isNotEmpty()) {
                arr.addAll(list)
            }
        }

        return tick + sortRecursive(arr, byEntry, place + 1, 1)
    }


}

