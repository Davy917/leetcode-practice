from typing import Optional
class Node:
    def __init__(self, val, next, random) -> None:
        self.val = val
        self.next = next
        self.random = random
class LinkedList:
    def __init__(self):
        self.head = None
    def buildexample(self):
        newNode4 = Node(1, None, None)
        newNode3 = Node(10, newNode4, None)
        newNode2 = Node(11, newNode3, None)
        newNode1 = Node(13, newNode2, None)
        newNode0 = Node(7, newNode1, None)
        #Set random
        newNode4.random = newNode0
        newNode3.random = newNode2
        newNode2.random = newNode4
        newNode1.random = newNode0
        newNode0.random = None
        head = newNode0
        return head
      
class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if head is None:
            return None
        dummy = Node(0, head, None)
        cur = dummy
        dict = {}
        while cur.next is not None:
            cur = cur.next
            dict[cur] = Node(cur.val, None, None)
        cur = dummy
        while cur.next is not None:
            cur = cur.next
            print(cur.val)
            if cur.next is None:
                dict[cur].next = None
            else:
                print(dict[cur])
                dict[cur].next = dict[cur.next]
            if cur.random is None:
                dict[cur].random = None
            else:
                dict[cur].random = dict[cur.random]
        cur = dummy.next
        return dict[cur]

"""
[d|-]--->[7|-]--->[13|-]--->[11|-]--->[10|-]--->[1|-]--->null
"""

if __name__ == "__main__":
    sol = Solution()
    mainList = LinkedList()
    exampleNode = mainList.buildexample()
    sol.copyRandomList(exampleNode)