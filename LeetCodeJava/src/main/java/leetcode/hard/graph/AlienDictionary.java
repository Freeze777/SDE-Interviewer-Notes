package leetcode.hard.graph;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/alien-dictionary/description/">https://leetcode.com/problems/alien-dictionary/description/</a>
 */
public class AlienDictionary {

    private final Character loopMarker = Character.MIN_VALUE;

    private void dfs(Character character, Map<Character, List<Character>> adjList, Set<Character> completed, Set<Character> discovered, StringBuilder order) {
        if (completed.contains(character)) return;
        discovered.add(character);
        for (Character nextChar : adjList.getOrDefault(character, Collections.emptyList())) {
            if (completed.contains(nextChar)) continue;
            if (discovered.contains(nextChar)) {
                completed.add(loopMarker);
                return;
            }
            dfs(nextChar, adjList, completed, discovered, order);
        }
        completed.add(character);
        order.append(character);
    }

    private static List<Character> getOrdering(String w1, String w2) {
        for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
            if (w1.charAt(j) != w2.charAt(j)) {
                return Arrays.asList(w1.charAt(j), w2.charAt(j));
            }
        }
        return new ArrayList<>();
    }

    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adjList = new HashMap<>();
        for (String word : words)
            for (Character c : word.toCharArray())
                adjList.put(c, new ArrayList<>()); // make sure every letter is part of the ordering

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            //prefix should always be first, else no ordering
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            List<Character> pair = getOrdering(w1, w2);
            if (!pair.isEmpty()) {
                adjList.compute(pair.get(0), (k, v) -> {
                    if (v == null) return new ArrayList<>(Collections.singletonList(pair.get(1)));
                    v.add(pair.get(1));
                    return v;
                });
            }
        }
        Set<Character> completed = new HashSet<>();
        Set<Character> discovered = new HashSet<>();
        StringBuilder order = new StringBuilder();
        for (Character character : adjList.keySet()) {
            dfs(character, adjList, completed, discovered, order);
        }
        order.reverse();
        return completed.contains(loopMarker) ? "" : order.toString();
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        System.out.println(ad.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(ad.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt", "xyz"}));
        System.out.println(ad.alienOrder(new String[]{"z", "x"}));
        System.out.println(ad.alienOrder(new String[]{"z", "x", "z"}));
        System.out.println(ad.alienOrder(new String[]{"wrt", "wrf", "xy", "er", "ett", "rftt", "xyz"}));
        System.out.println(ad.alienOrder(new String[]{"z", "z"}));
    }
}
