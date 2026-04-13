class Solution1122:
    def firstUniqChar(self, s: str) -> int:
        map1 = {}
        
        for i in range(len(s)):
            if map1.get(s[i]) == None:
                map1[s[i]] = 0
            map1[s[i]] += 1
        
        counter = 0
        for i in s:
            if map1[i] == 1:
                return counter
            counter += 1
        return -1

print(Solution1122().firstUniqChar("dddccdbba"))