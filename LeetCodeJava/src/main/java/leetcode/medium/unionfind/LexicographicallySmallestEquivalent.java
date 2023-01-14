package leetcode.medium.unionfind;

import java.util.TreeSet;

/**
 * <a href="https://leetcode.com/problems/lexicographically-smallest-equivalent-string/">1061. Lexicographically Smallest Equivalent String</a>
 */
public class LexicographicallySmallestEquivalent {
    public String smallestEquivalentString(String a, String b, String c) {
        var uf = new UnionFind(26);
        var ans = new StringBuilder();
        for (int i = 0; i < a.length(); i++) uf.union(a.charAt(i) - 'a', b.charAt(i) - 'a');
        var groupByParent = uf.getGroupsByParent(true);
        for (int i = 0; i < c.length(); i++) {
            var parent = uf.find(c.charAt(i) - 'a');
            var group = (TreeSet<Integer>) groupByParent.get(parent);
            ans.append((char) ('a' + group.first()));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        var lse = new LexicographicallySmallestEquivalent();
        System.out.println(lse.smallestEquivalentString("abc", "cde", "aab"));//aab
        System.out.println(lse.smallestEquivalentString("abc", "cde", "xyz"));//xyz
        System.out.println(lse.smallestEquivalentString("parker", "morris", "parser"));//makkek
        System.out.println(lse.smallestEquivalentString("hello", "world", "hold"));//hdld
        System.out.println(lse.smallestEquivalentString("leetcode", "programs", "sourcecode"));//aauaaaaada
    }
}
