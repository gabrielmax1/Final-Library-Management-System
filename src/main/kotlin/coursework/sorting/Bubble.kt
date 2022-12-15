package coursework.sorting


import coursework.database.BOOK

// TODO : Implement for any type (not just LECTURER) and provide order functional.
object Bubble {

    //Iterative implementation for Bubble sort on Author
    fun sortAuthorName(my_array : ArrayList<BOOK>): Int {
        var n: Int = my_array.size // length of array
        var ticks = 0

       do {
            var newn = 0
            for (i in 1 until n)  {
                ticks += 1
                if (my_array[i-1].AUTHOR > my_array[i].AUTHOR) {
                    val tmp = my_array[i-1]
                    my_array[i-1]=my_array[i]
                    my_array[i] = tmp
                    newn = i
                }

            }
            n = newn
        }  while (n>1)

        return ticks
    }

    //Iterative implementation for Bubble sort on Title
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
//            println("TMP bubble1:$my_array")
            n = newn
        }  while (n>1)
//        for (i in my_array)     println(i.AUTHOR)
//        println("----")

        return ticks
    }

    //Recursive implementation for Bubble sort on Title
    fun sortRecursiveTitle(my_array: ArrayList<BOOK>, arr_length : Int , tick : Int): Int{

        if(arr_length == 1) {
            return tick
        }

//        var temp : BOOK

        for( i in 1 until arr_length ){

            if(my_array[i].TITLE < my_array[i-1].TITLE){
                var temp : BOOK = my_array[i]
                my_array[i] = my_array[i-1]
                my_array[i-1] = temp
            }
        }

        return tick + sortRecursiveTitle(my_array, arr_length-1, tick+1)
    }

    //Recursive implementation for Bubble sort on Author
    fun sortRecursiveAuthor(my_array: ArrayList<BOOK>, arr_length : Int , tick : Int): Int{

        if(arr_length == 1) {
            return tick
        }

//        var temp : BOOK

        for( i in 1 until arr_length ){

            if(my_array[i].AUTHOR < my_array[i-1].AUTHOR){
                var temp : BOOK = my_array[i]
                my_array[i] = my_array[i-1]
                my_array[i-1] = temp
            }
        }

        return tick + sortRecursiveAuthor(my_array, arr_length-1, tick+1)
    }

}




