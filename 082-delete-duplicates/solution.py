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
    
class Solution:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy: ListNode = ListNode(0, head)#注意
        dummy.next = head
        pre = dummy
        cur = head
        while cur is not None:
            print(f"pre.val = {pre.val} " + f"cur.val = {cur.val}")
            #if pre.val != cur.val:

            cur = cur.next
            pre = pre.next
            

if __name__ == "__main__":
    nums = [1, 2, 3, 3, 4, 4, 5]
    sol = Solution()
    linkedList = linkedList()
    for i in nums:
        linkedList.addAtTail(i)
    sol.deleteDuplicates(linkedList.head)


"""
pre    cur
↓      ↓
d ---> 1 ---> 2 ---> 3 ---> 3 ---> 4 ---> 4 ---> 5

       pre    cur
       ↓      ↓
d ---> 1 ---> 2 ---> 3 ---> 3 ---> 4 ---> 4 ---> 5

              pre    cur
              ↓      ↓
d ---> 1 ---> 2 ---> 3 ---> 3 ---> 4 ---> 4 ---> 5
"""