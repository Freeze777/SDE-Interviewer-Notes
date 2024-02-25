package leetcode.hard.unionfind;

import leetcode.medium.unionfind.UnionFind;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/greatest-common-divisor-traversal/">2709. Greatest Common Divisor Traversal</a>
 */
public class GcdTraversal {

    private Set<Integer> primeFactors(int number) {
        var factors = new HashSet<Integer>();
        for (int prime = 2; prime <= number / prime; prime++) {
            while (number % prime == 0) {
                factors.add(prime);
                number /= prime;
            }
        }
        if (number > 1) factors.add(number);
        return factors;
    }

    public boolean canTraverseAllPairs(int[] nums) {
        if (nums.length == 1) return true;
        var set = new HashSet<Integer>();
        Arrays.stream(nums).forEach(set::add);
        if (set.contains(1)) return false;
        var uniqueElements = new ArrayList<>(set);
        var primeGroups = new HashMap<Integer, List<Integer>>();
        for (var element : uniqueElements) {
            for (var prime : primeFactors(element)) {
                primeGroups.compute(prime, (k, group) -> {
                    List<Integer> list = new ArrayList<>(group != null ? group : List.of());
                    list.add(element);
                    return list;
                });
            }
        }
        var unionFind = new UnionFind(uniqueElements);
        for (var group : primeGroups.entrySet()) {
            var groupLeader = group.getValue().get(0);
            for (int i = 1; i < group.getValue().size(); i++) unionFind.union(groupLeader, group.getValue().get(i));
        }
        return unionFind.getNumGroups() == 1;
    }

    public static void main(String[] args) {
        var gcdt = new GcdTraversal();
        System.out.println(gcdt.canTraverseAllPairs(new int[]{2, 3, 6}));//true
        System.out.println(gcdt.canTraverseAllPairs(new int[]{3, 9, 5}));//false
        System.out.println(gcdt.canTraverseAllPairs(new int[]{4, 3, 12, 8}));//true
        System.out.println(gcdt.canTraverseAllPairs(new int[]{2, 3, 5, 7}));//false
        System.out.println(gcdt.canTraverseAllPairs(new int[]{3, 3, 3}));//true
        System.out.println(gcdt.canTraverseAllPairs(new int[]{3, 3, 3, 1}));//false
        System.out.println(gcdt.canTraverseAllPairs(new int[]{3, 6, 9}));//true
        System.out.println(gcdt.canTraverseAllPairs(new int[]{1}));//true
        System.out.println(gcdt.canTraverseAllPairs(new int[]{2}));//true
        System.out.println(gcdt.canTraverseAllPairs(new int[]{1, 1}));//false
        System.out.println(gcdt.canTraverseAllPairs(new int[]{42, 98, 75, 55}));//true
    }
}
