package leetcode.medium.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://leetcode.com/problems/insert-delete-getrandom-o1/description/">https://leetcode.com/problems/insert-delete-getrandom-o1/description/</a>
 */
public class RandomizedSet {
    private final Map<Integer, Integer> lookup = new HashMap<>();
    private final ArrayList<Integer> randomAccess = new ArrayList<>();

    public RandomizedSet() {
    }

    public boolean insert(int val) {
        if (lookup.containsKey(val)) return false;
        randomAccess.add(val);
        int index = randomAccess.size() - 1;
        lookup.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if (!lookup.containsKey(val)) return false;
        int currentIndex = lookup.get(val);
        int lastIndex = lookup.size() - 1;
        int lastElement = randomAccess.get(lastIndex);
        randomAccess.set(currentIndex, lastElement);
        randomAccess.remove(lastIndex);
        lookup.put(lastElement, currentIndex);
        lookup.remove(val);
        return true;
    }

    public int getRandom() {
        int random = ThreadLocalRandom.current().nextInt(randomAccess.size());
        return randomAccess.get(random);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(2));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.insert(3));
        System.out.println(randomizedSet.insert(4));
        System.out.println(randomizedSet.insert(5));
        System.out.println(randomizedSet.remove(5));
        System.out.println(randomizedSet.remove(3));
        System.out.println(randomizedSet.lookup + "::" + randomizedSet.randomAccess);
    }
}
