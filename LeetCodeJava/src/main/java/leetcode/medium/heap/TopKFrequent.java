package leetcode.medium.heap;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/top-k-frequent-words/">https://leetcode.com/problems/top-k-frequent-words/</a>
 */
public class TopKFrequent {

    public static int compareEntries(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
        if (Objects.equals(a.getValue(), b.getValue())) return b.getKey().compareTo(a.getKey());
        return a.getValue().compareTo(b.getValue());
    }

    public List<String> topKFrequent(String[] words, int k) {
        PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(TopKFrequent::compareEntries);
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            if (!freq.containsKey(word)) freq.put(word, 0);
            freq.put(word, freq.get(word) + 1);
        }
        int i = 0;
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (i < k) {
                priorityQueue.add(entry);
            } else {
                if (compareEntries(priorityQueue.peek(), entry) < 0) {
                    priorityQueue.poll();
                    priorityQueue.add(entry);
                }
            }
            i++;
        }
        List<String> topK = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            topK.add(priorityQueue.poll().getKey());
        }
        Collections.reverse(topK);
        return topK;
    }

    public static void main(String[] args) {
        TopKFrequent tkf = new TopKFrequent();
        System.out.println(tkf.topKFrequent(new String[]{}, 10));
        System.out.println(tkf.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(tkf.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        System.out.println(tkf.topKFrequent(new String[]{"a", "b", "c"}, 2));
        System.out.println(tkf.topKFrequent(new String[]{"a", "b", "c", "c", "b"}, 2));
    }
}
