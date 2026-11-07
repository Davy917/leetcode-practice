import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).parent.parent))
from datastructure.LinkedList.ListNode import ListNode

class Solution:
    def __init__(self):
        self.size = 0
        self.tail = None
    def rotateRight(self, head: ListNode | None, k: int) -> ListNode | None:
        if not head:
            return None
        dummy = ListNode(-101, head)
        self.calSize(dummy)
        k %= self.size
        if k == 0:
            return head
        cur = dummy
        for _ in range(self.size - k):
            cur = cur.next
        dummy.next = cur.next
        cur.next = None
        self.tail.next = head
        return dummy.next

    def calSize(self, cur: ListNode):
        while cur.next:
            cur = cur.next
            self.tail = cur
            self.size += 1

"""
自己寫的
思路:
在紙上畫狀態圖
[1,2,3,4,5] 旋轉(0, 5, 10...次) 
[5,1,2,3,4] 旋轉(1, 6, 11...次)
[4,5,1,2,3] 旋轉(2, 7, 12...次)
[3,4,5,1,2] 旋轉(3, 8, 13...次)
[2,3,4,5,1] 旋轉(4, 9, 14...次)

觀察規律:
假設鏈表size為n
旋轉 1, n+1, 2n+1...次都可以得到[5,1,2,3,4]
同理 k, n+k, 2n+k...次都可以得到相同結果

旋轉k次跟旋轉 k%n 次會得到一樣結果
不用真的旋轉k次, 很有可能會超時, 只需旋轉 k%n 次

旋轉邏輯:
假設, 已知鏈表旋轉結果是這樣[3,4,5,1,2]
[d,1,2,3,4,5], 要怎麼變成[d,3,4,5,1,2]
斷點在2, 把[1,2]斷開
[d,3,4,5] 尾端接上[1,2] = [d,3,4,5,1,2]
只要每次都能精確找到斷點就可以解題

找斷點邏輯:
[d,1,2,3,4,5], 假設cur = d, cur每次往前一步, 如何讓cur精準停在斷點上?
看回最一開始畫的狀態圖
旋轉1次, 斷點在4, 相當於cur走4步
旋轉2次, 斷點在3, 相當於cur走3步
旋轉3次, 斷點在2, 相當於cur走2步
旋轉4次, 斷點在1, 相當於cur走1步

可得出:
鏈表長度 = n
旋轉次數 = k%n
cur往前幾次 = 鏈表長度 - 旋轉次數 = n - k%n

流程:
cur = dummy
cur往前n - k%n次, cur停在斷點上
把cur.next斷開, 重新接上就找到答案

旋轉鏈表有很多種實現方式, 目前這個版本時間空間複雜度都好
在寫的時候一定要畫狀態圖, 推敲找規律, 才能寫的好
"""