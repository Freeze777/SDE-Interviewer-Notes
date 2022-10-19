package leetcode.medium.greedy;

import java.util.Map;
import java.util.TreeMap;

/**
 * <a href="https://leetcode.com/problems/hand-of-straights/">https://leetcode.com/problems/hand-of-straights</a>
 * <p>
 * <a href="https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/">https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/</a>
 */
public class HandOfStraights {

    public boolean isNStraightHand(int[] hands, int groupSize) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Integer hand : hands) {
            map.compute(hand, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Integer currentHand : map.keySet()) {
            Integer numCards = map.get(currentHand);
            if (numCards == 0) continue;
            for (int i = 1; i < groupSize; i++) {
                Integer nextHand = currentHand + i;
                if (!map.containsKey(nextHand)) return false;
                Integer numNextHandCards = map.get(nextHand);
                if (numNextHandCards < numCards) return false;
                map.put(nextHand, numNextHandCards - numCards);
            }
            map.put(currentHand, 0);
        }
        return map.values().stream().allMatch(v -> v == 0);
    }

    public static void main(String[] args) {
        HandOfStraights hos = new HandOfStraights();
        System.out.println(hos.isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        System.out.println(hos.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(hos.isNStraightHand(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
        System.out.println(hos.isNStraightHand(new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
        System.out.println(hos.isNStraightHand(new int[]{1, 2, 3, 4}, 3));
    }
}
