package coursework.sorting

import coursework.database.BOOK

object QuickSort {
    var tick = 0
    fun sort_author(unsortList: ArrayList<BOOK>) : Int
    {
        tick = 0
        // Inmmersion
        fun sort_g(unsortList: ArrayList<BOOK>, inf: Int, sup: Int): Int {
            if (sup - inf <= 1) {

            } else {
                val half = partition_author(unsortList, inf, sup)
                // Swap to reduce quota,
                val tmp = unsortList[inf]
                unsortList[inf] = unsortList[half - 1]
                unsortList[half - 1] = tmp
                //
                assert(((half - 1) - inf < sup - inf) && (sup - half < sup - inf))
                sort_g(unsortList, inf, half - 1)
                sort_g(unsortList, half, sup)
            }
            return tick
        }
        return sort_g(unsortList,0,unsortList.size)
    }



    // We cannot use slices of unsortedList, since we modify the parameter itself.
    private fun partition_author(unsortList: ArrayList<BOOK>, inf: Int, sup: Int): Int {
        var half = inf + 1
        assert(sup - inf > 1)
        for (n in inf + 1 until sup) {
            tick+=1
            if (unsortList[n].AUTHOR <= unsortList[inf].AUTHOR) {
                val tmp = unsortList[half]
                unsortList[half] = unsortList[n]
                unsortList[n] = tmp
                half += 1
            }
        }
        assert(inf < half && inf <= sup)
        return half
    }
    fun sort_title(unsortList: ArrayList<BOOK>) : Int
    {
        tick = 0
        // Inmmersion
        fun sort_g(unsortList: ArrayList<BOOK>, inf: Int, sup: Int): Int {
            if (sup - inf <= 1) {

            } else {
                val half = partition_title(unsortList, inf, sup)
                // Swap to reduce quota,
                val tmp = unsortList[inf]
                unsortList[inf] = unsortList[half - 1]
                unsortList[half - 1] = tmp
                //
                assert(((half - 1) - inf < sup - inf) && (sup - half < sup - inf))
                sort_g(unsortList, inf, half - 1)
                sort_g(unsortList, half, sup)
            }
            return tick
        }
        return sort_g(unsortList,0,unsortList.size)
    }



    // We cannot use slices of unsortedList, since we modify the parameter itself.
    private fun partition_title(unsortList: ArrayList<BOOK>, inf: Int, sup: Int): Int {
        var half = inf + 1
        assert(sup - inf > 1)
        for (n in inf + 1 until sup) {
            tick+=1
            if (unsortList[n].TITLE <= unsortList[inf].TITLE) {
                val tmp = unsortList[half]
                unsortList[half] = unsortList[n]
                unsortList[n] = tmp
                half += 1
            }
        }
        assert(inf < half && inf <= sup)
        return half
    }



}




