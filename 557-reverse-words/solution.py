class Solution1122:
    def reverseWords(self, s: str) -> str:
        reverse_string = ""
        word = ""

        for i in range(0, len(s)):
            if s[i] != " ":
                word += s[i]
                if i == len(s) - 1:#例外狀況, 遍歷到最後一個字母時(非最佳寫法)
                    print(word)
                    for j in range(len(word)-1, -1, -1):
                        reverse_string += word[j]
            else:
                print(word)
                for j in range(len(word)-1, -1, -1):
                    reverse_string += word[j]
                reverse_string += " "
                word = ""

        return reverse_string

print(Solution1122().reverseWords("Let's  take LeetCode contest"))

"""
输入：s = "Let's take LeetCode contest"
输出："s'teL ekat edoCteeL tsetnoc"

输入： s = "Mr Ding"
输出："rM gniD"

可以。把「最後一個字元的處理」改為「迴圈結束後沖出剩餘的 word」，
就不需要在迴圈中判斷最後一個索引，並且不會多加尾端空白。

            # ...existing code...
            else:
                # ...existing code...
                for j in range(len(word) - 1, -1, -1):
                    reverse_string += word[j]
                reverse_string += " "
                word = ""

        # 迴圈後沖出最後一個詞
        for j in range(len(word) - 1, -1, -1):
            reverse_string += word[j]

        return reverse_string

另一路徑（保留連續空格）：直接用 split(' ') 與 join，無需特別處理最後字元。

class Solution1122:
    def reverseWords(self, s: str) -> str:
        return " ".join(w[::-1] for w in s.split(" "))
"""