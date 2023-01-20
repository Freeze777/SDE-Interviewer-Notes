package leetcode.utils

fun randomIntList(size: Int, min: Int, max: Int): List<Int> {
    val list = mutableListOf<Int>()
    val range = (min..max)
    for (i in 0 until size) list.add(range.random())
    return list
}
fun randomIntArray(size: Int, min: Int, max: Int) = randomIntList(size, min, max).toIntArray()

fun main() {
    println(randomIntArray(15, -10, 10))
}
