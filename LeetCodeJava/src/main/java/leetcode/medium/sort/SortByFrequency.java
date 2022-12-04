package leetcode.medium.sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.com/problems/sort-characters-by-frequency/">https://leetcode.com/problems/sort-characters-by-frequency/</a>
 */
public class SortByFrequency {
    public String frequencySort(String s) {
        List<Character> letters = new ArrayList<>(s.chars().mapToObj(c -> (char) c).toList());
        Map<Character, Integer> frequency = new HashMap<>();
        for (char ch : letters) frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        letters.sort((a, b) -> Objects.equals(frequency.get(a), frequency.get(b)) ? Character.compare(a, b) : Integer.compare(frequency.get(b), frequency.get(a)));
        return letters.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        SortByFrequency sbf = new SortByFrequency();
        System.out.println(sbf.frequencySort("tree"));
        System.out.println(sbf.frequencySort("cccaaa"));
        System.out.println(sbf.frequencySort("cccaaadddddzz"));
        System.out.println(sbf.frequencySort(""));
        System.out.println(sbf.frequencySort("a"));
        System.out.println(sbf.frequencySort("loveleetcode"));
    }
}
