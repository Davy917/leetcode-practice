from collections import defaultdict
class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        l, r = 0 , 0
        max_len = 0
        dd = defaultdict(int)
        for char in s:
            dd[char] += 1
            while len(dd) > 2:
                dd[s[l]] -= 1
                if dd[s[l]] == 0:
                    dd.pop(s[l])
                l += 1
            max_len = max(max_len, r-l+1)
            r += 1
        return max_len

if __name__ == "__main__":
    s = "eceba"
    print("Ans = ", Solution().lengthOfLongestSubstringTwoDistinct(s))
    
"""
自己寫的, 滑動窗口
需要一個哈希集合來記錄當前窗口有多少不同字元

e [e: 1]
ec [e: 1, c: 1]
ece [e: 2, c: 1]
eceb [e: 2, c: 1, b: 1], len(map) > 2, 炸掉
ceb [e: 1, c: 1, b: 1], len(map) > 2, 還是炸掉
eb [e: 1, b: 1], len(map) == 2, 繼續往下
eba [e: 1, b: 1, a: 1], len(map) > 2, 炸掉
ba [b: 1, a: 1], len(map) == 2, 繼續往下
r == len(s) 退出
max_len = 3
"""