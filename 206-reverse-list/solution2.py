#遞歸寫法用 solution.py 下去改的
from typing import Optional

class ListNode:
    def __init__(self, val, next) -> None:
        self.val = val
        self.next = next

class LinkedList:
    def __init__(self) -> None:
        self.head = None
        
    def addAtTail(self, val):
        newNode = ListNode(val, None)
        if self.head is None:
            self.head = newNode     
            print(f"head.val = {newNode.val}")
            return
        current = self.head
        while(current.next is not None):
            current = current.next
        current.next = newNode

    def printList(self, head):
        if head is None:
            return
        visitor = head
        nums = []
        nums.append(visitor.val)
        while visitor.next is not None:
            visitor = visitor.next
            nums.append(visitor.val)
        print(nums)
        return
    
class Solution:
    def reverse(self, current: Optional[ListNode], pre: Optional[ListNode]) -> Optional[ListNode]:
        LinkedList().printList(pre)
        if (current is None):
            return pre
        temp = current.next
        current.next = pre
        return self.reverse(temp, current)#注意
    
    def reverseList_v2(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None or head.next is None:
            return head
        return self.reverse(head, None)
    
if __name__ == "__main__":
    nums = [1, 2, 3, 4, 5, 6]
    list = LinkedList()
    sol = Solution()
    for i in nums:
        list.addAtTail(i)
    sol.reverseList_v2(list.head)