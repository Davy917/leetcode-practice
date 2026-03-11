class ListNode:
    def __init__(self, key, val, next):
        self.key = key
        self.val = val
        self.next = next
        
class LinkedList:

    def __init__(self):
        self.head = None
        self.tail = None

    def AddAtTail(self, key, val):
        newNode = ListNode(key, val, None)
        if self.head is None:
            print(f"append val = {newNode.val} at head")
            self.head = newNode
            self.tail = newNode
            return
        
        cur = self.head
        while cur.next is not None:
            cur = cur.next
            
        print(f"append val = {newNode.val} at tail")
        cur.next = newNode
        self.tail = newNode
        return self.head
    
    def RemoveNode(self, key):
        cur = self.head

        if cur.key == key:
            print(f"remove val = {cur.val} from head")
            self.head = cur.next
            return self.head
        
        while cur.next is not None:
            if cur.next.key == key:
                print(f"remove val = {cur.next.val} from head")
                cur.next = cur.next.next
                return self.head
            else:
                cur = cur.next
    """
    head--->[1,1|-]--->[2,2|]
    head--->[1,1|-]--->[2,2|-]--->[1,1]
    head--->[2,2|-]--->[1,1| ]
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
        
        self.list.AddAtTail(key, self.dict[key].val)
        self.list.RemoveNode(key)

        print("get value ", self.dict[key].val)
        return self.dict[key].val

    def put(self, key: int, val: int) -> None:
        if key in self.dict:
            self.list.AddAtTail(key, val)
            self.list.RemoveNode(key)
            self.dict[key] = self.list.tail
            return

        if len(self.dict) < self.capacity:
            self.list.AddAtTail(key, val)
            self.dict[key] = self.list.tail
            return
        else:
            del self.dict[self.list.head.key]
            self.list.RemoveNode(self.list.head.key)
            #self.list.printList(self.list.head)
            self.list.AddAtTail(key, val)
            self.dict[key] = self.list.tail
            #self.list.printList(self.list.head)

if __name__ == "__main__":
    lRUCache = LRUCache(2)
    lRUCache.put(2, 6)
    lRUCache.put(1, 5)
    lRUCache.put(1, 2)
    lRUCache.get(1)
    lRUCache.get(2)



    # lRUCache.put(1, 1); # 缓存是 {1=1}
    # lRUCache.put(2, 2); # 缓存是 {1=1, 2=2}
    # lRUCache.get(1);    # 返回 1 {2=2, 1=1}
    # lRUCache.put(3, 3); # 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    # lRUCache.get(2);    # 返回 -1 (未找到)
    # lRUCache.put(4, 4); # 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    # lRUCache.get(1);    # 返回 -1 (未找到)
    # lRUCache.get(3);    # 返回 3
    # lRUCache.get(4);    # 返回 4


    """
    head--->[1,1|-]--->[2,2| ]
    head--->[2,2| ]
    head--->[2,2|-]--->[1,1| ]
    """