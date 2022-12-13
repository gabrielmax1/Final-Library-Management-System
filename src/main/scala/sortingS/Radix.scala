//package sortingS
//import coursework.database.BOOK
//
//import java.util
//import scala.collection.mutable
//
//import java.util.ArrayList
//
//
//object Radix {
//
//  def findMaxLenght(arr: util.ArrayList[BOOK]): Int = {
//
//    var maxLen = 0
//    val it = arr.iterator()
//    while (it.hasNext) {
//
//      if (it.next().getAUTHOR.length > maxLen) {
//        maxLen = it.next().getAUTHOR.length
//      }
//    }
//    maxLen
//  }
//}
//    def sort(arr: util.ArrayList[BOOK], place: Int): Unit ={
//
//      val maxLen = findMaxLenght(arr)
//
//      //create bucket
//      var buckets = mutable.HashMap[Int, mutable.MutableList[String]]()
//      //Initialize bucket
//      for(i <- 0 until 28){
//        buckets += (i, new mutable.MutableList[String])
//      }
//
//      var place = 1
//
//      while(place < maxLen){
//
//
//
//        print(buckets)
//
//
//        place+= maxLen
//      }



