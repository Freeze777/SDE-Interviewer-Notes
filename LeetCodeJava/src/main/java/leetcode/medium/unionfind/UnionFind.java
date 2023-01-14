package leetcode.medium.unionfind;

import java.util.*;

public class UnionFind {
    private final Map<Integer, Integer> parents, groupSizes;
    private int numGroups, size;

    public UnionFind(int numVertices) {
        parents = new HashMap<>();
        groupSizes = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            parents.put(i, i);
            groupSizes.put(i, 1);
        }
        size = numGroups = numVertices;
    }

    public int getNumGroups() {
        return numGroups;
    }

    public int getGroupSizes(int vertex) {
        return groupSizes.get(find(vertex));
    }

    public Collection<Set<Integer>> getGroups(boolean sorted) {
        return getGroupsByParent(sorted).values();
    }

    public HashMap<Integer, Set<Integer>> getGroupsByParent(boolean sorted) {
        HashMap<Integer, Set<Integer>> groups = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int parent = find(i);
            if (!groups.containsKey(parent)) groups.put(parent, sorted ? new TreeSet<>() : new HashSet<>());
            groups.get(parent).add(i);
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
        System.out.println(uf.getGroupSizes(0));//5
        System.out.println(uf.getGroupsByParent(true));//{0=[0, 1, 2, 3, 4]}
    }
}