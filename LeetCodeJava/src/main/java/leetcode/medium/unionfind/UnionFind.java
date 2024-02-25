package leetcode.medium.unionfind;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UnionFind {
    private final Map<Integer, Integer> parents, groupSizes;
    private int numGroups;
    private final List<Integer> vertices;

    public UnionFind(int numVertices) {
        this(IntStream.range(0, numVertices).boxed().collect(Collectors.toList()));
    }

    public UnionFind(List<Integer> vertices) {
        parents = new HashMap<>();
        groupSizes = new HashMap<>();
        for (var vertex : vertices) {
            parents.put(vertex, vertex);
            groupSizes.put(vertex, 1);
        }
        numGroups = vertices.size();
        this.vertices = vertices;
    }

    public int getNumGroups() {
        return numGroups;
    }

    public int getGroupSize(int vertex) {
        return groupSizes.get(find(vertex));
    }

    public Collection<Set<Integer>> getGroups(boolean sorted) {
        return groupByParent(sorted).values();
    }

    public Map<Integer, Set<Integer>> groupByParent(boolean sorted) {
        Map<Integer, Set<Integer>> groups = new HashMap<>();
        for (int vertex : vertices) {
            int parent = find(vertex);
            if (!groups.containsKey(parent)) groups.put(parent, sorted ? new TreeSet<>() : new HashSet<>());
            groups.get(parent).add(vertex);
        }
        return groups;
    }

    public int find(int vertex) {
        if (parents.get(vertex) == vertex) return vertex;
        int parent = find(parents.get(vertex));
        parents.put(vertex, parent); //path compression
        return parent;
    }

    public boolean union(int vertex, int otherVertex) {
        int vertexParent = find(vertex);
        int otherVertexParent = find(otherVertex);
        if (vertexParent == otherVertexParent) return true;

        int vertexGroupSize = groupSizes.get(vertexParent);
        int otherVertexGroupSize = groupSizes.get(otherVertexParent);
        int deadParent = vertexParent;
        int finalParent = otherVertexParent;
        if (vertexGroupSize > otherVertexGroupSize) {
            deadParent = otherVertexParent;
            finalParent = vertexParent;
        }
        parents.put(deadParent, finalParent);
        groupSizes.remove(deadParent);
        groupSizes.put(finalParent, vertexGroupSize + otherVertexGroupSize);
        numGroups--;
        return find(vertex) == find(otherVertex); // force compression
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        System.out.println(uf.getNumGroups());//5
        uf.union(0, 1);
        System.out.println(uf.getNumGroups());//4
        uf.union(0, 2);
        System.out.println(uf.getNumGroups());//3
        uf.union(3, 4);
        System.out.println(uf.getNumGroups());//2
        uf.union(2, 3);
        System.out.println(uf.getNumGroups());//1
        System.out.println(uf.union(1, 3));//true
        System.out.println(uf.getNumGroups());//1
        System.out.println(uf.getGroupSize(0));//5
        System.out.println(uf.groupByParent(true));//{0=[0, 1, 2, 3, 4]}
    }
}