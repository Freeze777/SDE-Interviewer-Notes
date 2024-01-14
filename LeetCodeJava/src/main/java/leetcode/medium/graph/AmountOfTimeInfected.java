package leetcode.medium.graph;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/">https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/</a>
 */
public class AmountOfTimeInfected {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public Map<Integer, List<Integer>> convertBinaryTreeToGraph(TreeNode root) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        buildGraph(root, adjacencyList);
        return adjacencyList;
    }

    private void buildGraph(TreeNode node, Map<Integer, List<Integer>> adjacencyList) {
        if (node == null) {
            return;
        }

        adjacencyList.putIfAbsent(node.val, new ArrayList<>());

        if (node.left != null) {
            adjacencyList.get(node.val).add(node.left.val);
            adjacencyList.putIfAbsent(node.left.val, new ArrayList<>());
            adjacencyList.get(node.left.val).add(node.val);
            buildGraph(node.left, adjacencyList);
        }

        if (node.right != null) {
            adjacencyList.get(node.val).add(node.right.val);
            adjacencyList.putIfAbsent(node.right.val, new ArrayList<>());
            adjacencyList.get(node.right.val).add(node.val);
            buildGraph(node.right, adjacencyList);
        }
    }

    public int bfs(Map<Integer, List<Integer>> graph, int start) {
        record TimeAndNode(int time, int val) {
        }
        int maxTime = 0;
        Queue<TimeAndNode> queue = new LinkedList<>();
        queue.add(new TimeAndNode(0, start));
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            TimeAndNode node = queue.poll();
            maxTime = Math.max(maxTime, node.time);
            for (int neighbor : graph.get(node.val)) {
                if (!visited.contains(neighbor)) {
                    queue.add(new TimeAndNode(node.time + 1, neighbor));
                }
            }
            visited.add(node.val);
        }
        return maxTime;
    }


    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = convertBinaryTreeToGraph(root);
        return bfs(graph, start);
    }

    public static void main(String[] args) {
        AmountOfTimeInfected amountOfTimeInfected = new AmountOfTimeInfected();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(amountOfTimeInfected.amountOfTime(root, 3));
    }
}
