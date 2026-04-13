#快慢指針
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

class Solution1122:
    def middleNode(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None
        fast = head
        slow = head
        while fast.next is not None and fast.next.next is not None:
            fast = fast.next.next
            slow = slow.next
            print("fast = ", fast.val)
            print("slow", slow.val)

        if fast.next is None:
            print("ans = ", slow.val)
            return slow
        else:
            slow = slow.next
            print("ans = ", slow.val)
            return slow

if __name__ == "__main__":
    nums = [1,2]
    sol = Solution1122()
    mainList = LinkedList()
    for i in nums:
        newNode = ListNode(i, None)
        mainList.AddAtTail(newNode)
    sol.middleNode(mainList.head)
        