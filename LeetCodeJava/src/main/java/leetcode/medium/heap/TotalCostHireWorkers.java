package leetcode.medium.heap;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/total-cost-to-hire-k-workers/description/">https://leetcode.com/problems/total-cost-to-hire-k-workers/description/</a>
 */
public class TotalCostHireWorkers {

    private static class Candidate implements Comparable<Candidate> {
        int hireCost;
        int index;
        boolean isLeft;

        public Candidate(int hireCost, int index, boolean isLeft) {
            this.hireCost = hireCost;
            this.index = index;
            this.isLeft = isLeft;
        }

        @Override
        public int compareTo(Candidate o) {
            if (this.hireCost == o.hireCost) return Integer.compare(this.index, o.index);
            return Integer.compare(this.hireCost, o.hireCost);
        }
    }

    public long totalCost(int[] costs, int k, int candidates) {
        Set<Integer> candidatePool = new HashSet<>();
        Queue<Candidate> minHeap = new PriorityQueue<>();

        int left = candidates - 1;
        for (int i = 0; i < candidates; i++) {
            minHeap.add(new Candidate(costs[i], i, true));
            candidatePool.add(i);
        }

        int right = costs.length - candidates;
        for (int i = costs.length - 1; i >= costs.length - candidates; i--) {
            if (!candidatePool.contains(i)) {
                minHeap.add(new Candidate(costs[i], i, false));
                candidatePool.add(i);
            }
        }

        long totalHireCost = 0;
        for (int i = 0; i < k; i++) {
            Candidate hired = minHeap.remove();
            totalHireCost += hired.hireCost;
            int nextCandidate;
            if (hired.isLeft) {
                nextCandidate = left + 1;
                left++;
            } else {
                nextCandidate = right - 1;
                right--;
            }
            if (nextCandidate < 0 || nextCandidate >= costs.length) continue;
            if (!candidatePool.contains(nextCandidate)) {
                minHeap.add(new Candidate(costs[nextCandidate], nextCandidate, hired.isLeft));
                candidatePool.add(nextCandidate);
            }
        }
        return totalHireCost;
    }

    public static void main(String[] args) {
        TotalCostHireWorkers s = new TotalCostHireWorkers();
        System.out.println(s.totalCost(new int[]{17, 12, 10, 2, 7, 2, 11, 20, 8}, 3, 4));
        System.out.println(s.totalCost(new int[]{17, 12, 10, 2, 100, 7, 2, 11, 20, 8}, 3, 4));
        System.out.println(s.totalCost(new int[]{17, 12, 10, 2, 7, 2, 11, 20, 8}, 9, 4));
        System.out.println(s.totalCost(new int[]{1, 2, 4, 1}, 3, 3));
        System.out.println(s.totalCost(new int[]{31, 25, 72, 79, 74, 65, 84, 91, 18, 59, 27, 9, 81, 33, 17, 58}, 11, 2));//423
    }
}
