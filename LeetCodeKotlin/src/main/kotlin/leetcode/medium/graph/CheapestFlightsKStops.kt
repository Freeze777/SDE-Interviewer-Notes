package leetcode.medium.graph

import java.util.*

/**
 * 787. Cheapest Flights Within K Stops
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
class CheapestFlightsKStops {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, source: Int, destination: Int, maxStops: Int): Int {
        val graph = Array(n) { mutableListOf<Pair<Int, Int>>() } // (destination, cost)
        for (flight in flights) graph[flight[0]].add(Pair(flight[1], flight[2]))
        val minHeap = PriorityQueue<Triple<Int, Int, Int>> { a, b -> b.third - a.third } // (city, stops, cost)
        val overallCost = Array(n) { Int.MAX_VALUE }
        minHeap.add(Triple(source, maxStops, 0))
        overallCost[source] = 0
        while (minHeap.isNotEmpty()) {
            val (city, stops, currentCost) = minHeap.poll()
            overallCost[city] = minOf(currentCost, overallCost[city])
            if (stops >= 0) {
                for ((nextCity, nextCost) in graph[city]) {
                    if (currentCost + nextCost < overallCost[nextCity]) {
                        minHeap.add(Triple(nextCity, stops - 1, currentCost + nextCost))
                    }
                }
            }
        }
        return if (overallCost[destination] == Int.MAX_VALUE) -1 else overallCost[destination]
    }
}

fun main() {
    val cfks = CheapestFlightsKStops()
    println(
        cfks.findCheapestPrice(
            4,
            arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(2, 0, 100),
                intArrayOf(1, 3, 600),
                intArrayOf(2, 3, 200)
            ),
            0,
            3,
            1
        )
    )//700
    println(
        cfks.findCheapestPrice(
            3,
            arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(0, 2, 500)
            ),
            0,
            2,
            0
        )
    )//500
    println(
        cfks.findCheapestPrice(
            3,
            arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(0, 2, 500)
            ),
            0,
            2,
            1
        )
    )//200
    println(
        cfks.findCheapestPrice(
            3,
            arrayOf(
                intArrayOf(0, 1, 100),
                intArrayOf(1, 2, 100),
                intArrayOf(0, 2, 500)
            ),
            2,
            1,
            1
        )
    )//-1
    println(
        cfks.findCheapestPrice(
            11, arrayOf(
                intArrayOf(0, 3, 3),
                intArrayOf(3, 4, 3),
                intArrayOf(4, 1, 3),
                intArrayOf(0, 5, 1),
                intArrayOf(5, 1, 100),
                intArrayOf(0, 6, 2),
                intArrayOf(6, 1, 100),
                intArrayOf(0, 7, 1),
                intArrayOf(7, 8, 1),
                intArrayOf(8, 9, 1),
                intArrayOf(9, 1, 1),
                intArrayOf(1, 10, 1),
                intArrayOf(10, 2, 1),
                intArrayOf(1, 2, 100)
            ), 0, 2, 4
        )
    )//11
}
