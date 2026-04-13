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
    
class Solution1122:
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
    nums = [1, 2, 3]
    list = LinkedList()
    sol = Solution1122()
    for i in nums:
        list.addAtTail(i)
    sol.reverseList_v2(list.head)

"""
我的理解是:
每次代碼執行到 return self.reverse(temp, current), 
就會呼叫 self.reverse(temp, current) 並且嘗試把pre return, 
但每次都未能成功因為每次都會碰到新的return self.reverse(temp, current) 直到達成條件走進 current is None , 
就不會再碰到 return self.reverse(head, None), 
所以就擺脫了迴圈, current is None之後pre被傳出,
前面累積的那些return還沒被滿足, 就會開始一層一層的執行把pre傳出來到最外層, 
直到所有的return都被執行完

Copilot修正版:

你的整體方向是對的：會先一路往更深層呼叫（所以外層的 return 都「卡住等待」），直到 base case 才開始一層層把同一個結果傳回最外層。

幫你把幾個用詞/概念修正得更精準一點：

1. 到 return self.reverse(temp, current) 時：

不是「嘗試把 pre return 但失敗」
而是：外層這一層的函式先暫停，等待內層 self.reverse(...) 算出結果後，外層再把那個結果 return 出去。

2.「新的 return 一直出現直到 current is None」這句可以理解成：
遞迴一直往下呼叫，每一層都在等更深層回傳（所以看起來像 return 一直沒達成）。

3. 到 current is None（base case）：
這一層會直接 return pre（此時的 pre 就是反轉後的新頭 newHead）。
然後才開始「回程」：前面每一層先前卡住的 return self.reverse(...) 會依序得到回傳值，並再 return 回去。

4. 你提到「就不會再碰到 return self.reverse(head, None)」：

更精準是：最外層那個 return self.reverse(head, None) 早就已經呼叫出去了，它只是一直在「等」最深層回傳結果；等到回程一路傳上來，它才拿到結果並 return。
"""