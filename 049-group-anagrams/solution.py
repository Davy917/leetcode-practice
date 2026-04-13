from typing import List
class Solution1122:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        dict = {}
        for str in strs:
            key = tuple(sorted(str))
            if key not in dict:
                dict[key] = []
            dict[key].append(str)
        return dict

print(Solution1122().groupAnagrams(["eat", "tea", "tan", "ate", "nat", "bat"]))