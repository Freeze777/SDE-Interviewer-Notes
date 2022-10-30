package leetcode.medium.arrays.slidingwindow;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

/**
 * <a href="https://leetcode.com/problems/minimum-window-substring/">https://leetcode.com/problems/minimum-window-substring/</a>
 */
public class MinimumWindowSubstring {
    private Map<Character, Long> getFrequencyMap(String str) {
        return str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
    }

    private boolean hasMatchingCharacters(Map<Character, Long> sourceMap, Map<Character, Long> targetMap) {
        return targetMap.keySet().stream().allMatch(ch -> sourceMap.getOrDefault(ch, 0L) >= targetMap.get(ch));
    }

    public String minWindow(String source, String target) {
        int left = 0, right = 0, minLeft = 0, minRight = source.length() - 1;
        Map<Character, Long> sMap = getFrequencyMap(source.substring(left, right + 1));
        Map<Character, Long> tMap = getFrequencyMap(target);
        while (left <= right) {
            if (hasMatchingCharacters(sMap, tMap)) {
                int window = right - left + 1;
                int minWindow = minRight - minLeft + 1;
                if (minWindow > window) {
                    minLeft = left;
                    minRight = right;
                }
                sMap.compute(source.charAt(left), (k, v) -> v - 1);
                left++;
            } else {
                right++;
                if (right >= source.length()) break;
                sMap.compute(source.charAt(right), (k, v) -> v == null ? 1 : v + 1);
            }
        }
        return left == 0 ? "" : source.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.println(mws.minWindow("abcdef", "cdfz"));//""
        System.out.println(mws.minWindow("abdc", "abc"));//"abdc"
        System.out.println(mws.minWindow("abdc", "abcd"));//"abdc"
        System.out.println(mws.minWindow("abcdef", "cdf"));//cdef
        System.out.println(mws.minWindow("abcdef", "acd"));//abcd
        System.out.println(mws.minWindow("ADOBECODEBANC", "ABC"));//BANC
        System.out.println(mws.minWindow("abc", "b")); // b
        System.out.println(mws.minWindow("cabwefgewcwaefgcf", "cae")); //cwae
    }
}
