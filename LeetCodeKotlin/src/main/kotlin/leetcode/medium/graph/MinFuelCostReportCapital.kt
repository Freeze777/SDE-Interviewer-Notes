package leetcode.medium.graph

import kotlin.math.ceil

/**
 * 2477. Minimum Fuel Cost to Report to the Capital
 * https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
 */
class MinFuelCostReportCapital {
    data class FuelStats(val fuelConsumed: Long, val numPeople: Int)

    fun minimumFuelCost(roads: Array<IntArray>, s: Int): Long {
        val graph = Array(roads.size + 1) { mutableListOf<Int>() }
        for (i in roads.indices) {
            graph[roads[i][0]].add(roads[i][1])
            graph[roads[i][1]].add(roads[i][0])
        }
        val visited = BooleanArray(graph.size)
        return postOrderWalk(graph, visited, 0, s).fuelConsumed
    }

    private fun postOrderWalk(
        graph: Array<MutableList<Int>>,
        visited: BooleanArray,
        current: Int,
        seats: Int
    ): FuelStats {
        visited[current] = true
        val connectedCities = graph[current]
        if (connectedCities.size == 1 && visited[connectedCities[0]]) return FuelStats(1, 1) //leaf node
        var (totalFuelConsumed, totalPeople) = 0L to 1
        for (city in connectedCities) {
            if (visited[city]) continue
            val fuelStats = postOrderWalk(graph, visited, city, seats)
            totalFuelConsumed += fuelStats.fuelConsumed
            totalPeople += fuelStats.numPeople
        }
        if (current == 0) return FuelStats(totalFuelConsumed, totalPeople)
        val numCars = ceil(totalPeople / (seats * 1.0)).toInt()
        return FuelStats(totalFuelConsumed + numCars, totalPeople)
    }
}

fun main() {
    val mfcrc = MinFuelCostReportCapital()
    println(
        mfcrc.minimumFuelCost(
            arrayOf(
                intArrayOf(3, 1),
                intArrayOf(3, 2),
                intArrayOf(1, 0),
                intArrayOf(0, 4),
                intArrayOf(0, 5),
                intArrayOf(4, 6)
            ), 2
        )
    )// 7

    println(
        mfcrc.minimumFuelCost(
            arrayOf(
                intArrayOf(3, 1),
                intArrayOf(3, 2),
                intArrayOf(1, 0),
                intArrayOf(0, 4),
                intArrayOf(0, 5),
                intArrayOf(4, 6),
                intArrayOf(6, 7),
                intArrayOf(2, 8),
                intArrayOf(7, 9),
                intArrayOf(9, 10),
                intArrayOf(8, 11)
            ), 2
        )
    )// 19
}