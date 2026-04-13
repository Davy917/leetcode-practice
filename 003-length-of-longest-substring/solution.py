from typing import Dict
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:

        max_str_len = 0
        cur_str_len = 0
        dict : Dict[str, int]= {}
        
        i = 0
        while i < len(s):

            #如果發現重複，則進行下面初始化
            if s[i] in dict:
                print(f"find {s[i]} in dict")
                i = dict[s[i]] + 1 #指針移到重覆元素的下一位
                dict.clear() #初始化字典
                cur_str_len = 0 #初始化當前長度
                print(f"i reset to {i} go next loop")
                continue

            #如果非重複字元則進行下面
            dict[s[i]] = i #字典賦值
            i += 1 #指針
            cur_str_len +=1 #長度

            #debug用
            print(f"i = {i}")
            print(f"dict = {dict}")

            #判別當前長度是不是最長字串
            if cur_str_len > max_str_len:
                print(f"find larger len {cur_str_len}")
                max_str_len = cur_str_len

        return max_str_len
sol = Solution()
#print(sol.lengthOfLongestSubstring("abcaebcbb"))
print(sol.lengthOfLongestSubstring("pwwkew"))