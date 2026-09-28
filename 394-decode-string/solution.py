from collections import deque
class Solution:
    def decodeString(self, s: str) -> str:
        stack = deque()
        num = ""
        string = ""
        for char in s:
            match char:
                case "[":
                    stack.append([num, string])
                    num = ""
                    string = ""
                case "]":
                    pop_out = stack.pop()
                    string = pop_out[1] + int(pop_out[0]) * string
                case _:
                    if char.isdigit():
                        num += char
                    else:
                        string += char
        return string
if __name__ == "__main__":
    s = "3[a2[c]]"
    print("Ans = ", Solution().decodeString(s))

"""
遇到[
    stack.append([num, string])
    初始化num, string
遇到]
    pop_out = stack.pop()
    pop_out[0]數字
    pop_out[1]文字
    str += popout[1] + pop_out[0] * (string)
遇到數字
    num += char
遇到文字
    string += char

    
num = 3, string = "", Stack()
num = 0, string = "", Stack([3, ""])
num = 0, string = a, Stack([3, ""])
num = 2, string = a, Stack([3, ""])
num = 0, string = "", Stack([3, ""], [2, "a"])
num = 0, string = c, Stack([3, ""], [2, "a"])
num = 0, string = acc, Stack([3, ""])
num = 0, string = accaccacc, Stack()
"""