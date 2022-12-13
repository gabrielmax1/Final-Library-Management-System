package coursework.sorting

import coursework.database.BOOK

object MergeSort {

    var tick = 0
    fun sort_author(unsortList: ArrayList<BOOK>): Pair<ArrayList<BOOK>, Int> {
        tick = 0
        val res = ArrayList(unsortList)

        if (unsortList.size <= 1)
            return Pair(res, tick)
        else {
            val half = unsortList.size / 2
            val leftScrambled = ArrayList(unsortList.slice(0 until half))
            val rightScrambled = ArrayList(unsortList.slice(half until unsortList.size))

            val (leftSorted, _) = sort_author(leftScrambled)
            val (rightSorted, _) = sort_author(rightScrambled)

            merge_rec_1(leftSorted, rightSorted, res)

            return Pair(res, tick)
        }
    }

    fun merge_rec_1(
        leftSorted: ArrayList<BOOK>,
        rightSorted: ArrayList<BOOK>,
        res: ArrayList<BOOK>,
    ) {

        fun merge_rec_author(
            leftSorted: ArrayList<BOOK>, l: Int,
            rightSorted: ArrayList<BOOK>, r: Int,
            res: ArrayList<BOOK>, t: Int,
        ) {
            if (!((l < leftSorted.size) || (r < rightSorted.size))) {
                assert((l == leftSorted.size) && (r == rightSorted.size))
            } else {
                assert(t < res.size)
                tick += 1
                if (l == leftSorted.size) {
                    res[t] = rightSorted[r]
                    merge_rec_author(leftSorted, l, rightSorted, r + 1, res, t + 1)
                } else if (r == rightSorted.size) {
                    res[t] = leftSorted[l]
                    merge_rec_author(leftSorted, l + 1, rightSorted, r, res, t + 1)
                } else {
                    val leftName = leftSorted[l].AUTHOR
                    val rightName = rightSorted[r].AUTHOR
                    if (leftName < rightName) {
                        res[t] = leftSorted[l]
                        merge_rec_author(leftSorted, l + 1, rightSorted, r, res, t + 1)
                    } else {
                        res[t] = rightSorted[r]
                        merge_rec_author(leftSorted, l, rightSorted, r + 1, res, t + 1)
                    }
                }
            }
        }

//        // Inmmersion call.
          var (l, r, t) = Triple(0, 0, 0)
          merge_rec_author(leftSorted, l, rightSorted, r, res, t)  // not B
    }
    fun sort_title(unsortList: ArrayList<BOOK>): Pair<ArrayList<BOOK>, Int> {
        tick = 0
        val res = ArrayList(unsortList)

        if (unsortList.size <= 1)
            return Pair(res, tick)
        else {
            val half = unsortList.size / 2
            val leftScrambled = ArrayList(unsortList.slice(0 until half))
            val rightScrambled = ArrayList(unsortList.slice(half until unsortList.size))

            val (leftSorted, _) = sort_title(leftScrambled)
            val (rightSorted, _) = sort_title(rightScrambled)

            merge_rec_2(leftSorted, rightSorted, res)

            return Pair(res, tick)
        }
    }
    fun merge_rec_2(
        leftSorted: ArrayList<BOOK>,
        rightSorted: ArrayList<BOOK>,
        res: ArrayList<BOOK>,
    ) {

        fun merge_rec_title(
            leftSorted: ArrayList<BOOK>, l: Int,
            rightSorted: ArrayList<BOOK>, r: Int,
            res: ArrayList<BOOK>, t: Int,
        ) {
            if (!((l < leftSorted.size) || (r < rightSorted.size))) {
                assert((l == leftSorted.size) && (r == rightSorted.size))
            } else {
                assert(t < res.size)
                tick += 1
                if (l == leftSorted.size) {
                    res[t] = rightSorted[r]
                    merge_rec_title(leftSorted, l, rightSorted, r + 1, res, t + 1)
                } else if (r == rightSorted.size) {
                    res[t] = leftSorted[l]
                    merge_rec_title(leftSorted, l + 1, rightSorted, r, res, t + 1)
                } else {
                    val leftName = leftSorted[l].TITLE
                    val rightName = rightSorted[r].TITLE
                    if (leftName < rightName) {
                        res[t] = leftSorted[l]
                        merge_rec_title(leftSorted, l + 1, rightSorted, r, res, t + 1)
                    } else {
                        res[t] = rightSorted[r]
                        merge_rec_title(leftSorted, l, rightSorted, r + 1, res, t + 1)
                    }
                }
            }
        }
        // Inmmersion call.
        var (l1, r1, t1) = kotlin.Triple(0, 0, 0)
        merge_rec_title(leftSorted, l1, rightSorted, r1, res, t1)  // not B

    }

}

//fun main() {
//    val (left, right, res) =Triple(
//            ArrayList(arrayListOf(-1,2,3)),
//            ArrayList(arrayListOf(1,12,13)),
//            ArrayList(arrayListOf(1,32,13,4,15,6)))
//    MergeSort.merge_R(left,right,res)
//    res.forEach { i ->
//        print("${i} ")
//    }
//}

//fun main() {
//    val left = ArrayList(arrayListOf("Liam", "Olivia","Bebe","Harpez", "Noah", "Emma", "Oliver", "Charlotte", "Elijah", "Amelia", "James", "Ava",
//        "William", "Sophia", "Benjamin", "Isabella", "Lucas", "Mia", "Henry", "Evelyn", "Theodore", "Harper"))
//    val (a,i) = MergeSort.sort(left)
//    println(i)
//    a.forEach({ println(it)}
//    )
//
//}
