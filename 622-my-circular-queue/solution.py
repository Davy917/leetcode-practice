class MyCircularQueue:
    def __init__(self, k: int):
        self.queue = [-1] * k
        self.boundary = k - 1
        self.count = 0
        self.head = -1
        self.tail = -1
    def enQueue(self, value: int) -> bool:

        if not self.isFull():   #隊裡還有空間
            if self.count == 0: #是第一次入隊
                self.tail += 1
                self.head += 1
            else:               #非第一次入隊
                if self.tail != self.boundary:  # tail還有空間能退
                    self.tail += 1
                else:                           # tail沒有空間能退
                    self.tail = 0

            self.queue[self.tail] = value
            self.count += 1
            return True
        return False
    def deQueue(self) -> bool:
        if not self.isEmpty(): #還有元素能出隊
            self.queue[self.head] = -1
            self.count -= 1
            if self.count == 0:
                self.tail = -1
                self.head = -1
            else:
                if self.head != self.boundary:
                    self.head += 1
                elif self.head == self.boundary:
                    self.head = 0

            return True
        return False

    def Front(self) -> int:

        val = self.queue[self.head]
        return val if val != -1 else -1

    def Rear(self) -> int:
        val = self.queue[self.tail]
        return val if val != -1 else -1

    def isEmpty(self) -> bool:
        return self.count == 0
        
    def isFull(self) -> bool:
        return self.count == len(self.queue)


if __name__ == "__main__":
    circularQueue = MyCircularQueue(5)
    circularQueue.enQueue(5)
    circularQueue.enQueue(13)
    circularQueue.enQueue(8)
    circularQueue.enQueue(2)
    circularQueue.enQueue(10)
    circularQueue.deQueue()
    circularQueue.deQueue()
    circularQueue.enQueue(23)
    circularQueue.enQueue(6)
    circularQueue.deQueue()
    circularQueue.deQueue()
    circularQueue.deQueue()
    circularQueue.deQueue()
    circularQueue.deQueue()

"""
自己寫的
思路:

這題是盯著leetbook上面給的測資寫出來的
https://leetcode.cn/leetbook/read/queue-stack/kgtj7/

STEP1
先把__main__這筆測資運行時queue, head, tail的變化寫出來
實際寫的時候這些空格是補-1, 這邊為了方便展示

queue = [ , , , , , ],  head = -1, tail = -1 <----注意! 初始從-1出發
queue = [5, , , , , ],  head = 0, tail = 0
queue = [5,13, , , ],  head = 0, tail = 1
queue = [5,13,8, , ], head = 0, tail = 2
queue = [5,13,8,2, ], head = 0, tail = 3
queue = [5,13,8,2,10], head = 0, tail = 4 <----isFull() = True
queue = [ ,13,8,2,10], head = 1, tail = 4
queue = [ , ,8,2,10], head = 2, tail = 4
queue = [23, ,8,2,10], head = 2, tail = 0 <----tail踩到邊界了, 所以這一步歸零
queue = [23,6,8,2,10], head = 2, tail = 1
queue = [23,6, ,2,10], head = 3, tail = 1
queue = [23,6, , ,10], head = 4, tail = 1
queue = [23,6, , , ], head = 0, tail = 1 <----head踩到邊界了, 所以這一步歸零
queue = [ ,6, , , ], head = 1, tail = 1
queue = [ , , , , ], head = -1, tail = -1 <----最後一個元素也清掉了, tail, head回到初始狀態

STEP2
從 enQueue 開始實作, 畫出偽代碼樹狀圖:

隊裡還有空間

    是第一次入隊
        tail, head 同時移動到 0

    非第一次入隊
        tail還有空間能後退
            tail 往後退一格
        tail沒有空間能後退
            tail 歸零

    把元素加進下標為tail的那個格子 <----- 只要確保tail, head在正確位置, 怎麼加元素都不會出錯
    把總數加一

隊裡沒空間了
    直接return False

STEP3
接著 deQueue 開始實作, 畫出偽代碼樹狀圖:
隊裡還有元素

    把元素移出下標為head的那個格子 <----- 因為 enQueue 確保了head, tail一定是正確位置, 所以 deQueue 設計成先拔掉元素, 再移動head, tail
    把總數減一

    隊裡沒有元素
        head, tail初始化都回到-1

    隊裡還有元素
        head還有空間能後退
            head 往後退一格
        head沒有空間能後退
            head 歸零

隊裡沒元素了
    直接return False

STEP4
    把剩下的函式寫出來, 然後就可以寫代碼, 這邊就不贅述了

總結:
1. head, tail初始化成-1, 這麼做是為了簡化代碼

請看舉例:
假如初始化 tail = 0 加入一個值 tail = 1
這時候你調用Rear其實會得到 queue[1], 但我們都知道 queue[1] 那個時候是沒有元素的

當然也可以初始化成0, 但就要再思考防護措施
所以這邊才會把tail, head初始化成-1

2. deQueue 的邏輯是順著 enQueue 寫的
enQueue 是先確保 tail , head 在正確位置上才加入元素
deQueue就需要先移除元素, 因為 tail, head 一定在正確位置上
"""