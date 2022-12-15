package coursework.sorting

import coursework.database.BOOK
import java.util.*
import kotlin.collections.ArrayList


//Singleton object used to sort book entries, only recursive method is implemented

object MergeSort {

    var tick = 0


    //Main method for the Merge sort
    fun sort(unsortList: ArrayList<BOOK>, byEntry: String): Pair<ArrayList<BOOK>, Int> {
        tick = 0
        val res = ArrayList(unsortList)

        if (unsortList.size <= 1)
            return Pair(res, tick)
        else {
            val half = unsortList.size / 2
            val leftScrambled = ArrayList(unsortList.slice(0 until half))
            val rightScrambled = ArrayList(unsortList.slice(half until unsortList.size))

            val (leftSorted, _) = sort(leftScrambled, byEntry)
            val (rightSorted, _) = sort(rightScrambled, byEntry)

            merge_rec_1(leftSorted, rightSorted, res, byEntry)

            return Pair(res, tick)
        }
    }

    //Method used for the actual merging of arrays. It decides which list to compare using the variable byEntry
    //leftSorter: ArrayList<BOOK> sorted array to be merged
    //rightSorted: ArrayList<BOOK> sorted array to be merged
    //res: ArrayList<BOOk> result array
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //returns Pair<ArrayList<B00K>, int> : sorted list and number of ticks
    fun merge_rec_1(leftSorted: ArrayList<BOOK>,
                    rightSorted: ArrayList<BOOK>,
                    res: ArrayList<BOOK>,
                    byEntry: String){

        fun merge_rec_author(
            leftSorted: ArrayList<BOOK>, l: Int,
            rightSorted: ArrayList<BOOK>, r: Int,
            res: ArrayList<BOOK>, t: Int,
            byEntry: String
        ) {

            var leftName = ""
            var rightName = ""
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
}





