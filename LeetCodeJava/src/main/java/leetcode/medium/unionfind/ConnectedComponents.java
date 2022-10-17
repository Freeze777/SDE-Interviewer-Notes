package leetcode.medium.unionfind;

/**
 * <a href="https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/">https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/</a>
 */
public class ConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.getNumGroups();
    }

    public static void main(String[] args) {
        ConnectedComponents cc = new ConnectedComponents();
        System.out.println(cc.countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
        System.out.println(cc.countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        System.out.println(cc.countComponents(5, new int[][]{{0, 1}, {1, 2}, {0, 2}, {3, 4}}));

    }
}
