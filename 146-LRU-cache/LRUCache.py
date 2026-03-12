class ListNode:
    def __init__(self, key, val, next, prev):
        self.key = key
        self.val = val
        self.next = next
        self.prev = prev
        
class LinkedList:

    def __init__(self):
        self.head = None
        self.tail = None
    def AddAtTail(self, key, val):
        newNode = ListNode(key, val, None, None)
        if self.head is None:
            print(f"append val = {newNode.val} at head")
            self.head = newNode
            self.tail = newNode
            return
        
        print(f"append val = {newNode.val} at tail")
        self.tail.next = newNode
        newNode.prev = self.tail
        self.tail = newNode
        return self.head
    
    def RemoveNode(self, ListNode):
        if ListNode is None:
            return self.head

        # 只有一個節點
        if ListNode.prev is None and ListNode.next is None:
            self.head = None
            self.tail = None
        # 頭節點
        elif ListNode.prev is None:
            self.head = ListNode.next
            self.head.prev = None
        # 尾節點
        elif ListNode.next is None:
            self.tail = ListNode.prev
            self.tail.next = None
        # 中間節點
        else:
            ListNode.prev.next = ListNode.next
            ListNode.next.prev = ListNode.prev

        ListNode.prev = None
        ListNode.next = None
        return self.head

    """
    head
    ↓
    [1,1]<===>[2,2]<===>[3,3]

    head
    ↓
    [2,2]<===>[3,3]
    """

    
    def printList(self, head):
        if(head is None):
            return
        cur = head
        nums = []
        nums.append(head.val)
        while cur.next is not None:
            cur = cur.next
            nums.append(cur.val)
        print(nums)

class LRUCache:
    def __init__(self, capacity: int):
        self.list = LinkedList()#注意，建一次，重複使用
        self.capacity = capacity
        self.dict = {}

    def get(self, key:int) -> int:
        if key not in self.dict:
            print("failed to get value ")
            return -1
        
        self.list.RemoveNode(self.dict[key])
        self.list.AddAtTail(key, self.dict[key].val)
        self.dict[key] = self.list.tail#注意
        print("get value ", self.dict[key].val)
        self.list.printList(self.list.head)
        return self.dict[key].val

    def put(self, key: int, val: int) -> None:
        if key in self.dict:
            self.list.RemoveNode(self.dict[key])
            self.list.AddAtTail(key, val)
            self.dict[key] = self.list.tail
            self.list.printList(self.list.head)
            return

        if len(self.dict) < self.capacity:
            self.list.AddAtTail(key, val)
            self.dict[key] = self.list.tail
            self.list.printList(self.list.head)
            return
        else:
            old_ListNode = self.list.head
            old_key = old_ListNode.key
            self.list.RemoveNode(old_ListNode)
            del self.dict[old_key]
            self.list.AddAtTail(key, val)
            self.dict[key] = self.list.tail
            self.list.printList(self.list.head)

if __name__ == "__main__":
    lRUCache = LRUCache(1)
    lRUCache.put(1, 1); # 缓存是 {1=1}
    lRUCache.put(2, 2); # 缓存是 {1=1, 2=2}
    lRUCache.get(1);    # 返回 1
    lRUCache.put(3, 3); # 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    lRUCache.get(2);    # 返回 -1 (未找到)
    lRUCache.put(4, 4); # 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    lRUCache.get(1);    # 返回 -1 (未找到)
    lRUCache.get(3);    # 返回 3
    lRUCache.get(4);    # 返回 4