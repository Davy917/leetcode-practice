class LinkedList(object):
    def __init__(self, val = 0, next = None):
        self.val = val
        self.next = next

class MyLinkedList(object):

    def __init__(self):
        self.head

    def get(self, index):
        counter = 0
        visitor = self.head

        if visitor == None:
            return None

        while counter != index and visitor != None:
            visitor = visitor.next
            counter += 1

        return -1 if visitor == None else visitor.val

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

        #覺得寫這樣好像也可以
        while tailNode != None:

            if tailNode.next == None:
                tailNode.next = newNode
                break

            tailNode = tailNode.next


    def addAtIndex(self, index, val):
        """
        :type index: int
        :type val: int
        :rtype: None
        """
        
    def deleteAtHead(self, index):
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
        """
        :type index: int
        :rtype: None
        """
    def printList(self, head):
        return head
        
if __name__ == "__main__":
    # obj = MyLinkedList()
    # param_1 = obj.get(index)
    # obj.addAtHead(val)
    # obj.addAtTail(val)
    # obj.addAtIndex(index,val)
    # obj.deleteAtIndex(index)

    """
        假設index = 2

                            visitor     index = 2
                            ↓           ↓
        head--->[A | -]---> [B | -]---> [C | -]---> [D | ]

                                [newNode | ]

    """