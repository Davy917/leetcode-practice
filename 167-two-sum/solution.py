from typing import List
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        Sum = []
        left = 0
        right = len(numbers) - 1

        while right > left:
            if numbers[left] + numbers[right] == target:
                Sum.append(left + 1)
                Sum.append(right + 1)
                return Sum
            elif numbers[left] + numbers[right] > target:
                right -= 1
            else:
                left += 1
        return None
print(Solution().twoSum([2,3,4], 8))