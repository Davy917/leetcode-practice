from typing import List
class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        temp = 0
        stack = []
        for char in tokens:
            match char:
                case "+":
                    temp = int(stack.pop())
                    temp = int(stack.pop()) + temp
                    stack.append(temp)
                case "-":
                    temp = int(stack.pop())
                    temp = int(stack.pop()) - temp
                    stack.append(temp)
                case "*":
                    temp = int(stack.pop())
                    temp = int(stack.pop()) * temp
                    stack.append(temp)
                case "/":
                    temp = int(stack.pop())
                    temp = int(int(stack.pop()) / temp)
                    stack.append(temp)
                case _:
                    stack.append(char)
        return int(stack[0])

if __name__ == "__main__":
    tokens1 = ["2","1","+","3","*"]
    tokens2 = ["4","13","5","/","+"]
    tokens3 = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
    tokens4 = ["1","2","+","3","4","+","*"]
    print("Ans = ", Solution().evalRPN(tokens3))

"""
除法介紹:
LanguagePractice/Compare4Language/除法.md

match-case介紹
LanguagePractice/Compare4Language/switch_case.md

自己寫的
以token4為例 tokens4 = ["1","2","+","3","4","+","*"]
stack = [1], temp = 0
stack = [1, 2], temp = 0
stack = [1], temp = 2 <--- 遇到+, 第一個出棧的元素直接賦值給 temp
stack = [], temp = 1 + temp = 3
stack = [3] <--- 把3押回棧中
stack = [3, 3]
stack = [3, 3, 4]
stack = [3, 3], temp = 4 <--- 遇到+, 第一個出棧的元素直接賦值給 temp
stack = [3], temp = 3 + temp = 7
stack = [3, 7] <--- 把7押回棧中
stack = [3], temp = 7 <--- 遇到*, 第一個出棧的元素直接賦值給 temp
stack = [], temp = 3 * temp = 21
stack = [21] <--- 把21押回棧中

整個過程就是不斷的出棧, 然後完成這個等式
temp = val (+-*/) temp
然後再壓回棧中

代碼結構:
for char in token
    char 是運算子
        temp = 第一個出棧的
        temp = 第二個出棧的 (+-*/) temp
        temp 壓回棧中
    char 是數字
        入棧

stack[0]一定會是答案, 直接返回
"""