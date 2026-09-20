from collections import deque
class MyStack:

    def __init__(self):
        self.stack = deque()
        self.t = 0 # t代表棧頂元素

    def push(self, x: int) -> None:
        self.stack.append(x)
        self.t = x
    def pop(self) -> int:
        n = len(self.stack)
        for i in range(1, n):
            to_add = self.stack.popleft() #popleft彈出
            self.stack.append(to_add)     #append加回
            if i == n - 1:                #迴圈最後一圈
                self.t = to_add           #更新 t
        return self.stack.popleft()

    def top(self) -> int:
        return self.t

    def empty(self) -> bool:
        return len(self.stack) == 0

"""
自己寫的

規則
可用的deque操作：
    append(x) → push to back ✅
    popleft() → pop from front ✅
    len(q) → size ✅
    len(q) == 0 → is empty ✅

不可用的:
    q[-1]、q[0] → 索引存取 ❌
    pop() → 從尾端取出 ❌
    appendleft() → 從前端加入 ❌

這題主要在考察pop的實現, 並且有上述的限制, 只能使用popleft, append

舉例[1, 2, 3]

popleft彈出1, append加回
[2, 3, 1]

popleft彈出2, append加回
[3, 1, 2]

這時再做一次popleft, 就可以取得3 <---我們要的

總結:
n = len(stack)
可得出上面的動作反覆做n-1次, 然後離開迴圈, 直接popleft就是最尾端的元素了, 直接返回它
pop之後, 棧頂元素會改變, 所以我們也要把 t 更新, 但 t 要如何更新?

(popleft彈出, append加回) 做到最後一次時, 當時的數值就會是 t 預期的值

再驗證 [4, 9, 6, 3], n = 4
pop會得到3
top會得到6

popleft彈出4, append加回
[9, 6, 3, 4]

popleft彈出9, append加回
[6, 3, 4, 9]

popleft彈出6, append加回 <--- 注意! 此時6就是top預期數值, 因此順便賦值給t
[3, 4, 9, 6]

return stack.popleft()

上面再次驗證了(popleft彈出, append加回)循環做n-1次, 然後最後一輪要賦值給 t
我們就可取得正確的pop, top
"""