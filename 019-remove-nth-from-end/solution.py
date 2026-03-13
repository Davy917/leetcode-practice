#雙指針
from typing import Optional
class ListNode:
    def __init__(self, val, prev, next):
        self.val = val
        self.prev = prev
        self.next = next

class LinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def AddAtTail(self, newNode):
        if self.head is None:
            print(f"Add {newNode.val} at head")
            self.head = newNode
            self.tail = newNode
            return
        newNode.prev = self.tail
        self.tail.next = newNode
        self.tail = newNode
        print(f"Add {newNode.val} at tail")

    def printList(self, head):
        if head is None:
            return
        cur = head
        nums = []
        nums.append(cur.val)
        while cur.next is not None:
            cur = cur.next
            nums.append(cur.val)
        print(nums)

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if head is None:
            return None
        dummy: Optional[ListNode] = ListNode(0, None, head)
        first = dummy
        second = dummy

        #first先移動到second + n的位置
        for i in range(0, n):
            first = first.next

        print(f"first = {first.val} " + f"second = {second.val}")

        while first.next is not None:
            first = first.next
            second = second.next
            if first is None:
                print("first is None " +  f"second = {second.val}")
                break
            print(f"first = {first.val} " + f"second = {second.val}")

        #second 會在 n 前一位
        second.next = second.next.next

        return dummy.next
    """
    ↓         ↓
    d--->1--->2--->3--->4--->5
         ↓         ↓
    d--->1--->2--->3--->4--->5
                   ↓         ↓
    d--->1--->2--->3--->4--->5
                        ↓         ↓
    d--->1--->2--->3--->4--->5--->None
    """

if __name__ == "__main__":
    nums = [1, 2, 3, 4, 5]
    n = 4
    mainList = LinkedList()
    for i in nums:
        newNode = ListNode(i, None, None)
        mainList.AddAtTail(newNode)
    mainList.head = Solution().removeNthFromEnd(mainList.head, n)
    mainList.printList(mainList.head)