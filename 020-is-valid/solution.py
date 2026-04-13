class Solution:
    def isValid(self, s: str) -> bool:
        #如果輸入的是奇數,代表永遠不會全部都配對完
        if len(s) % 2 != 0:
            return False
        
        #創建一個字典
        pairs = {")" : "(","]" : "[","}" : "{"}

        #創建一個空列表
        stack = list()

        for ch in s:

            #如果進來的是右括號
            if ch in pairs:
                #先看stack裡面有無左括號
                if len(stack) == 0:
                    return False
                #stack中最新的那個左括號跟現在進來的這右括號不匹配
                if stack[-1] != pairs[ch]:
                    return False
                #上面兩個條件都沒踩到,代表當前左括號是匹配的可以被消除
                stack.pop()
            
            #如果進來的是左括號
            else:
                #直接丟進stack
                stack.append(ch)
                
        #最後檢查,如果stack空了,則印True
        return not stack

result = Solution().isValid("([)]")
print(result)