class LRUCache extends LinkedHashMap<Integer, Integer> {
    private final int MAX_CAPACITY;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        MAX_CAPACITY = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > MAX_CAPACITY;
    }

    @Override
    public Integer get(Object key) {
        return super.getOrDefault(key, -1);
    }
}
