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
        tailNode = self.head

        if tailNode is None:
            tailNode = newNode
            return
        
        while tailNode is not None:
            if tailNode.next is None:
                tailNode.next = newNode
                break
            tailNode = tailNode.next

    def removeElements(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
        pass

    def printList(self, head):
        current = self.head
        print("[")
        while current is not None:
            print(current.val)
            if(current.next is not None):
                print(", ")
            current = current.next
        print("]")

if __name__ == "__main__":
    sol = Solution()
    nums = [1, 2, 6, 3, 4, 5, 6]
    val = 6
    for i in nums:
        sol.addAtTail(i)
    print("as is")
    sol.printList(sol.head)