package interviews;

import java.util.*;

public class GrabInterviewQuestion {

    static class Graph {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
    }

    static class Pair {
        Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        Integer first;
        Integer second;
    }

    public static void function(int n, List<Pair> mergers, List<Integer> queries) {
        Graph graph = new Graph();
        for (int i = 0; i < n; i++) {
            graph.adjList.put(i, new ArrayList<>());
        }
        for (Pair pair : mergers) {
            graph.adjList.get(pair.first).add(pair.second);
            graph.adjList.get(pair.second).add(pair.first);
        }

        //Optimisation: Precompute connected components
        // Time Complexity: O(N + E) for precomputation, O(1) per query
        Integer[] results = precompute(graph);

        for (Integer query : queries) {
            System.out.println(results[query]);
        }
    }

    private static Integer[] precompute(Graph graph) {
        Integer[] results = new Integer[graph.adjList.size()];
        boolean[] visited = new boolean[graph.adjList.size()];
        Integer startNode = 0;
        while (true) {
            startNode = findNextUnvisited(startNode, visited);
            //Find all reachable node to startNode using BFS
            Set<Integer> reachableNodes = new HashSet<>();
            if (startNode == null) break;
            Queue<Integer> bfsQ = new LinkedList<>();
            bfsQ.add(startNode);
            while (!bfsQ.isEmpty()) {
                final Integer node = bfsQ.remove();
                visited[node] = true;
                reachableNodes.add(node);
                for (Integer adjNode : graph.adjList.get(node)) {
                    if (!visited[adjNode]) {
                        bfsQ.add(adjNode);
                    }
                }
            }
            Integer noOfReachableNodes = reachableNodes.size();
            for (Integer node : reachableNodes) {
                results[node] = noOfReachableNodes;
            }
        }
        return results;
    }

    private static Integer findNextUnvisited(int startNode, boolean[] visited) {
        for (Integer i = startNode; i < visited.length; i++) {
            if (!visited[i]) return i;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Test : 1");
        List<Pair> mergers = Arrays.asList(new Pair(0, 1), new Pair(2, 3));
        List<Integer> queries = Arrays.asList(0, 1, 2, 3);
        function(4, mergers, queries);
        System.out.println("Test : 2");
        mergers = Arrays.asList(new Pair(0, 1), new Pair(1, 3));
        queries = Arrays.asList(0, 1, 2, 3);
        function(4, mergers, queries);
        System.out.println("Test : 3");
        mergers = Arrays.asList(new Pair(0, 1));
        queries = Arrays.asList(0, 1, 2, 3);
        function(4, mergers, queries);
        System.out.println("Test : 4");
        mergers = Arrays.asList(new Pair(0, 1), new Pair(1, 2), new Pair(2, 3));
        queries = Arrays.asList(0, 1, 2, 3);
        function(4, mergers, queries);
    }
}
