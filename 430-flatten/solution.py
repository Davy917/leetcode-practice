from typing import Optional;
class Node:
    def __init__(self, val, prev, next, child) -> None:
        self.val = val
        self.prev = prev
        self.next = next
        self.child = child
class LinkedList:
    def __init__(self) -> None:
        self.head = None
    def buildexample(self) -> Node:
    #Set next, child
        newNode12 = Node(12, None, None, None)
        newNode11 = Node(11, None, newNode12, None)
        newNode10 = Node(10, None, None, None)
        newNode9 = Node(9, None, newNode10, None)
        newNode8 = Node(8, None, newNode9, newNode11)
        newNode7 = Node(7, None, newNode8, None)
        newNode6 = Node(6, None, None, None)
        newNode5 = Node(5, None, newNode6, None)
        newNode4 = Node(4, None, newNode5, None)
        newNode3 = Node(3, None, newNode4, newNode7)
        newNode2 = Node(2, None, newNode3, None)
        newNode1 = Node(1, None, newNode2, None)

    #Set prev
        #layer3
        newNode12.prev = newNode11
        #layer2
        newNode10.prev = newNode9
        newNode9.prev = newNode8
        newNode8.prev = newNode7
        #layer1
        newNode6.prev = newNode5
        newNode5.prev = newNode4
        newNode4.prev = newNode3
        newNode3.prev = newNode2
        newNode2.prev = newNode1
        head = newNode1
        return head
class Solution1122:
    def flatten(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if head is None:
            return None
            
        cur = head
        while cur is not None:

            if cur.child is None:
                print(cur.val)
                cur = cur.next
                continue
            print(cur.val, " find child")
            temp = cur.next
            childhead = cur.child
            
            cur.child.prev = cur
            cur.next = cur.child
            cur.child = None
            
            while childhead.next is not None:
                childhead = childhead.next

            childhead.next = temp
            if temp is None:
                cur = cur.next
                continue
            temp.prev = childhead
            cur = cur.next
            print(cur.val)
            
        return head
if __name__ == "__main__":
    sol = Solution1122()
    mainList = LinkedList()
    examplehead = mainList.buildexample()
    sol.flatten(examplehead)