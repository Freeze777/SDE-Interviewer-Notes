package leetcode.hard.design

/**
 * 460. LFU Cache
 * https://leetcode.com/problems/lfu-cache/
 */
class LFUCache(private val capacity: Int) {
    private val keyValueStore = HashMap<Int, Int>()
    private val keyUsageCount = HashMap<Int, Int>()
    private val usageCountToKeysLookup = HashMap<Int, LinkedHashSet<Int>>()
    private var minUsageCount = 1

    fun get(key: Int): Int {
        if (capacity == 0 || !keyValueStore.containsKey(key)) return -1
        updateKeyUsage(key)
        return keyValueStore[key]!!
    }

    fun put(key: Int, value: Int) {
        if (capacity == 0) return
        if (keyValueStore.containsKey(key)) {
            updateKeyUsage(key)
            keyValueStore[key] = value
            return
        }
        if (keyValueStore.size == capacity) evictLeastFrequentlyUsed()
        addNewKeyValue(key, value)
    }

    private fun evictLeastFrequentlyUsed() {
        val candidateKeys = usageCountToKeysLookup[minUsageCount]!!
        val evictedKey = candidateKeys.first()//lru
        candidateKeys.remove(evictedKey)
        keyValueStore.remove(evictedKey)
        keyUsageCount.remove(evictedKey)
    }

    private fun updateKeyUsage(key: Int) {
        val prevCount = keyUsageCount[key]!!
        val nextCount = prevCount + 1
        val keys = usageCountToKeysLookup[prevCount]!!
        keys.remove(key)
        if (minUsageCount == prevCount && keys.isEmpty()) minUsageCount = nextCount
        keyUsageCount[key] = nextCount
        usageCountToKeysLookup.computeIfAbsent(nextCount) { LinkedHashSet() }.add(key)
    }

    private fun addNewKeyValue(key: Int, value: Int) {
        minUsageCount = 1
        keyUsageCount[key] = 1
        usageCountToKeysLookup.computeIfAbsent(1) { LinkedHashSet() }.add(key)
        keyValueStore[key] = value
    }

    fun printState() {
        println("keyValueStore: $keyValueStore,keyUsageCount: $keyUsageCount,usageCountToKeysLookup: $usageCountToKeysLookup,minCount: $minUsageCount")
    }
}

fun main() {
    var cache = LFUCache(3)
    cache.put(1, 1)
    cache.put(2, 2)
    cache.put(3, 3)
    cache.put(4, 4)
    cache.printState()//2,3,4
    cache.get(1)
    cache.get(2)
    cache.get(3)
    cache.get(4)
    cache.printState()//2,3,4
    cache.put(4, 16)
    cache.printState()//2,3,4
    cache.put(5, 5)
    cache.printState()//3,4,5
    cache.put(6, 6)
    cache.printState()//3,4,6

    cache = LFUCache(3)
    cache.put(2, 2)
    cache.put(4, 4)
    cache.put(1, 1)
    cache.put(2, 3)
    cache.printState()//1,2,4
    cache.get(3)
    cache.get(2)
    cache.get(4)
    cache.get(1)
    cache.printState()//1,2,4
    cache.put(5, 5)
    cache.printState()//1,2,5
}
