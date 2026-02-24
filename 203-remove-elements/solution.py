from typing import Optional
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:

    def __init__(self):
        self.head = None #為甚麼不能寫成ListNode.head = None

    def addAtTail(self, val):
        newNode = ListNode(val)

        if self.head is None:
            self.head = newNode
            return
        
        tailNode = self.head

        while tailNode is not None:
            if tailNode.next is None:
                tailNode.next = newNode
                newNode.next = None
                break
            tailNode = tailNode.next



    def removeElements(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
        pass

    def printList(self, head):
        current = head
        val = []
        while current is not None:
            val.append(current.val)
            current = current.next
        print(val)

if __name__ == "__main__":
    sol = Solution()
    nums = [1, 2, 6, 3, 4, 5, 6]
    val = 6
    for i in nums:
        sol.addAtTail(i)
    print("as is")
    sol.printList(sol.head)