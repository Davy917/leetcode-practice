from typing import Optional
class ListNode:
    def __init__(self, val, next):
        self.val = val
        self.next = next
class solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        pre: Optional[ListNode] = None
        current = head
        while current is not None:#為什麼是current 而不是 current.next
            temp = current.next
            current.next = pre
            pre = current
            current = temp
        return pre



"""
reverseList解說
變數:
1. current：目前正在處理的「當前節點」
2. pre：已經反轉完成的「新鏈」的頭
3. temp：暫存 current.next，避免斷鏈後找不到後面節點

每一圈 while 其實都在做同一件事：
1. 先把「下一個節點」存起來（temp = current.next）
2. 把當前節點的箭頭反過來指向 pre（current.next = pre）
3. 更新 pre 到當前節點（pre = current）
4. current 前進到原本下一個節點（current = temp）

初始化:
current → (1) → (2) → (3) → None
temp    → None
pre     → None

第一迴圈:
(1) temp = current.next
    current → (1) → (2) → (3) → None
    temp    → (2) → (3) → None
    pre     → None

 pre       cur         temp
  ↓         ↓           ↓
None---->[1 | -]---> [2 | -]---> [3 | -]---> None

(2) current.next = pre
    current → (1) → None
    temp    → (2) → (3) → None
    pre     → None

 pre       cur          temp
  ↓         ↓            ↓
None<----[- |1 ]      [2 | -]---> [3 | -]---> None

(3) pre = current
    current → (1) → None
    temp    → (2) → (3) → None
    pre     → (1) → None

           pre
           cur         temp
            ↓           ↓
None<----[1 | ]      [2 | -]---> [3 | -]---> None

(4) current = temp
    current → (2) → (3) → None
    temp    → (2) → (3) → None
    pre     → (1) → None
                       cur
           pre         temp
            ↓           ↓
None<----[1 | ]      [2 | -]---> [3 | -]---> None

第二迴圈:
開始

(1) temp = current.next
    current → (2) → (3) → None
    temp    → (3) → None
    pre     → (1) → None

           pre         cur         temp
            ↓           ↓           ↓
None<----[1 | ]      [2 | -]---> [3 | -]---> None

(2) current.next = pre
    current → (2) → (1) → None
    temp    → (3) → None
    pre     → (1) → None

           pre         cur         temp
            ↓           ↓           ↓
None<----[1 | ] <----[- | 2]     [3 | -]---> None

(3) pre = current
    current → (2) → (1) → None
    temp    → (3) → None
    pre     → (2) → (1) → None

                       pre
                       cur         temp
                        ↓           ↓
None<----[1 | ] <----[- | 2]     [3 | -]---> None

(4) current = temp
    current → (3) → None
    temp    → (3) → None
    pre     → (2) → (1) → None

                                   cur
                       pre         temp
                        ↓           ↓
None<----[1 | ] <----[- | 2]     [3 | -]---> None

第三迴圈:
(1) current.next = temp
    current → (3) → None
    temp    → None
    pre     → (2) → (1) → None

                       pre         cur       temp
                        ↓           ↓         ↓    
None<----[1 | ] <----[- | 2]     [3 | -]---> None

(2)current.next = pre
    current → (3) → (2) → (1) → None
    temp    → None
    pre → (2) → (1) → None

                       pre         cur       temp
                        ↓           ↓         ↓    
None<----[1 | ] <----[- | 2] <---[- | 3]     None

(3) pre = current
    current → (3) → (2) → (1) → None
    temp    → None
    pre     → (3) → (2) → (1) → None

                                   pre
                                   cur       temp
                                    ↓         ↓    
None<----[1 | ] <----[- | 2] <---[- | 3]     None

(4) current = temp 
    current → None
    temp    → None
    pre → (3) → (2) → (1) → None

                                             cur
                                   pre       temp
                                    ↓         ↓    
None<----[1 | ] <----[- | 2] <---[- | 3]     None
"""


