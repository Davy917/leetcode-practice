from typing import Optional
try:
    from .ListNode import ListNode   # 被當成 package 匯入時（例如 __init__.py）
except ImportError:
    from ListNode import ListNode    # 直接執行此檔時（同資料夾）

class MyLinkedList:

    def __init__(self):
        self.head: Optional[ListNode] = None
        self.tail: Optional[ListNode] = None
        self.size = 0
    def get(self, index: int) -> int:
        if 0 > index or index >= self.size:
            return -1
        cur = self.head
        j = 0
        while j < index:
            cur = cur.next
            j += 1
        return cur.val

    def addAtHead(self, val: int) -> None:
        self.addAtIndex(0, val)

    def addAtTail(self, val: int) -> None:
        self.addAtIndex(self.size, val)

    def addAtIndex(self, index: int, val: int) -> None:
        if 0 > index or index > self.size:
            return
        dummy = ListNode(None, self.head, -1)
        new_node = ListNode(None, None, val)
        cur = dummy
        j = 0
        while j < index:
            cur = cur.next
            j += 1
        temp = cur.next
        cur.next = new_node
        new_node.prev = cur
        new_node.next = temp
        if temp:
            temp.prev = new_node

        if index == 0:
            self.head = new_node
        if index == self.size:
            self.tail = new_node
        self.size += 1


    def deleteAtIndex(self, index: int) -> None:
        if 0 > index or index >= self.size:
            return
        dummy = ListNode(None, self.head, -1)
        cur = dummy
        j = 0
        while j < index:
            cur = cur.next
            j += 1

        temp = cur.next.next
        cur.next = temp
        if temp:
            temp.prev = cur

        if index == 0:
            self.head = temp
        if index == self.size - 1:
            self.tail = cur
            if self.size == 1:
                self.tail = None
        self.size -= 1

    def printList(self, head: ListNode) -> None:
        cur = head
        to_print = []
        while cur:
            to_print.append(cur.val)
            cur = cur.next
        print(to_print)

if __name__ == "__main__":
    dll = MyLinkedList()
    dll.addAtTail(1)
    dll.addAtHead(0)
    dll.addAtTail(2)
    dll.addAtIndex(1, 4)
    dll.deleteAtIndex(1)
    dll.printList(dll.head)