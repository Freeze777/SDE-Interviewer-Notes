package leetcode.medium.unionfind;

import java.util.*;

public class UnionFind {
    private final Map<Integer, Integer> parents = new HashMap<>();
    private final Map<Integer, Integer> groupSizes = new HashMap<>();
    private int numGroups;
    private int size;

    public UnionFind(int numVertices) {
        initialize(numVertices);
    }

    @Override
    public String toString() {
        return "UnionFind{" + "parents=" + parents + ", groupSizes=" + groupSizes +
                ", numGroups=" + numGroups + ", size=" + size + '}';
    }

    public int getNumGroups() {
        return numGroups;
    }

    public int getGroupSizes(int vertex) {
        return groupSizes.get(find(vertex));
    }

    private void initialize(int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            parents.put(i, i);
            groupSizes.put(i, 1);
        }
        size = numGroups = numVertices;
    }

    public Collection<Set<Integer>> getGroups() {
        HashMap<Integer, Set<Integer>> groups = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int parent = find(i);
            if (!groups.containsKey(parent)) groups.put(parent, new HashSet<>());
            groups.get(parent).add(i);
        }
        return groups.values();
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
        System.out.println(uf.getNumGroups());
        uf.union(0, 1);
        System.out.println(uf.getNumGroups());
        uf.union(0, 2);
        System.out.println(uf.getNumGroups());
        uf.union(3, 4);
        System.out.println(uf.getNumGroups());
        uf.union(2, 3);
        System.out.println(uf.getNumGroups());
        uf.union(1, 3);
        System.out.println(uf.getNumGroups());
        System.out.println(uf.getGroupSizes(0));
    }
}
