package leetcode.medium.misc;

/**
 * <a href="https://leetcode.com/problems/palindromic-substrings/">Palindromic Substrings</a>
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++)
            total += buildAndCountPalindromes(s, i, i) + buildAndCountPalindromes(s, i, i + 1);
        return total;
    }

    private int buildAndCountPalindromes(String s, int left, int right) {
        if (left < 0 || right >= s.length() || s.charAt(left) != s.charAt(right)) return 0;
        return 1 + buildAndCountPalindromes(s, left - 1, right + 1);
    }

    public static void main(String[] args) {
        var ps = new PalindromicSubstrings();
        System.out.println(ps.countSubstrings("abc"));//3
        System.out.println(ps.countSubstrings("aaa"));//6
        System.out.println(ps.countSubstrings("aaaabaaaa"));//25
    }
}
