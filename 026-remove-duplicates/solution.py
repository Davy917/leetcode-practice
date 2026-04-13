#Python用列表,java用雙指針
from typing import List
class Solution1122:
    def removeDuplicates(self, nums: List[int]) -> int:

        #寫一個從列表後面開始的迴圈
        for i in range (len(nums)-1, -1, -1):
            #如果前面已經沒有數字了,中斷
            if i == 0:
                return len(nums)
            #如果當前數字等於前一數字,刪掉當前數字
            if(nums[i] == nums[i-1]):
                del nums[i]
            else:
                continue
result = Solution1122().removeDuplicates([1, 1, 2, 2, 6, 6, 6])
print(result)