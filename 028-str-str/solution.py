#雙指針 + while迴圈
class Solution1122:
    def strStr(self, haystack: str, needle: str) -> int:

        #宣告雙指針
        j = 0
        i = 0
        #宣告一個標籤
        tag = 0

        while(i < len(haystack)):
            print(i, j, tag)
            #如果不匹配
            if haystack[i] != needle[j]:

                #把標籤貼到下一組
                tag += 1
                #重置雙指針
                j = 0
                i = tag
                print("不匹配", i, j, tag)


            #如果匹配
            else:
                #如果完全匹配
                if(j == len(needle)-1):
                    #返回指針位置
                    return tag
                #雙指針往前推進
                i += 1
                j += 1

        #沒找到匹配的下標
        return -1

result = Solution1122().strStr("leetcode", "leeto")
print(result)