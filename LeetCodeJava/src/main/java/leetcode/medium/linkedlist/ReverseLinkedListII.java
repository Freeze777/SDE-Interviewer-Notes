package leetcode.medium.linkedlist;

public class ReverseLinkedListII extends ReverseLinkedList {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;

        ListNode rTemp = head;
        while (--right > 0) rTemp = rTemp.next;
        ListNode rTail = rTemp.next;
        rTemp.next = null;

        if (left == 1) {
            ListNode newHead = reverseList(head);
            head.next = rTail;
            return newHead;
        }

        ListNode ltemp = head;
        while (--left > 1) ltemp = ltemp.next;
        ListNode lstart = ltemp.next;
        ltemp.next = reverseList(lstart);
        lstart.next = rTail;
        return head;
    }

    public static void main(String[] args) {
        ReverseLinkedListII test = new ReverseLinkedListII();
        test.printList(test.reverseBetween(test.createStub(5), 1, 5));
        test.printList(test.reverseBetween(test.createStub(5), 1, 4));
        test.printList(test.reverseBetween(test.createStub(5), 2, 4));
        test.printList(test.reverseBetween(test.createStub(5), 2, 5));
        test.printList(test.reverseBetween(test.createStub(5), 3, 4));

    }
}
