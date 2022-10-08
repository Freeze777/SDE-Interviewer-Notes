package leetcode.medium.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/detonate-the-maximum-bombs/">https://leetcode.com/problems/detonate-the-maximum-bombs/</a>
 */
public class DetonateMaxBombs {
    private boolean isDetonated(int[] potentialBomb, int[] explodedBomb) {
        long x = potentialBomb[0], y = potentialBomb[1];
        long a = explodedBomb[0], b = explodedBomb[1], r = explodedBomb[2];
        return (x - a) * (x - a) + (y - b) * (y - b) <= r * r;
    }

    private Set<Integer> detonate(int starterBomb, int[][] bombs) {
        Set<Integer> exploded = new HashSet<>();
        Queue<Integer> bfsQ = new LinkedList<>();
        bfsQ.add(starterBomb);
        while (!bfsQ.isEmpty()) {
            int detonatedBomb = bfsQ.remove();
            exploded.add(detonatedBomb);
            for (int bomb = 0; bomb < bombs.length; bomb++) {
                if (exploded.contains(bomb)) continue;
                if (isDetonated(bombs[bomb], bombs[detonatedBomb])) {
                    bfsQ.add(bomb);
                }
            }
        }
        return exploded;
    }

    public int maximumDetonation(int[][] bombs) {
        Set<Integer> totalDetonations = new HashSet<>();
        int maxDetonations = Integer.MIN_VALUE;
        for (int bomb = 0; totalDetonations.size() < bombs.length; bomb++) {
            if (totalDetonations.contains(bomb)) continue;
            Set<Integer> explodedBombs = detonate(bomb, bombs);
            maxDetonations = Math.max(maxDetonations, explodedBombs.size());
            totalDetonations.addAll(explodedBombs);
        }
        return maxDetonations;
    }

    public static void main(String[] args) {
        DetonateMaxBombs dmb = new DetonateMaxBombs();
        System.out.println(dmb.maximumDetonation(new int[][]{{2, 1, 3}, {6, 1, 4}})); //2
        System.out.println(dmb.maximumDetonation(new int[][]{{1, 1, 5}, {10, 10, 5}})); //1
        System.out.println(dmb.maximumDetonation(new int[][]{{1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}})); //5
        // 11
        System.out.println(dmb.maximumDetonation(new int[][]{{6, 874, 154}, {214, 633, 233}, {786, 52, 144}, {62, 561, 134}, {643, 144, 17}, {609, 578, 432}, {553, 548, 433}, {237, 992, 472}, {16, 588, 323}, {984, 826, 50}, {210, 694, 143}, {758, 74, 24}, {363, 173, 116}, {121, 741, 332}, {274, 97, 147}}));
    }
}
