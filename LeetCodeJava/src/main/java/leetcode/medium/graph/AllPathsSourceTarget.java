package leetcode.medium.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 797. All Paths From Source to Target
 * <a href="https://leetcode.com/problems/all-paths-from-source-to-target/">https://leetcode.com/problems/all-paths-from-source-to-target/</a>
 */
public class AllPathsSourceTarget {
    private static class BfsNode {
        int vertex;
        List<Integer> path;

        BfsNode(int vertex, List<Integer> path) {
            this.vertex = vertex;
            this.path = path;
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Queue<BfsNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.add(new BfsNode(0, new ArrayList<>()));
        while (!queue.isEmpty()) {
            BfsNode node = queue.poll();
            node.path.add(node.vertex);
            if (node.vertex == graph.length - 1) {
                result.add(node.path);
            } else {
                for (int nextNode : graph[node.vertex]) queue.add(new BfsNode(nextNode, new ArrayList<>(node.path)));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var apst = new AllPathsSourceTarget();
        //[[0, 1, 3], [0, 2, 3]]
        System.out.println(apst.allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
        //[[0, 4], [0, 3, 4], [0, 1, 3, 4], [0, 1, 2, 3, 4], [0, 1, 4]]
        System.out.println(apst.allPathsSourceTarget(new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}));
    }
}
