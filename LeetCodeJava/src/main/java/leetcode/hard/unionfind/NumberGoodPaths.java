package leetcode.hard.unionfind;

import leetcode.medium.unionfind.UnionFind;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/number-of-good-paths/">2421. Number of Good Paths</a>
 */
public class NumberGoodPaths {
    public int numberOfGoodPaths(int[] values, int[][] edges) {
        final int N = values.length;
        var graph = new HashMap<Integer, List<Integer>>();
        var groupByValues = new TreeMap<Integer, List<Integer>>();
        for (int vertex = 0; vertex < N; vertex++) {
            graph.put(vertex, new ArrayList<>());
            groupByValues.computeIfAbsent(values[vertex], k -> new ArrayList<>()).add(vertex);
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        var visited = new boolean[N];
        var uf = new UnionFind(N);
        var totalGoodPaths = N;
        //process groups in descending order of node values. Hence, we used TreeMap.
        for (var sameValuesVertices : groupByValues.values()) {
            for (var vertex : sameValuesVertices) {
                graph.get(vertex).stream().filter(neighbour -> visited[neighbour]).forEach(neighbor -> uf.union(vertex, neighbor));
                visited[vertex] = true;
            }
            var vertexCountByParent = new HashMap<Integer, Integer>();
            sameValuesVertices.stream().map(uf::find).forEach(parent -> vertexCountByParent.put(parent, vertexCountByParent.getOrDefault(parent, 0) + 1));
            totalGoodPaths += vertexCountByParent.values().stream().map(vertexCount -> vertexCount * (vertexCount - 1) / 2).reduce(0, Integer::sum);
        }
        return totalGoodPaths;
    }

    public static void main(String[] args) {
        var ngp = new NumberGoodPaths();
        System.out.println(ngp.numberOfGoodPaths(new int[]{1, 3, 2, 1, 3}, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}}));//6
        System.out.println(ngp.numberOfGoodPaths(new int[]{1, 1, 2, 2, 3}, new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}}));//7
        System.out.println(ngp.numberOfGoodPaths(new int[]{1}, new int[][]{}));//1
        System.out.println(ngp.numberOfGoodPaths(new int[]{1, 2, 3, 4, 5}, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));//5
        System.out.println(ngp.numberOfGoodPaths(new int[]{1, 2, 3, 3, 2}, new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}}));//6
        System.out.println(ngp.numberOfGoodPaths(new int[]{1, 1, 2, 2, 2, 3}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}}));//10
    }
}
