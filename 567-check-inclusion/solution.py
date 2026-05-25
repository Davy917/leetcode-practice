from collections import defaultdict
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        counting = defaultdict(int)
        for c in s1:
            counting[ord(c)] += 1
        need_count = len(s1)
        l, r = 0, 0
        while r < len(s1):
            if counting[ord(s2[r])] > 0:
                need_count -= 1
                if need_count == 0:
                    return True
            counting[ord(s2[r])] -= 1
            if r-l+1 == len(s1):
                counting[ord(s2[l])] += 1
                need_count += 1
                l += 1
            r += 1
        return False
if __name__ == "__main__":
    s1 = "adc"
    s2 = "dcda"
    print("Ans = ", Solution().checkInclusion(s1, s2))