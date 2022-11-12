package leetcode.hard.heap;

import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/find-median-from-data-stream/description/">https://leetcode.com/problems/find-median-from-data-stream/description/</a>
 */
public class MedianFinder {
    private final PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>((a, b) -> -(a.compareTo(b)));
    private final PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

    public MedianFinder() {
    }

    private void balance() {
        if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
            rightMinHeap.add(leftMaxHeap.remove());
        }
        if (rightMinHeap.size() > leftMaxHeap.size() + 1) {
            leftMaxHeap.add(rightMinHeap.remove());
        }
    }

    public void addNum(int num) {
        if (!leftMaxHeap.isEmpty() && num >= leftMaxHeap.peek()) {
            rightMinHeap.add(num);
        } else {
            leftMaxHeap.add(num);
        }
        balance();
    }

    public double findMedian() {
        if (leftMaxHeap.size() == rightMinHeap.size()) return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
        if (leftMaxHeap.size() > rightMinHeap.size()) return leftMaxHeap.peek();
        return rightMinHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        for (int i = 0; i < 10; i++) {
            mf.addNum(i);
            System.out.println(mf.findMedian());
        }
        for (int i = -9; i < 0; i++) {
            mf.addNum(i);
            System.out.println(mf.findMedian());
        }
    }
}
