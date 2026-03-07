#使用快慢指針，反轉鏈表，空間複雜度有優勢
from typing import Optional#寫optional的好處是??
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class linkedList:
    def __init__(self):
        self.head = None #注意
        self.tail = None #注意
    def addAtTail(self, val):
        newNode = ListNode(val)
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

    def reverseList(self, slow):
        current = slow
        pre = None
        while current is not None:
            temp = current.next
            current.next = pre
            pre = current
            current = temp
            print("pre = ", pre.val)
        return pre

class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        fast = head.next
        slow = head
        while fast.next is not None and fast.next.next is not None:
            fast = fast.next.next
            slow = slow.next
            print(f"fast = {fast.val}", f"slow = {slow.val}")
        linkedList.reverseList(slow.next)

if __name__ == "__main__":
    nums = [1, 2, 3, 4, 4, 3, 2, 1]
    sol = Solution()
    linkedList = linkedList()
    for i in nums:
        linkedList.addAtTail(i)
    linkedList.printList(linkedList.head)
    sol.isPalindrome(linkedList.head)

"""
算法

整个流程可以分为以下4个步骤：

1. 找到前半部分链表的尾节点。
2. 反转后半部分链表。
3. 判断是否回文。
4. 返回结果。
"""
