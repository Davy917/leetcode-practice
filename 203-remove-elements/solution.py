from typing import Optional
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution1122:

    def __init__(self):
        self.head = None 
        """
        為甚麼不能寫成ListNode.head = None??
        self.head 是 Solution1122 物件自己的屬性
        ListNode.head 是 ListNode 類別屬性
        """

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
        while head is not None and head.val == val:
            head = head.next

        if head is None:
            return None
        
        current = head

        while current.next is not None:
            if current.next.val == val:
                current.next = current.next.next
            else:
                current = current.next

        return head
    
    def printList(self, head):
        current = head
        val = []
        while current is not None:
            val.append(current.val)
            current = current.next
        print(val)

if __name__ == "__main__":
    sol = Solution1122()
    nums = [1, 1, 6, 3, 1, 5, 6]
    val = 1
    for i in nums:
        sol.addAtTail(i)
    print("as is")
    sol.printList(sol.head)
    result = sol.removeElements(sol.head, val)
    print("to be")
    sol.printList(result)