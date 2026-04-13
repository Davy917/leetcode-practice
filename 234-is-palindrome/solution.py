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
        return pre
    
class Solution1122:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        
        if head is None:
            return True
        fast = head#注意
        slow = head

        while fast.next is not None and fast.next.next is not None:
            fast = fast.next.next
            slow = slow.next
            print(f"fast = {fast.val}", f"slow = {slow.val}")

        pre = linkedList.reverseList(slow.next)
        linkedList.printList(pre)

        while pre is not None:#注意
            print(f"head.val = {head.val} " + f"pre.val = {pre.val}")
            if head.val == pre.val:
                head = head.next
                pre = pre.next
            else:
                print("is not Palindrome")
                return False
        print("is Palindrome")
        return True
    
if __name__ == "__main__":
    nums = [1, 3, 4, 3, 1]
    sol = Solution1122()
    linkedList = linkedList()
    for i in nums:
        linkedList.addAtTail(i)
    #linkedList.printList(linkedList.head)
    sol.isPalindrome(linkedList.head)
"""
算法

整个流程可以分为以下4个步骤：

1. 找到前半部分链表的尾节点。
2. 反转后半部分链表。
3. 判断是否回文。
4. 返回结果。
"""
"""
line 44
fast = head
不能寫成
fast = head.next
因為會影響line 49迴圈的步數, 進一步影響slow的位置
line54 我們會把slow.next傳進reverseList
所以又會進一步影響反轉鏈表

如果反轉鏈表有問題, 後面line57 在比對是否回文的時候
在某些情況下會不精準

line57 為什麼不能寫成 while pre.next is not None
一樣是迴圈步數的問題
寫成那樣, 不管pre是奇數還是偶數都會少走一圈
在比對的時候也會少比一次, 導致錯誤
"""
