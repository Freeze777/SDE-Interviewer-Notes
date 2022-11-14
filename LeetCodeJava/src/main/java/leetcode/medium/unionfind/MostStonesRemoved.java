package leetcode.medium.unionfind;


import java.util.*;

/**
 * <a href="https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/">https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/</a>
 */
public class MostStonesRemoved {

    private void removeStonesByUnion(UnionFind uf, Map<Integer, List<Integer>> connectedStones) {
        for (List<Integer> stones : connectedStones.values()) {
            for (int i = 0; i < stones.size() - 1; i++) {
                uf.union(stones.get(i), stones.get(i + 1));
            }
        }
    }

    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind(stones.length);
        Map<Integer, List<Integer>> rowStones = new HashMap<>();
        Map<Integer, List<Integer>> colStones = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            int row = stones[i][0];
            int col = stones[i][1];
            rowStones.putIfAbsent(row, new ArrayList<>());
            colStones.putIfAbsent(col, new ArrayList<>());
            rowStones.get(row).add(i);
            colStones.get(col).add(i);
        }
        removeStonesByUnion(uf, rowStones);
        removeStonesByUnion(uf, colStones);
        return uf.getGroups().stream().mapToInt(Set::size).sum() - uf.getNumGroups();
    }

    public static void main(String[] args) {
        MostStonesRemoved msr = new MostStonesRemoved();
        System.out.println(msr.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
        System.out.println(msr.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));
        System.out.println(msr.removeStones(new int[][]{{0, 0}}));
        System.out.println(msr.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}}));
    }

}
