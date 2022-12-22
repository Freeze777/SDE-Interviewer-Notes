package leetcode.medium.graph

import java.util.LinkedList

/**
 * 841. Keys and Rooms
 * https://leetcode.com/problems/keys-and-rooms/
 */
fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
    val graph = HashMap<Int, MutableList<Int>>()
    for (room in rooms.indices) graph[room] = mutableListOf()
    for (room in rooms.indices) graph[room]?.addAll(rooms[room])
    val bfsQ = LinkedList<Int>()
    val visited = HashSet<Int>()
    bfsQ.add(0)
    while (bfsQ.isNotEmpty()) {
        val room = bfsQ.remove()
        if (visited.size == rooms.size) return true
        if (visited.contains(room)) continue
        graph[room]?.let { bfsQ.addAll(it) }
        visited.add(room)
    }
    return visited.size == rooms.size
}

fun main() {
    println(canVisitAllRooms(listOf(listOf(1), listOf(2), listOf(3), listOf())))
    println(canVisitAllRooms(listOf(listOf(1, 3), listOf(3, 0, 1), listOf(2), listOf(0))))
}