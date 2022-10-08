package leetcode.medium.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/number-of-provinces/">https://leetcode.com/problems/number-of-provinces/</a>
 */
public class NumberOfProvinces {

    private void bfs(int startCity, int[][] isConnected, Set<Integer> visited) {
        Queue<Integer> bfsQ = new LinkedList<>();
        bfsQ.add(startCity);
        while (!bfsQ.isEmpty()) {
            int visitedCity = bfsQ.remove();
            visited.add(visitedCity);
            for (int neighbour = 0; neighbour < isConnected.length; neighbour++) {
                if (isConnected[visitedCity][neighbour] == 1 && !visited.contains(neighbour)) {
                    bfsQ.add(neighbour);
                }
            }
        }
    }

    public int findNumberOfProvinces(int[][] isConnected) {
        Set<Integer> visited = new HashSet<>();
        int numProvinces = 0;
        for (int city = 0; city < isConnected.length; city++) {
            if (visited.contains(city)) continue;
            bfs(city, isConnected, visited);
            numProvinces++;
        }
        return numProvinces;
    }

    public static void main(String[] args) {
        NumberOfProvinces nop = new NumberOfProvinces();
        System.out.println(nop.findNumberOfProvinces(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); //2
        System.out.println(nop.findNumberOfProvinces(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}})); //3
    }
}
