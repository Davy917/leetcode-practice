class LinkedList(object):
    def __init__(self, val = 0, next = None):
        self.val = val
        self.next = next

class MyLinkedList(object):

    def __init__(self):
        self.head = None#注意

    def get(self, index):
        counter = 0
        visitor = self.head

        if visitor is None:
            return -1

        while counter != index and visitor != None:
            visitor = visitor.next
            counter += 1

        return -1 if visitor is None else visitor.val

    def addAtHead(self, val):
        newNode = LinkedList(val)
        newNode.next = self.head #注意
        self.head = newNode
        
    def addAtTail(self, val):
        newNode = LinkedList(val)

        if self.head == None: #空鏈表直接賦值
            self.head = newNode
            return
        
        tailNode = self.head

        while tailNode != None:

            if tailNode.next == None:
                tailNode.next = newNode
                break

            tailNode = tailNode.next


    def addAtIndex(self, index, val):
        newNode = LinkedList(val)
        visitor = self.head
        if index == 0:
            self.addAtHead(val)
            return

        counter = 0
        while counter < index-1 and visitor is not None:
            visitor = visitor.next
            counter += 1
            print(f"counter = {counter}")
            
        if counter == index-1 and visitor is not None:
            print(f"Index {val} into position {index}")
            newNode.next = visitor.next
            visitor.next = newNode
        
    def deleteAtHead(self):
        """
        在 deleteAtHead 方法中，你是要刪除鏈表的第一個節點（head）。

        看這個例子：
        head--->[A | -]---> [B | -]---> [C | ]

        執行 deleteAtHead() 後，你想要的結果是：
        head---> [B | -]---> [C | ]

        所以邏輯是：
            head 目前指向節點 A
            head.next 指向節點 B
            this.head = head.next; 讓 head 改為指向節點 B

        這樣就刪除了節點 A。
        如果寫成 this.head = head.next.next;
        那就會跳過節點 B 直接指向節點 C，這樣反而刪除了節點 A 和節點 B，不符合需求。
        """
        if self.head == None:
            return
        self.head = self.head.next

    def deleteAtIndex(self, index):
        visitor = self.head
        
        if index == 0:
            self.deleteAtHead()
            return
        counter = 0
        #2
        while counter < index-1 and visitor.next is not None:
            visitor = visitor.next
            counter += 1
            print(f"counter = {counter}")
            
        if counter == index-1 and visitor.next is not None:
            print(f"Delete position {index} value")
            visitor.next = visitor.next.next
            
    def printList(self):
        current = self.head
        elements = []
        while current is not None:
            elements.append(str(current.val))
            current = current.next
        print("[" + ", ".join(elements) + "]")
        
        """
        str.join() 是一個 字串 (string) 的方法，它的主要功能是將一個 可迭代物件 (iterable) 中的所有字串元素連接起來，並在每個元素之間插入呼叫 join() 方法的那個字串。  

        簡單來說，它就像是用膠水把一堆字串粘合起來，而這個「膠水」就是你呼叫 join() 時使用的字串。  
        """
        
if __name__ == "__main__":
    obj = MyLinkedList()
    obj.addAtIndex(2, 1)
    obj.printList()
    obj.addAtIndex(3, 4)
    obj.printList()
    obj.addAtTail(1)
    obj.get(0)
    obj.deleteAtIndex(0)
    obj.get(0)
"""
        假設index = 2

                            visitor     index = 2
                            ↓           ↓
        head--->[A | -]---> [B | -]---> [C | -]---> [D | ]

                                [newNode | ]

"""