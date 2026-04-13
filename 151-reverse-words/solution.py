class Solution1122:
    def P0151_reverseWords(self, s: str) -> str:
        list1 = s.split(' ')
        print(type(list1))

        while '' in list1:#好理解但效率差
            list1.remove('')
        #最優寫法clean_list = [item for item in my_list if item != '']
        list1.reverse()
        print(list1)
        result = ' '.join(list1)
        return result

sol = Solution1122()
print(sol.P0151_reverseWords("  hello world  "))

"""
你有什麼想法?
strip??
split??
先存成List??再顛倒?
首先要得到一個乾淨的列表
現在我們得到一張乾淨列表了
你有什麼想法?
把列表整個顛倒過來
"""