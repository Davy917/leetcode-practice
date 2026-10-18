"""
官方解看js
自己寫的看python
"""
from typing import List
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        s = set()
        for i in range(len(nums)):
            s.add(nums.pop(i))
            diff_size = len(s.difference(nums))
            if diff_size == 1:
                return s.pop()
            nums.insert(i, s.pop())
        return -1 #理論上不會走到這
if __name__ == "__main__":
    nums = [4,1,2,1,2]
    print("Ans = ", Solution().singleNumber(nums))

"""
自己寫的這版提交會過, 但時間複雜度是O(n方)而不是O(n)

不符合題目限制的O(n) + O(1)
"""