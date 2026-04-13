from typing import Optional
#雙指針
class ListNode:
    def __init__(self, val, prev, next):
        self.val = val
        self.prev = prev
        self.next = next

class LinkedList:
    def __init__(self):
        self.head = None
        self.tail = None
        self.dummy: Optional[ListNode] = ListNode(0, None, self.head)

    def addAtTail(self, ListNode):
        newNode = ListNode
        if self.head is None:
            self.head = newNode
            self.tail = newNode
            return
        newNode.prev = self.tail
        self.tail.next = newNode
        self.tail = newNode
        print(f"add {newNode.val} at tail")
        return

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

class Solution1122:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        if head is None or head.next is None:
            return False
        slow = head
        fast = head
        while fast is not None and fast.next is not None:
            print(f"fast.val = {fast.val} " + f"slow.val = {slow.val}")
            slow = slow.next
            fast = fast.next.next
            if fast == slow:
                print("find loop")
                return True
            
        print("not find loop")
        return False
    
if __name__ == "__main__":
    nums = [3, 2, 0, -4]
    pos = 1
    mainList = LinkedList()
    for i in nums:
        newNode = ListNode(i, None, None)
        mainList.addAtTail(newNode)
        
    #手動製造環形鏈表
    mainList.tail.next = mainList.head.next
    #LinkedList.printList(mainList.head)
    Solution1122().hasCycle(mainList.head)