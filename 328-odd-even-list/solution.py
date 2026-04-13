from typing import Optional
class ListNode:
    def __init__(self, val, next) -> None:
        self.val = val
        self.next = next
        
class LinkedList:
    def __init__(self) -> None:
        self.head = None
        
    def AddAtTail(self, newNode):
        if self.head is None:
            self.head = newNode
            return
        cur = self.head
        while cur.next is not None:
            cur = cur.next
        cur.next = newNode
        
    def printList(self, head):
        if self.head is None:
            return head
        nums = []
        nums.append(head.val)
        cur = head
        while cur.next is not None:
            cur = cur.next
            nums.append(cur.val)
        print(nums)
        
class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return head
        odd = head
        evenhead = head.next
        even = evenhead
        while even and even.next:#注意
            odd.next = odd.next.next
            odd = odd.next
            even.next = odd.next
            even = even.next
        odd.next = evenhead
        mainList.printList(head)
        return head
if __name__ == "__main__":
    nums = [2,1,3,5,6,4,7]
    sol = Solution()
    mainList = LinkedList()
    for i in nums:
        newNode = ListNode(i, None)
        mainList.AddAtTail(newNode)
    sol.oddEvenList(mainList.head)
"""
[2,1,3,5,6,4,7]

初始化:
        odd      even
head--->[2|-]--->[1|-]--->[3|-]--->[5|-]--->[6|-]--->[4|-]--->[7|-]
evenhead-------↗

Loop1:
odd.next = odd.next.next
        odd
head--->[2|-]--->[3|-]--->[5|-]--->[6|-]--->[4|-]--->[7|-]
            even
evenhead--->[1|-]

odd = odd.next
                  odd
head--->[2|-]--->[3|-]--->[5|-]--->[6|-]--->[4|-]--->[7|-]
            even
evenhead--->[1|-]

even.next = odd.next
                  odd
head--->[2|-]--->[3|-]--->[5|-]--->[6|-]--->[4|-]--->[7|-]
            even            |
evenhead--->[1|-]-----------|

even = even.next
                    odd     even
head--->[2|-]--->[3|-]--->[5|-]--->[6|-]--->[4|-]--->[7|-]
                            |
evenhead--->[1|-]-----------|

Loop2:
odd.next = odd.next.next
                  odd
head--->[2|-]--->[3|-]--->[6|-]--->[4|-]--->[7|-]
                    even
evenhead--->[1|-]--->[5|-]

odd = odd.next
                          odd
head--->[2|-]--->[3|-]--->[6|-]--->[4|-]--->[7|-]
                      even
evenhead--->[1|-]--->[5|-]

even.next = odd.next
                          odd
head--->[2|-]--->[3|-]--->[6|-]--->[4|-]--->[7|-]
                    even             |
evenhead--->[1|-]--->[5|-]-----------|

even = even.next
                            odd     even
head--->[2|-]--->[3|-]--->[6|-]--->[4|-]--->[7|-]
                                     |
evenhead--->[1|-]--->[5|-]-----------|

Loop3:
Pass
"""