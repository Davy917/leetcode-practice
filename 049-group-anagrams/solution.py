"""
第一次練習時寫的 java
八個月後再寫的python
"""
from typing import List
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        m: dict[str, List[str]] = {}
        for Str in strs:
            og_str = Str
            sort_str = "".join(sorted(Str))
            m.setdefault(sort_str, []).append(og_str)

        return [m[key] for key in m]
if __name__ == "__main__":
    strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    print("Ans = ", Solution().groupAnagrams(strs))

"""
自己寫的, 哈希映射
sort_str = 對str排序, 可以用sort_str 來判斷其它str是否為字母異位詞

代碼結構:
m = dict()
for str in strs:
    og_str = str
    sort_str = 對str排序
    字典裡面有sort_str了嗎
    有
        og_str 加入 m[sort_str]
    無
        m 新增一組[sort_str: [og_str]]


檢查字典有無sort_str直接壓成一行
m.setdefault(sort_str, []).append(og_str)

用defaultdict能再更簡潔
m[sort_str].append(og_str)
"""