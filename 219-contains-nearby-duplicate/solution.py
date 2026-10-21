class Solution:
    def containsNearbyDuplicate(self, nums: list[int], k: int) -> bool:
        m = {}
        index = 0
        for num in nums:
            if num in m:
                if abs(index - m[num]) <= k:
                    return True
                else:
                    m[num] = index
            else:
                m[num] = index
            index += 1

        return False
if __name__ == "__main__":
    nums = [1, 2, 3, 1]
    print("Ans = ", Solution().containsNearbyDuplicate(nums, 3))

"""
自己寫的
應該能夠做到遍歷nums找到答案
容易忽略:
nums = [1, 0, 1, 1], k = 1
1--->map[1, 0]
0--->map[0, 1]
1--->map[1, 2], 2 - 0 沒有>= 1, 所以map[1]應該被更新
1--->map[1, 3]

代碼結構:
for index, num 遍歷nums
    num有在map中嗎?
        有
            abs(index - map[num]) 有 >= k嗎
                有--->return true
                沒有--->更新map[num]
        沒有
            加入map
"""