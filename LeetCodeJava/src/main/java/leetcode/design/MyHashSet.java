package leetcode.design;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/design-hashset/">705. Design HashSet</a>
 */
class MyGenericHashSet<K> {
    final int INIT_CAPACITY = 1 << 13;
    @SuppressWarnings("unchecked")
    final LinkedList<K>[] buckets = new LinkedList[INIT_CAPACITY];

    public MyGenericHashSet() {
    }

    public void add(K key) {
        if (!contains(key)) getBucket(key).add(key);
    }

    public void remove(K key) {
        getBucket(key).remove(key);
    }

    public boolean contains(K key) {
        return getBucket(key).contains(key);
    }

    private int getBucketId(K key) {
        return key.hashCode() & (buckets.length - 1);
    }

    private LinkedList<K> getBucket(K key) {
        if (buckets[getBucketId(key)] == null) buckets[getBucketId(key)] = new LinkedList<>();
        return buckets[getBucketId(key)];
    }
}

public class MyHashSet extends MyGenericHashSet<Integer> {
    public static void main(String[] args) {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));    // returns true
        System.out.println(hashSet.contains(3));    // returns false (not found)
        System.out.println(hashSet.contains(2));    // returns true
        hashSet.add(2);
        System.out.println(hashSet.contains(2));    // returns true
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));    // returns false (already removed)
    }
}
