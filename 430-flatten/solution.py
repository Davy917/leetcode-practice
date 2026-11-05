"""
自己寫的
第一次練習看java
指針 + 廣度遍歷看python
棧 + 深度遍歷看go
"""
from typing import Optional;
class Node:
    def __init__(self, val, prev, next, child) -> None:
        self.val = val
        self.prev = prev
        self.next = next
        self.child = child

class Solution:
    def flatten(self, head: 'Optional[Node]'):
        dummy = Node(0, None, head, None)
        cur = dummy
        while cur:
            if not cur.child:
                cur = cur.next
                continue
            visitor = cur.child
            while visitor.next:
                visitor = visitor.next
            if cur.next:
                cur.next.prev = visitor
            visitor.next = cur.next
            cur.child.prev = cur
            cur.next = cur.child
            cur.child = None
            cur = cur.next
        return dummy.next