package coursework.sorting


import coursework.database.BOOK

// TODO : Implement for any type (not just LECTURER) and provide order functional.
object Bubble {

    fun sortAuthorName(my_array : ArrayList<BOOK>): Int {
        var n: Int = my_array.size // length of array
        var ticks = 0
        // Q: n=min p : 0 <= p <= N and p<N-2 -> A[p]>A[p+1]: p
        // quota : n >= 0
        // I : \\forall i : n <= i < size: A[i]<A[i+1]
       do {
            var newn = 0
            // I2: newn > 0 -> \forall i: newn < i <size : A[i-1]<A[i]
            for (i in 1 until n)  {
                ticks += 1
//                var firstAuthor = my_array[i-1].
//                val firstName = my_array[i-1].AUTHOR.split(" ")[1]
//                val secondName = my_array[i].AUTHOR.split(" ")[1]
//                if (firstName > secondName) {
                if (my_array[i-1].AUTHOR > my_array[i].AUTHOR) {
                    val tmp = my_array[i-1]
                    my_array[i-1]=my_array[i]
                    my_array[i] = tmp
                    newn = i
                }

            }
           println("TMP bubble1:$my_array")
            n = newn
        }  while (n>1)
//        for (i in my_array)     println(i.AUTHOR)
//        println("----")

        return ticks
    }



    fun sortBookTitle(my_array : ArrayList<BOOK>): Int {
        var n: Int = my_array.size // length of array
        var ticks = 0
        // Q: n=min p : 0 <= p <= N and p<N-2 -> A[p]>A[p+1]: p
        // quota : n >= 0
        // I : \\forall i : n <= i < size: A[i]<A[i+1]
        do {
            var newn = 0
            // I2: newn > 0 -> \forall i: newn < i <size : A[i-1]<A[i]
            for (i in 1 until n)  {
                ticks += 1
//                var firstAuthor = my_array[i-1].
//                val firstName = my_array[i-1].AUTHOR.split(" ")[1]
//                val secondName = my_array[i].AUTHOR.split(" ")[1]
//                if (firstName > secondName) {
                if (my_array[i-1].TITLE > my_array[i].TITLE) {
                    val tmp = my_array[i-1]
                    my_array[i-1]=my_array[i]
                    my_array[i] = tmp
                    newn = i
                }

            }
            println("TMP bubble1:$my_array")
            n = newn
        }  while (n>1)
//        for (i in my_array)     println(i.AUTHOR)
//        println("----")

        return ticks
    }

    fun sortString(my_array : ArrayList<String>): Int {
        var n:Int=my_array.size
        var ticks = 0
        // Q: n=min p : 0 <= p <= N and p<N-2 -> A[p]>A[p+1]: p
        // quota : n >= 0
        // I : \\forall i : n <= i < size: A[i]<A[i+1]
        do {
            var newn = 0
            // I2: newn > 0 -> \forall i: newn < i <size : A[i-1]<A[i]
            for (i in 1 until n)  {
                ticks += 1
//                var firstAuthor = my_array[i-1].
                val firstName = my_array[i-1] //.YEAR_OF_PUBLICATION //.AUTHOR.split(" ")[1]
                val secondName = my_array[i] //.YEAR_OF_PUBLICATION //.AUTHOR.split(" ")[1]
                if (firstName > secondName) {
                    val tmp = my_array[i-1]
                    my_array[i-1]=my_array[i]
                    my_array[i] = tmp
                    newn = i
                }
            }
            n = newn
        }  while (n>1)
//        for (i in my_array)     println(i.AGE)
//        println("----")

        return ticks
    }

}

//fun main() {
//    val test = ArrayList(arrayListOf("Liam", "Olivia","Bebe","Harpez", "Noah", "Emma", "Oliver", "Charlotte", "Elijah", "Amelia", "James", "Ava",
//        "William", "Sophia", "Benjamin", "Isabella", "Lucas", "Mia", "Henry", "Evelyn", "Theodore", "Harper"))
//    val result = Bubble.sortString(test)
//    println(result)
//    println(test)
//    a.forEach({ println(it)}



