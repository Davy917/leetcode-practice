from collections import deque
class MinStack:
    def __init__(self):
        self.stack = deque()
        self.min_val = float('inf')

    def push(self, value: int) -> None:
        if self.min_val > value:
            self.min_val = value
        self.stack.append(value)

    def pop(self) -> None:
        leave = self.stack.pop()
        if leave == self.min_val:
            if len(self.stack) != 0:
                self.min_val = min(self.stack)
            else:
                self.min_val = float('inf') #最後一個元素離隊, 應該馬上讓 min_val 回到初始狀態

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.min_val
    
if __name__ == "__main__":
    minStack = MinStack()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    minStack.getMin()
    minStack.pop()
    minStack.top()
    minStack.getMin()

#自己寫的