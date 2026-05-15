"""
雙指針, 自己寫出來的
"""
from itertools import count
from typing import List
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        if len(nums) == 0:
            return 0

        i = 0
        j = len(nums) - 1

        while i < j:
            while i < j and nums[j] == val:
                j -= 1
            if nums[i] == val:
                nums[i], nums[j] = nums[j], nums[i]
            i += 1
        print(nums)
        result = 0
        for num in nums:
            if num != val:
                result += 1

        return result
    
        """
        最後的迴圈, 可以用生成式替代
        return sum(1 for num in nums if num != val)
        """

if __name__ == "__main__":
    arr = [2, 1]
    target = 1
    print("Ans = ", Solution().removeElement(arr, target))

"""
Copilot給出可以這樣優化:
def removeElement(self, nums: List[int], val: int) -> int:
    i = 0
    j = len(nums) - 1
    
    while i <= j:
        if nums[i] == val:
            nums[i], nums[j] = nums[j], nums[i]
            j -= 1
        else:
            i += 1
    
    return i
"""
