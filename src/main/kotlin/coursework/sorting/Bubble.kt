package coursework.sorting


import coursework.database.BOOK
import java.util.*
import kotlin.collections.ArrayList



//Singleton object used for Bubble sort methods to sort book entries, implements both iterative and recursive implementations.
object Bubble {


    //Iterative implementation of Bubble sort for list of BOOK
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //returns the number of ticks
    fun sortIterative(my_array : ArrayList<BOOK>, byEntry: String): Int {

        var n: Int = my_array.size
        var ticks = 0


        do {
            var newn = 0
            for (i in 1 until n)  {
                ticks += 1

                //Choose what list to sort
                if(byEntry.uppercase(Locale.getDefault()) == "AUTHOR"){

                    if (my_array[i-1].AUTHOR > my_array[i].AUTHOR) {
                        val tmp = my_array[i-1]
                        my_array[i-1]=my_array[i]
                        my_array[i] = tmp
                        newn = i
                    }
                }

                else{
                    if (my_array[i-1].TITLE > my_array[i].TITLE) {
                        val tmp = my_array[i-1]
                        my_array[i-1]=my_array[i]
                        my_array[i] = tmp
                        newn = i
                    }
                }




            }
            n = newn
        }  while (n>1)

        return ticks
    }

    //Recursive implementation of Bubble sort for list of BOOK
    //byEntry: "TITLE" or "AUTHOR" used to decide which list to sort
    //returns the number of ticks
    fun sortRecursive(my_array: ArrayList<BOOK>, byEntry: String, arr_length : Int , tick : Int): Int{

        if(arr_length == 1) {
            return tick
        }


        for( i in 1 until arr_length ){

            //Choose what list to sort by
            if(byEntry.uppercase(Locale.getDefault()) == "AUTHOR"){

                if (my_array[i-1].AUTHOR < my_array[i].AUTHOR) {
                    val tmp = my_array[i-1]
                    my_array[i-1]=my_array[i]
                    my_array[i] = tmp

                }
            }

            else{
                if (my_array[i-1].TITLE < my_array[i].TITLE) {
                    val tmp = my_array[i-1]
                    my_array[i-1]=my_array[i]
                    my_array[i] = tmp

                }
            }
        }

        return tick + sortRecursive(my_array, byEntry, arr_length-1, tick+1)
    }



}




