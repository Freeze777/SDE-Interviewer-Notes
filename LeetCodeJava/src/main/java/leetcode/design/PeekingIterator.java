package leetcode.design;


import java.util.*;

/**
 * <a href="https://leetcode.com/problems/peeking-iterator/">284. Peeking Iterator</a>
 */
public class PeekingIterator implements Iterator<Integer> {
    final Iterator<Integer> iterator;
    final LinkedList<Integer> queue;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        this.queue = new LinkedList<>();
    }

    public Integer peek() {
        Integer element = next();
        queue.offer(element);
        return element;
    }

    @Override
    public Integer next() {
        if (queue.isEmpty()) {
            return iterator.next();
        }
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext() || !queue.isEmpty();
    }

    public static void main(String[] args) {
        PeekingIterator peekingIterator = new PeekingIterator(Arrays.asList(1, 2, 3).listIterator()); // [1,2,3]
        System.out.println(peekingIterator.next());    // return 1
        System.out.println(peekingIterator.peek());    // return 2
        System.out.println(peekingIterator.next());    // return 2
        System.out.println(peekingIterator.next());    // return 3
        System.out.println(peekingIterator.hasNext()); // return False

        PeekingIterator peekingIterator1 = new PeekingIterator(Arrays.asList(1, 2, 3, 9, 10, 99).listIterator());// [1,2,3,9,10,99]
        System.out.println(peekingIterator1.peek()); //return 1
        System.out.println(peekingIterator1.peek()); //return 1
        System.out.println(peekingIterator1.peek()); //return 1
        System.out.println(peekingIterator1.next()); //return 1
        System.out.println(peekingIterator1.peek()); //return 2
        System.out.println(peekingIterator1.next()); //return 2
    }
}
