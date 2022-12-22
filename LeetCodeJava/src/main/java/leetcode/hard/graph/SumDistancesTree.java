package leetcode.hard.graph;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 834. Sum of Distances in Tree
 * <a href="https://leetcode.com/problems/sum-of-distances-in-tree/">https://leetcode.com/problems/sum-of-distances-in-tree/</a>
 */
public class SumDistancesTree {
    private void computeAllDistanceSums(int root, int n, HashMap<Integer, List<Integer>> graph, int[] answer, int[] subtreeSize) {
        for (Integer child : graph.get(root)) {
            if (answer[child] == 0) {
                answer[child] = (answer[root] - subtreeSize[child]) + (n - subtreeSize[child]);
                computeAllDistanceSums(child, n, graph, answer, subtreeSize);
            }
        }
    }

    private void computeRootStatistics(int root, HashMap<Integer, List<Integer>> graph, int level, boolean[] visited, int[] subtreeSize, int[] distance) {
        visited[root] = true;
        var size = 1;
        for (Integer child : graph.get(root)) {
            if (!visited[child]) {
                computeRootStatistics(child, graph, level + 1, visited, subtreeSize, distance);
                size += subtreeSize[child];
            }
        }
        distance[root] = level;
        subtreeSize[root] = size;
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        var answer = new int[n];
        var graph = new HashMap<Integer, List<Integer>>();
        for (var i = 0; i < n; i++) graph.put(i, new LinkedList<>());
        for (var edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        var root = ThreadLocalRandom.current().nextInt(0, n); // any vertex can be a root
        var subtreeSize = new int[n];
        var distance = new int[n];
        var visited = new boolean[n];
        computeRootStatistics(root, graph, 0, visited, subtreeSize, distance);
        answer[root] = Arrays.stream(distance).sum();
        computeAllDistanceSums(root, n, graph, answer, subtreeSize);
        return answer;
    }

    public static void main(String[] args) {
        var sumDistancesTree = new SumDistancesTree();
        //[8, 12, 6, 10, 10, 10]
        System.out.println(Arrays.toString(sumDistancesTree.sumOfDistancesInTree(6, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}})));
        //[2, 3, 3]
        System.out.println(Arrays.toString(sumDistancesTree.sumOfDistancesInTree(3, new int[][]{{1, 0}, {2, 0}})));
        //[0]
        System.out.println(Arrays.toString(sumDistancesTree.sumOfDistancesInTree(1, new int[][]{})));
        //[1, 1]
        System.out.println(Arrays.toString(sumDistancesTree.sumOfDistancesInTree(2, new int[][]{{1, 0}})));
        //[11, 16, 8, 13, 13, 11, 16]
        System.out.println(Arrays.toString(sumDistancesTree.sumOfDistancesInTree(7, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}, {5, 6}})));
        //[15, 21, 11, 17, 17, 13, 17, 23]
        System.out.println(Arrays.toString(sumDistancesTree.sumOfDistancesInTree(8, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}, {5, 6}, {6, 7}})));
    }
}
