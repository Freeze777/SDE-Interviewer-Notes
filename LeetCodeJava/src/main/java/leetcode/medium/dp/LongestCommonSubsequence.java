package leetcode.medium.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/longest-common-subsequence/">https://leetcode.com/problems/longest-common-subsequence/</a>
 */
public class LongestCommonSubsequence {

    private int longestCommonSubsequence(String s, int i, String t, int j, int[][] cache) {
        if (i >= s.length() || j >= t.length()) return 0;
        if (cache[i][j] != -1) return cache[i][j];
        if (s.charAt(i) == t.charAt(j)) return (cache[i][j] = 1 + longestCommonSubsequence(s, i + 1, t, j + 1, cache));
        int moveBoth = longestCommonSubsequence(s, i + 1, t, j + 1, cache);
        int moveS = longestCommonSubsequence(s, i + 1, t, j, cache);
        int moveT = longestCommonSubsequence(s, i, t, j + 1, cache);
        return (cache[i][j] = Math.max(moveS, Math.max(moveT, moveBoth)));
    }

    public int longestCommonSubsequence(String s, String t) {
        int[][] cache = new int[s.length()][t.length()];
        for (int[] dp : cache) Arrays.fill(dp, -1);
        return longestCommonSubsequence(s, 0, t, 0, cache);
    }

    public static void main(String[] args) {
        var lcs = new LongestCommonSubsequence();
        System.out.println(lcs.longestCommonSubsequence("abcde", "ace"));//3
        System.out.println(lcs.longestCommonSubsequence("abc", "abc"));//3
        System.out.println(lcs.longestCommonSubsequence("abc", "def"));//0
        System.out.println(lcs.longestCommonSubsequence("bsbininm", "jmjkbkjkv"));//1
        System.out.println(lcs.longestCommonSubsequence("oxcpqrsvwf", "shmtulqrypy"));//2
    }
}
