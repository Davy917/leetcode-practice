#自己寫的
from collections import defaultdict
from typing import List
class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        dd = defaultdict(int)
        cur_count, max_count = 0, 0
        l, r = 0, 0
        while r < len(fruits):
            dd[fruits[r]] += 1
            cur_count += 1
            while len(dd) > 2:
                dd[fruits[l]] -= 1
                cur_count -= 1
                if dd[fruits[l]] == 0:
                    del dd[fruits[l]]
                l += 1
            max_count = max(max_count, cur_count)
            r += 1
        return max_count
if __name__ == "__main__":
    fruits = [3,3,3,1,2,1,1,2,3,3,4]
    print("Ans = ", Solution().totalFruit(fruits))