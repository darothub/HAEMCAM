package ng.com.thewhitecellfoundation.common.utils

import java.lang.Math.abs

fun findMissingNumber(a: ArrayList<Int>): Int {
    println("Param $a")
    a.remove(0)
    if (a.size == 1 && a[0] == 0) {
        return 1
    }
    val sum = a.sum()
    println("sum $sum")
    val size = a.size
    println("size $size")
    val expectedSum = (size + 1) * (size + 2) / 2
    println("expectedSum $expectedSum")
    return kotlin.math.abs(expectedSum - sum)
}

fun main() {
    val a = arrayListOf<Int>(1)
    println(findMissingNumber(a))
}
