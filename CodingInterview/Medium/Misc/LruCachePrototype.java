import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

class LruNode {
    public int key;
    public int val;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LruNode lruNode = (LruNode) o;
        return key == lruNode.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "LruNode{" +
                "key=" + key +
                ", val=" + val +
                '}';
    }

    public LruNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    private int maxCapacity;
    Map<Integer, LruNode> cache;
    LinkedList<LruNode> store;

    public LRUCache(int capacity) {
        cache = new HashMap<>(capacity);
        store = new LinkedList<>();
        maxCapacity = capacity;
    }

    private void moveFirst(LruNode node) {
        store.remove(node);
        store.addFirst(node);
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            var node = cache.get(key);
            moveFirst(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            var node = cache.get(key);
            node.val = value;
            moveFirst(node);
        } else {
            var node = new LruNode(key, value);
            if (cache.size() >= maxCapacity) {
                var stale = store.removeLast();
                cache.remove(stale.key);
            }
            store.addFirst(node);
            cache.put(key, node);
        }
    }

    @Override
    public String toString() {
        return "LruCache{" +
                "cache=" + cache +
                ", store=" + store +
                '}';
    }
}

public class LruCacheTest {

    public static void main(String[] args) {
        TestLru();
    }

    public static void TestLru() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));// return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));// returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));// return -1 (not found)
        System.out.println(lRUCache.get(3));// return 3
        System.out.println(lRUCache.get(4));// return 4
        lRUCache.put(4, 44);
        System.out.println(lRUCache.get(4));// return 44
    }
}
