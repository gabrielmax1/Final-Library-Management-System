package coursework.sorting

import coursework.database.BOOK
import java.util.*
import kotlin.collections.ArrayList


//Singleton object used to sort book entries using QuickSort algorithms, only recursive method is implemented

object QuickSort {
    var tick = 0

    //Main method for quick sort
    fun sort(unsortList: ArrayList<BOOK>, byEntry: String): Int {
        tick = 0
        // Inmmersion
        fun sort_g(unsortList: ArrayList<BOOK>, byEntry: String, inf: Int, sup: Int): Int {

            //Check if arraySize is one or smaller
            if (sup - inf <= 1) {

            } else {
                //
                val half = partition(unsortList, byEntry, inf, sup)

                // Swap to reduce quota,
                val tmp = unsortList[inf]
                unsortList[inf] = unsortList[half - 1]
                unsortList[half - 1] = tmp

                assert(((half - 1) - inf < sup - inf) && (sup - half < sup - inf))
                sort_g(unsortList, byEntry, inf, half - 1)
                sort_g(unsortList, byEntry, half, sup)
            }
            return tick
        }
        return sort_g(unsortList, byEntry, 0, unsortList.size)
    }

    //Method used for the actual sorting of arrays. It decides which list to compare using the variable byEntry
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //inf: lower limit of array
    //sup: superior limit of array
    //returns the number of ticks
    private fun partition(unsortList: ArrayList<BOOK>, byEntry: String, inf: Int, sup: Int): Int {
        var half = inf + 1
        assert(sup - inf > 1)

        var left = ""
        var right = ""
        for (n in inf + 1 until sup) {
            tick += 1

            if (byEntry.uppercase(Locale.getDefault()) == "AUTHOR") {
                if (unsortList[n].AUTHOR <= unsortList[inf].AUTHOR) {
                    val tmp = unsortList[half]
                    unsortList[half] = unsortList[n]
                    unsortList[n] = tmp
                    half += 1
                }
            } else {
                if (unsortList[n].TITLE <= unsortList[inf].TITLE) {
                    val tmp = unsortList[half]
                    unsortList[half] = unsortList[n]
                    unsortList[n] = tmp
                    half += 1
                }
            }
        }
        assert(inf < half && inf <= sup)
        return half

    }
}