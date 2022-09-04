"""
Find the m/n th node in a linked list

Example lets say length of the linked list L = 27

Testcases:
m=2 n=3 => Ans: 18th Node
m=1 n=9 => Ans: 3rd Node
m=3 n=9 => Ans: 9th Node
m=9 n=9 => Ans: 1st Node
m=1 n=5 => Ans: 5th Node
m=2 n=5 => Ans: 10th Node
"""

"""
My Amazon Interview Question

"""

from ProblemSolving.Medium.LinkedList.Model.LinkedListNodeModel import LinkedListNode, create_mock_linked_list, \
    get_linked_list_nodes


def m_by_n_node(head: LinkedListNode, m: int, n: int):
    from math import gcd

    m, n = m // gcd(m, n), n // gcd(m, n)
    i, temp, fractional_node = 1, head, None

    while temp is not None:
        if i % n == 0:
            if fractional_node is None:
                fractional_node = head
            else:
                fractional_node = fractional_node.next
            for i in range(m - 1):
                fractional_node = fractional_node.next
        i, temp = i + 1, temp.next

    return fractional_node


if __name__ == '__main__':
    node = create_mock_linked_list(27)
    print(get_linked_list_nodes(node))
    print(m_by_n_node(node, 2, 3).val)
    print(m_by_n_node(node, 1, 9).val)
    print(m_by_n_node(node, 3, 9).val)
    print(m_by_n_node(node, 9, 9).val)
    print(m_by_n_node(node, 1, 5).val)
    print(m_by_n_node(node, 2, 5).val)
