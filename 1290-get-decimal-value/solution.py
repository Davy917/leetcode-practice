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
    def getDecimalValue(self, head: Optional[ListNode]) -> int:
        if head is None:
            return None
        cur = head
        ans = 0
        while cur is not None:
            ans = ans * 2 + cur.val
            print("ans = ", ans)
            cur = cur.next
            if cur is None:
                return ans
if __name__ == "__main__":
    nums = [1, 0, 1]
    sol = Solution1122()
    mainList = LinkedList()
    for i in nums:
        newNode = ListNode(i, None)
        mainList.AddAtTail(newNode)
    sol.getDecimalValue(mainList.head)