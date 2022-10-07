package leetcode.medium.linkedlist;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://leetcode.com/problems/linked-list-random-node/">https://leetcode.com/problems/linked-list-random-node/</a>
 */
public class RandomNode {
    private ListNode head = null;
    private int numElements = 1;

    public RandomNode(ListNode head) {
        makeCircularList(head);
    }

    private void makeCircularList(ListNode head) {
        ListNode tail = this.head = head;
        while (tail.next != null) {
            tail = tail.next;
            numElements++;
        }
        tail.next = head;
    }

    public int getRandom() {
        int rn = ThreadLocalRandom.current().nextInt(1, numElements + 1);
        while (rn-- > 0) this.head = this.head.next;
        return this.head.val;
    }

    public static void main(String[] args) {
        RandomNode rn = new RandomNode(ListNode.createStub(10));
        for (int i = 0; i < 100; i++) {
            System.out.println(rn.getRandom());
        }
    }
}
