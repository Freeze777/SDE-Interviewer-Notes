package leetcode.utils

fun randomIntList(size: Int, lowerLimit: Int, upperLimit: Int): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 0 until size) {
        list.add((lowerLimit..upperLimit).random())
    }
    return list
}

fun main() {
    println(randomIntList(15, -10, 10))
}
