#遞迴寫法，迭代寫法見java
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
    def printList(self, head):
        if self.head is None:
            return head
        nums = []
        nums.append(head.val)
        cur = head
        while cur.next is not None:
            cur = cur.next
            nums.append(cur.val)
        print(nums)
        
class Solution1122:
    def __init__(self) -> None:
        self.layer = 1
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        print("layer = ", self.layer)#debug

        if not head or not head.next:
            print("head or head.next is None, break")
            return head
        newHead = head.next
        self.layer += 1#debug
        print("head = ", head.val)#debug
        print("newHead = ", newHead.val)#debug
        head.next = self.swapPairs(newHead.next)
        newHead.next = head
        return newHead
"""
初始化:
head
  |
  V 
[1] -> [2] -> [3] -> [4] -> None

第一層swapPairs
newHead = head.next
head   newHead
  |      |
  V      V
[1] -> [2] -> [3] -> [4] -> None

head.next = self.swapPairs(newHead.next)
head   newHead
  |      |
  V      V
[1] -> [2] -> [3] -> [4] -> None
                              ^
                              |
                              等待 swapPairs(ListNode(3)) 的返回值
第二層swapPairs:
newHead = head.next
(遞迴中的 head) (遞迴中的 newHead)
           |                  |
           V                  V
         [3] ->             [4] -> None
        
head.next = self.swapPairs(newHead.next)
(遞迴中的 head) (遞迴中的 newHead)
           |                  |
           V                  V
         [3] ->             [4] -> None
                                    ^
                                    |
                                    等待 swapPairs(None) 的返回值
第三層swapPairs:
if not head or not head.next:
    return head
    
返回第二層swapPairs:
head.next = self.swapPairs(newHead.next)
(遞迴中的 head) (遞迴中的 newHead)
           |                  |
           V                  V
         [3] -> None        [4] -> None  (注意：此時 4 的 next 仍然指向 None，它沒有被改變)
         
head.next = None
(遞迴中的 head) (遞迴中的 newHead)
           |                  |
           V                  V
         [3] <-             [4] -> None
           ^                  |
           |                  |
           -------------------

newHead.next = head
(遞迴中的 head) (遞迴中的 newHead)
           |                  |
           V                  V
         [3] <-             [4] -> None
           ^                  |
           |                  |
           -------------------
           
現在 4 -> 3 -> None 這段鏈結串列已經形成。  

return newHead
返回第一層swapPairs:
返回 newHead，也就是 ListNode(4)。


返回第一層swapPairs:
head.next = self.swapPairs(newHead.next)
(原始 head) (原始 newHead)
         |             |
         V             V
       [1] ->        [2]
         ^             |
         |             |
         ---------------------------------> [4] -> [3] -> None
         
head.next = ListNode(4)
newHead.next = head
(原始 head) (原始 newHead)
         |             |
         V             V
       [1] <-        [2]
         ^             |
         |             |
         ---------------------------------> [4] -> [3] -> None
         ^                                  |
         |----------------------------------
現在 2 -> 1 -> 4 -> 3 -> None 這段鏈結串列已經形成

return newHead  
返回 newHead，也就是 ListNode(2)。
"""

if __name__ == "__main__":
    nums = [1, 2, 3, 4]
    sol = Solution1122()
    mainList = LinkedList()
    for i in nums:
        newNode = ListNode(i, None)
        mainList.AddAtTail(newNode)
    sol.swapPairs(mainList.head)