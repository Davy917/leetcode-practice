from typing import Optional
class ListNode:
    def __init__(self, val, next) -> None:
        self.val = val
        self.next = next
        
class LinkedList:
    def __init__(self) -> None:
        self.head = None
    def AddAtTail(self, newNode):
        if self.head is None:
            self.head = newNode
            return
        cur = self.head
        while cur.next is not None:
            cur = cur.next
        cur.next = newNode

class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None
        pass

if __name__ == "__main__":
    nums = [2,1,3,5,6,4,7]
    sol = Solution()
    mainList = LinkedList()
    for i in nums:
        newNode = ListNode(i, None)
        mainList.AddAtTail(newNode)

"""
[2,3,6,7,1,5,4]

[2|-]--->[3|-]--->[6|-]--->[7|-]--->[1|-]--->[5|-]--->[4|-]
"""