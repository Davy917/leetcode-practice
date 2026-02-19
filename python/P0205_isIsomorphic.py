class Solution:
    def P0205_isIsomorphic(self, s: str, t: str) -> bool:
        map1 = {}
        map2 = {}
        for i in range(len(s)):
            if map1.get(s[i]) and map1[s[i]] != t[i]:
                return False
            if map2.get(t[i]) and map2[t[i]] != s[i]:
                return False
            
            map1[s[i]] = t[i]
            map2[t[i]] = s[i]
        return True

print(Solution().P0205_isIsomorphic("ego", "add"))