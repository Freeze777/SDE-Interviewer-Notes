package leetcode.medium.linkedlist

/**
 * 142. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 */
class LinkedListCycleII {
    data class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    private fun loopIntersection(head: ListNode?): ListNode? {
        var slow = head
        var fast = head
        do {
            slow = slow?.next
            fast = fast?.next?.next
        } while (slow != fast)
        return slow
    }

    fun detectCycle(head: ListNode?): ListNode? {
        val node = loopIntersection(head) ?: return null
        var slow = head
        var fast = node
        while (slow != fast) {
            slow = slow?.next
            fast = fast.next!!
        }
        return slow
    }
}

fun main() {
    val llc = LinkedListCycleII()
    val head = LinkedListCycleII.ListNode(3)
    head.next = LinkedListCycleII.ListNode(2)
    head.next?.next = LinkedListCycleII.ListNode(0)
    head.next?.next?.next = LinkedListCycleII.ListNode(-4)
    head.next?.next?.next?.next = head.next
    println(llc.detectCycle(head)?.`val`)// 2
    val head2 = LinkedListCycleII.ListNode(1)
    head2.next = LinkedListCycleII.ListNode(2)
    head2.next?.next = head2
    println(llc.detectCycle(head2)?.`val`)// 1
    val head3 = LinkedListCycleII.ListNode(1)
    println(llc.detectCycle(head3))// null
}