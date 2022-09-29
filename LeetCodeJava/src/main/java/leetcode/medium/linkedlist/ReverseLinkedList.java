package leetcode.medium.linkedlist;

import java.util.List;
import java.util.Stack;

public class ReverseLinkedList {
    protected static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    protected void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.print("null\n");
    }

    protected ListNode createStub(int n) {
        ListNode tmp = new ListNode(1);
        ListNode head = tmp;
        for (int i = 2; i <= n; i++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        return head;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        Stack<ListNode> s = new Stack<ListNode>();
        while (head != null) {
            s.add(head);
            head = head.next;
        }

        head = s.pop();
        ListNode temp = head;
        while (!s.isEmpty()) {
            temp.next = s.pop();
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }
}
