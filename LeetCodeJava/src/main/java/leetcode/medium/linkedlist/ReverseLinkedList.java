package leetcode.medium.linkedlist;

import java.util.Stack;



public class ReverseLinkedList {
    protected void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.print("null\n");
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
