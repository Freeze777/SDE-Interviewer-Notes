package leetcode.design;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/design-hashmap/">706. Design HashMap</a>
 */
public class MyHashMap {

    private static class Entry {
        Integer key, value;

        Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            return Objects.equals(this.key, ((Entry) o).key);
        }
    }

    private final int INIT_CAPACITY = 1 << 13;
    @SuppressWarnings("unchecked")
    private final LinkedList<Entry>[] buckets = new LinkedList[INIT_CAPACITY];

    public MyHashMap() {
    }

    public void put(Integer key, Integer value) {
        var entry = getEntry(key);
        if (entry.isEmpty()) getBucket(key).add(new Entry(key, value));
        else entry.get().value = value;
    }

    public Integer get(Integer key) {
        return getEntry(key).map(kvEntry -> kvEntry.value).orElse(-1);
    }

    public void remove(Integer key) {
        var entry = getEntry(key);
        if (entry.isEmpty()) return;
        getBucket(key).remove(entry.get());
    }

    private int bucketIndex(Integer key) {
        return key.hashCode() & (buckets.length - 1);
    }

    private Optional<Entry> getEntry(Integer key) {
        for (Entry entry : getBucket(key)) {
            if (entry.key.equals(key)) return Optional.of(entry);
        }
        return Optional.empty();
    }

    private LinkedList<Entry> getBucket(Integer key) {
        if (buckets[bucketIndex(key)] == null) buckets[bucketIndex(key)] = new LinkedList<>();
        return buckets[bucketIndex(key)];
    }

    public static void main(String[] args) {
        var mp = new MyHashMap();
        System.out.println(mp.get(1));//-1
        mp.put(1, 100);
        System.out.println(mp.get(1));//100
        mp.put(1, 99);
        System.out.println(mp.get(1));//99
        mp.remove(1);
        System.out.println(mp.get(1));//-1
        mp.put(2, 200);
        mp.put(200, 91);
        mp.put(9087, 87654);
        System.out.println(mp.get(2));//200
        System.out.println(mp.get(200));//91
        System.out.println(mp.get(9087));//87654
        System.out.println(mp.get(99999));//-1
    }
}
