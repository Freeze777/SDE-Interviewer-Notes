from typing import List


class LinkedListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


def create_mock_linked_list(size: int) -> LinkedListNode:
    head = LinkedListNode(1)
    temp = head
    for i in range(2, size + 1):
        temp.next = LinkedListNode(i)
        temp = temp.next
    return head


def get_linked_list_nodes(head: LinkedListNode) -> List[int]:
    if head is None:
        return []
    return [head.val] + get_linked_list_nodes(head.next)
