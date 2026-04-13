#一次遍歷，不需要使用額外空間
from typing import Optional
class ListNode:
    def __init__(self, val, next):
        self.val = val
        self.next = next

class linkedList:
    def __init__(self):

        self.head = None #注意
        self.tail = None #注意

    def addAtTail(self, val):
        newNode = ListNode(val, None)
        if self.head is None:
            self.head = newNode
            return
        current = self.head
        while current.next is not None:
            current = current.next
        current.next = newNode
        return self.head
    def printList(self, head):
        if(head is None):
            return
        current = head
        nums = []
        nums.append(head.val)
        while current.next is not None:
            current = current.next
            nums.append(current.val)
        print(nums)

class Solution1122:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:

        if head is None or head.next is None:
            return head
        
        dummy: ListNode = ListNode(0, head)#注意
        cur = dummy
        temp:int = -101

        #此迴圈註定無法處理尾節點
        while cur.next is not None and cur.next.next is not None:
            if cur.next.val == cur.next.next.val or cur.next.val == temp:
                print(f"cur.next.val = {cur.next.val}" + f" cur.next.next.val = {cur.next.next.val}")
                temp = cur.next.val #先用temp把要刪除的節點的值存下來
                print("temp = ", temp)
                cur.next = cur.next.next #注意 cur 仍然指向原節點，但其 next 指針指向了新的節點
                linkedList.printList(dummy)
            else:
                cur = cur.next

        #處理尾節點
        if cur.next.val == temp:
            cur.next = None
            
        return dummy.next # 注意：回傳新頭，不是舊 head

if __name__ == "__main__":
    #nums = [1, 2, 3, 3, 4, 4, 5]
    nums = [2, 2, 2, 3]
    sol = Solution1122()
    linkedList = linkedList()
    for i in nums:
        linkedList.addAtTail(i)

    # 注意：把新頭接回去
    linkedList.head = sol.deleteDuplicates(linkedList.head)
    linkedList.printList(linkedList.head)
    
"""
cur    next   next.next
↓      ↓      ↓
d ---> 1 ---> 2 ---> 3 ---> 3 ---> 4 ---> 4 ---> 5

       cur    next   next.next
       ↓      ↓      ↓
d ---> 1 ---> 2 ---> 3 ---> 3 ---> 4 ---> 4 ---> 5

                     next   next.next
                     ↓      ↓
d ---> 1 ---> 2 ---> 3 ---> 3 ---> 4 ---> 4 ---> 5

                     next   next.next
                     ↓      ↓
d ---> 1 ---> 2 ---> 3 ---> 4 ---> 4 ---> 5

                     next   next.next
                     ↓      ↓
d ---> 1 ---> 2 ---> 4 ---> 4 ---> 5

                     next   next.next
                     ↓      ↓
d ---> 1 ---> 2 ---> 4 ---> 5

                      next
                      ↓
d ---> 1 ---> 2  ---> 5

迴圈走到這邊會踩到判定條件而無法繼續運行
while cur.next is not None and cur.next.next is not None:

所以才需要再補這段
if cur.next.val == temp:
    cur.next = None
"""