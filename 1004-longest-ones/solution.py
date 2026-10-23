"""
自己寫的java, python
自己寫的優化版go
"""
class Solution:
    def longestOnes(self, nums: list[int], k: int) -> int:
        l, r, max_len = 0, 0, 0
        while r < len(nums):
            if nums[r] == 0:
                k -= 1
                while k < 0:
                    if nums[l] == 0:
                        k += 1
                    l += 1
            max_len = max(max_len, r - l + 1)
            r += 1
        return max_len
if __name__ == "__main__":
    nums = [1,1,1,0,0,0,1,1,1,1,0]
    print("Ans = ", Solution().longestOnes(nums, 2))
    """
    想到什麼?
    滑動窗口
    left = 0, right = 0
    left = 0, right = 1
    left = 0, right = 2
    left = 0, right = 3
    left = 0, right = 4
    left = 0, right = 5 炸掉

    接下來試著擴展
    left = 1, right = 5
    left = 2, right = 5
    left = 3, right = 5
    left = 4, right = 5 <---k回到0, right可以移動了
    left = 4, right = 6
    left = 4, right = 7
    left = 4, right = 8
    left = 4, right = 9
    left = 5, right = 10

    right走到底就結束了
    """