package leetcode.medium.backtracking;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/">https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/</a>
 */
public class MaxLengthConcatenation {
    private int buildWordBitMap(String word) {
        int bitmap = 0;
        for (char ch : word.toCharArray()) {
            int bitMask = 1 << (ch - 'a');
            if ((bitmap & bitMask) != 0) return 0;
            bitmap |= bitMask;
        }
        return bitmap;
    }

    private boolean hasMatch(int word, int otherWord) {
        return (word & otherWord) != 0;
    }

    private int concat(int word, int otherWord) {
        return (word | otherWord);
    }

    public int maxLength(List<String> words) {
        List<Integer> _words = words.stream().map(this::buildWordBitMap).collect(Collectors.toList());
        return maxLengthHelper(_words, 0, 0);
    }

    public int maxLengthHelper(List<Integer> words, int start, int uniqueChars) {
        if (start == words.size()) return Integer.bitCount(uniqueChars);
        int word = words.get(start);
        int resultWithoutWord = maxLengthHelper(words, start + 1, uniqueChars);
        if (hasMatch(word, uniqueChars)) return resultWithoutWord;
        int resultWithWord = maxLengthHelper(words, start + 1, concat(word, uniqueChars));
        return Math.max(resultWithWord, resultWithoutWord);
    }

    public static void main(String[] args) {
        MaxLengthConcatenation mlc = new MaxLengthConcatenation();
        System.out.println(mlc.maxLength(Arrays.asList("a", "ab", "az")));
        System.out.println(mlc.maxLength(Arrays.asList("a", "ab", "cd")));
        System.out.println(mlc.maxLength(Arrays.asList("cha", "r", "act", "ers")));
        System.out.println(mlc.maxLength(Arrays.asList("abcdefghijklmnopqrstuvwxyz", "a")));
        System.out.println(mlc.maxLength(Arrays.asList("un", "iq", "ue")));
        System.out.println(mlc.maxLength(Arrays.asList("aa", "bb")));
    }
}
