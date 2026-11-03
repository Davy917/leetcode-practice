from typing import Optional
class ListNode:
    def __init__(self, prev, next, val):
        self.prev: Optional[ListNode] = prev
        self.next: Optional[ListNode] = next
        self.val = val

"""
正確匯入方式
707-my-linked-list\solution.py
"""