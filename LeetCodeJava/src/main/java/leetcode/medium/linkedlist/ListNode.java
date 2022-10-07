package leetcode.medium.linkedlist;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode createStub(int n) {
        ListNode tmp = new ListNode(1);
        ListNode head = tmp;
        for (int i = 2; i <= n; i++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        return head;
    }
}
