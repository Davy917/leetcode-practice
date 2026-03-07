from typing import Optional
class ListNode:
    def __init__(self, val, next):
        self.val = val
        self.next = next

class solution2:
    def reverList_v2(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None or head.next is None:
            return head
        nextNode = head.next
        newHead = self.reverList_v2(nextNode)
        nextNode.next = head
        head.next = None
        return newHead
"""
reverseList_v2解說
未完成
"""