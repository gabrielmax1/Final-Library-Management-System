package coursework.searching

object BinarySearh {
// Java program to implement Binary Search for strings
    // Returns index of x if it is present in arr[],
    // else return -1
    fun binarySearch(arr: Array<String?>, x: String): Int {
        var l = 0
        var r = arr.size - 1
        while (l <= r) {
            val m = l + (r - l) / 2
            val res = x.compareTo(arr[m]!!)

            // Check if x is present at mid
            if (res == 0) return m

            // If x greater, ignore left half
            if (res > 0) l = m + 1 else r = m - 1
        }
        return -1
    }

    // Driver method to test above
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = arrayOf<String?>("test", "module", "IntelliJ", "Practice")
        val x = "module"
        val result = binarySearch(arr, x)
        if (result == -1) println("Element not present") else println(
            "Element found at "
                    + "index " + result
        )
    }
}