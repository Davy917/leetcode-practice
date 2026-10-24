from collections import defaultdict
from typing import List
class Solution:
    def groupStrings(self, strings: List[str]) -> List[List[str]]:
        dd = defaultdict(list)
        key = ()
        for Str in strings:
            temp = []
            for i in range(1, len(Str)):
                temp.append((ord(Str[i]) - ord(Str[i-1]) + 26) % 26)
            key = tuple(temp)
            print(key) #debug
            dd[key].append(Str)

        return [dd[key] for key in dd]
if __name__ == "__main__":
    strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
    print(Solution().groupStrings(strings))
"""
自己寫的

歸類
abc, bcd, xyz
az, ba
a, z
acef

在 Python 中要取得字元的 ASCII 值進行算術運算，用 ord() 和 chr()
要先把字串轉成移位序列
abc = (1, 1)
bcd = (1, 1)
xyz = (1, 1)
acef = (2, 2, 1)
az = (25,)
ba = (-1,)
a = ()
z = ()


再細看
az = (25,)
ba = (-1,)

這兩組要歸在一類, 所以要做個小修正
As-is: temp.append((ord(Str[i]) - ord(Str[i-1]))
To-be: temp.append((ord(Str[i]) - ord(Str[i-1]) + 26) % 26)

az = (25,)
ba = (25,)

defaultdict的版本--->更簡潔
m[key].append(Str)

dict的版本
m.setdefault(key, []).append(Str)
"""