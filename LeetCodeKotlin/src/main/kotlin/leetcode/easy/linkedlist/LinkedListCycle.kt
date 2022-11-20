package leetcode.easy.linkedlist

/**
 * 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */
class LinkedListCycle {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun hasCycle(head: ListNode?): Boolean {
        var tmp = head
        val visited = HashSet<Int>()
        while (tmp != null) {
            if (visited.contains(tmp.hashCode())) return true
            visited.add(tmp.hashCode())
            tmp = tmp.next
        }
        return false
    }

    fun hasCycleFast(head: ListNode?): Boolean {
        if (head?.next == null) return false
        var slow = head
        var fast = head
        while (fast != null) {
            fast = fast.next?.next
            slow = slow?.next
            if (fast == slow) return true
        }
        return false
    }
}

fun main() {
    val s = LinkedListCycle()
    val head = LinkedListCycle.ListNode(3)
    head.next = LinkedListCycle.ListNode(2)
    head.next!!.next = LinkedListCycle.ListNode(0)
    head.next!!.next!!.next = LinkedListCycle.ListNode(-4)
    head.next!!.next!!.next!!.next = head.next
    println(s.hasCycle(head)) // true
    println(s.hasCycleFast(head)) // true

    val head2 = LinkedListCycle.ListNode(1)
    head2.next = LinkedListCycle.ListNode(2)
    head2.next!!.next = head2
    println(s.hasCycle(head2)) // true
    println(s.hasCycleFast(head2)) // true
}