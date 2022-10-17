package leetcode.medium.unionfind;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<Integer, Integer> parents = new HashMap<>();
    private final Map<Integer, Integer> groupSizes = new HashMap<>();
    private int numGroups;
    private int size;

    UnionFind(int numVertices) {
        initialize(numVertices);
    }

    @Override
    public String toString() {
        return "UnionFind{" +
                "parents=" + parents +
                ", groupSizes=" + groupSizes +
                ", numGroups=" + numGroups +
                ", size=" + size +
                '}';
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

    public int find(int vertex) {
        if (parents.get(vertex) == vertex) return vertex;
        int parent = find(parents.get(vertex));
        parents.put(vertex, parent); //path compression
        return parent;
    }

    public boolean union(int vertex, int otherVertex) {
        int vertexParent = find(vertex);
        int otherVertexParent = find(otherVertex);
        int vertexGroupSize = groupSizes.get(vertexParent);
        int otherVertexGroupSize = groupSizes.get(otherVertexParent);
        if (vertexGroupSize > otherVertexGroupSize) {
            parents.put(otherVertexParent, vertexParent);
            groupSizes.remove(otherVertexParent);
            groupSizes.put(vertexParent, vertexGroupSize + otherVertexGroupSize);
        } else {
            parents.put(vertexParent, otherVertexParent);
            groupSizes.remove(vertexParent);
            groupSizes.put(otherVertexParent, vertexGroupSize + otherVertexGroupSize);
        }
        numGroups--;
        return find(vertex) == find(otherVertex);
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
    }
}
