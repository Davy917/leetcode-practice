from collections import deque
class MovingAverage:

    def __init__(self, size: int):
        self.sum = 0
        self.dq = deque()
        self.max_size = size

    def next(self, val: int) -> float:
        self.sum += val
        if len(self.dq) != self.max_size:
            self.dq.append(val)
            return self.sum / len(self.dq)
        leave = self.dq.popleft()
        self.sum -= leave
        self.dq.append(val)
        return self.sum / len(self.dq)

if __name__ == "__main__":
    movingAverage = MovingAverage(3)
    movingAverage.next(1)
    movingAverage.next(10)
    movingAverage.next(3)
    movingAverage.next(5)

"""
自己寫的
dq未滿
    val加入尾部
dq已滿
    dq[0]出隊, val加入尾部
    
維護一個sum, 隨時更新dq內部的總和, 平均值就是sum / len(dq)
"""