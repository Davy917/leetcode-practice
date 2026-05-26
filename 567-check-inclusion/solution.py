#自己寫出來的

from collections import defaultdict
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        counting = defaultdict(int)
        for c in s1:
            counting[ord(c)] += 1
        need_count = len(s1)

        l, r = 0, 0
        while r < len(s2):
            if counting[ord(s2[r])] > 0:
                need_count -= 1
                if need_count == 0:
                    return True
            counting[ord(s2[r])] -= 1
            print(f"counting{counting}, need_count = {need_count}")
            if r-l+1 == len(s1):
                counting[ord(s2[l])] += 1
                if counting[ord(s2[l])] > 0:
                    need_count += 1
                l += 1
            r += 1

        return False
if __name__ == "__main__":
    s1 = "adc"
    s2 = "ddcda"
    print("Ans = ", Solution().checkInclusion(s1, s2))

"""
參考代碼:
438-find-anagrams/solution.go
076-min-window/solution.go
"""