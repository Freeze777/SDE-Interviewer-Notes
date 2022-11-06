package leetcode.easy.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/is-subsequence/description/">https://leetcode.com/problems/is-subsequence/description/</a>
 */
public class IsSubsequence {

    public int isSubsequence(String main, int i, String sub, int j, int[][] cache) {
        if (i >= main.length() || j >= sub.length()) return 0;
        if (cache[i][j] != -1) return cache[i][j];
        int result = 0;
        if (main.charAt(i) == sub.charAt(j)) {
            if (j == sub.length() - 1) result = 1;
            else result = isSubsequence(main, i + 1, sub, j + 1, cache);
        }
        cache[i][j] = result <= 0 ? isSubsequence(main, i + 1, sub, j, cache) : result;
        return cache[i][j];
    }

    public boolean isSubsequence(String sub, String main) {
        if (sub.isEmpty()) return true;
        int[][] cache = new int[main.length()][sub.length()];
        for (final int[] line : cache) Arrays.fill(line, -1);
        return isSubsequence(main, 0, sub, 0, cache) == 1;
    }

    public static void main(String[] args) {
        IsSubsequence is = new IsSubsequence();
        System.out.println(is.isSubsequence("abc", "ahbgdc"));
        System.out.println(is.isSubsequence("axc", "ahbgdc"));
        System.out.println(is.isSubsequence("axc", "ahbgdcxfiowetuioewc"));
        System.out.println(is.isSubsequence("abc", "nmajkhobqwertyucp"));
        System.out.println(is.isSubsequence("abc", "najbkcp"));
        System.out.println(is.isSubsequence("", "abcdef"));
    }

}
