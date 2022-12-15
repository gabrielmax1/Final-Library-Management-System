package coursework.sorting

import coursework.database.BOOK
import java.util.*
import kotlin.collections.ArrayList


//Singleton object used to sort book entries, only recursive method is implemented

object MergeSort {

    var tick = 0


    //Main method for the Merge sort
    fun sort(unsortList: ArrayList<BOOK>, byEntry: String, implementation: String): Pair<ArrayList<BOOK>, Int> {
        tick = 0
        val res = ArrayList(unsortList)

        if (unsortList.size <= 1)
            return Pair(res, tick)
        else {
            val half = unsortList.size / 2
            val leftScrambled = ArrayList(unsortList.slice(0 until half))
            val rightScrambled = ArrayList(unsortList.slice(half until unsortList.size))

            val (leftSorted, _) = sort(leftScrambled, byEntry, implementation)
            val (rightSorted, _) = sort(rightScrambled, byEntry, implementation)

            if(implementation.uppercase(Locale.getDefault()) == "RECURSIVE") {
                merge_recursive(leftSorted, rightSorted, res, byEntry)
                return Pair(res, tick)
            }else{

                var pair : Pair<java.util.ArrayList<BOOK>, Int> = merge_iterative(leftSorted, rightSorted, res, byEntry)
                return Pair(pair.first, pair.second)
            }


        }
    }

    //Method used for the actual merging of arrays using recurssion
    //leftSorter: ArrayList<BOOK> sorted array to be merged
    //rightSorted: ArrayList<BOOK> sorted array to be merged
    //res: ArrayList<BOOk> result array
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //returns Pair<ArrayList<B00K>, int> : sorted list and number of ticks
    private fun merge_recursive(leftSorted: ArrayList<BOOK>,
                    rightSorted: ArrayList<BOOK>,
                    res: ArrayList<BOOK>,
                    byEntry: String){

        // l - index of left array
        // r - index of left array
        // res - result array
        // byEntry - field to sort by
        fun merge_rec_author(
            leftSorted: ArrayList<BOOK>, l: Int,
            rightSorted: ArrayList<BOOK>, r: Int,
            res: ArrayList<BOOK>, t: Int,
            byEntry: String
        ) {

            var leftName : String
            var rightName : String
            if (!((l < leftSorted.size) || (r < rightSorted.size))) {
                assert((l == leftSorted.size) && (r == rightSorted.size))
            } else {
                assert(t < res.size)
                tick += 1
                if (l == leftSorted.size) {
                    res[t] = rightSorted[r]
                    merge_rec_author(leftSorted, l, rightSorted, r + 1, res, t + 1, byEntry)
                } else if (r == rightSorted.size) {
                    res[t] = leftSorted[l]
                    merge_rec_author(leftSorted, l + 1, rightSorted, r, res, t + 1, byEntry)
                } else {

                    if(byEntry.uppercase(Locale.getDefault()) == "AUTHOR") {
                        leftName = leftSorted[l].AUTHOR
                        rightName = rightSorted[r].AUTHOR
                    }
                    else{
                        leftName = leftSorted[l].TITLE
                        rightName = rightSorted[r].TITLE
                    }
                    if (leftName < rightName) {
                        res[t] = leftSorted[l]
                        merge_rec_author(leftSorted, l + 1, rightSorted, r, res, t + 1, byEntry)
                    } else {
                        res[t] = rightSorted[r]
                        merge_rec_author(leftSorted, l, rightSorted, r + 1, res, t + 1, byEntry)
                    }
                }
            }
        }

//        // Inmmersion call.
        var (l, r, t) = Triple(0, 0, 0)
        merge_rec_author(leftSorted, l, rightSorted, r, res, t, byEntry)  // not B
    }


    //Iterative methods used for the acutal merging of arrays
    //leftSorted : ArrayList<BOOK> sorted array to be merged
    //rightSorted : ArrayList<BOOk> sortd array to be merged
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //returns Pair<ArrayList<B00K>, int> : sorted list and number of ticks
    private fun merge_iterative(leftSorted: ArrayList<BOOK>,
                                rightSorted: ArrayList<BOOK>,
                                res: ArrayList<BOOK>,
                                byEntry: String): Pair<ArrayList<BOOK>, Int>{

        var l = 0
        var r = 0
        var k = 0

        var ticks = 0

        var entryLeft : String
        var entryRight: String
//        print(leftSorted + " " + rightSorted)
        while(l < leftSorted.size  && r < rightSorted.size ){

            //Choose entry to sort by
            if (byEntry.uppercase(Locale.getDefault()) == "AUTHOR"){
                entryLeft = leftSorted[l].AUTHOR
                entryRight = rightSorted[r].AUTHOR
            }
            else
            {
                entryLeft = leftSorted[l].TITLE
                entryRight = rightSorted[r].TITLE
            }

            if(entryLeft < entryRight) {

                res[k] = leftSorted[l]
                l++
            }else{
                res[k] = rightSorted[r]
                r++
            }
            k++
            ticks++
        }

        while(l < leftSorted.size){

            res[k] = leftSorted[l]
            k++
            l++
            ticks++
        }

        while(r < rightSorted.size){

            res[k] = rightSorted[r]
            r++
            ticks++
        }
        return Pair((ArrayList(res)), ticks)
    }

}





