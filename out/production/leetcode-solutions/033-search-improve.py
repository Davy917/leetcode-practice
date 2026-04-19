"""
代碼參考模板:
algo/BinarySearch/BinarySearch_basic.java
"""
class solution(object):
    def search(self, nums : list, target : int):
        left = 0
        right = len(nums) - 1
        while left <= right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            #判斷middle的左半邊是否排序好
            if nums[left] <= nums[middle]:#注意等號, 沒加的話[3, 1] 1 會出錯
                if nums[left] <= target < nums[middle]:
                    right = middle - 1
                else:
                    left = middle + 1
            #判斷middle的右半邊是否排序好
            else:
                if nums[middle] < target <= nums[right]:
                    left = middle + 1
                else:
                    right = middle - 1
            if nums[middle] == target:
                return middle
        return -1
if __name__ == "__main__":
    nums = [3, 1]
    target = 1
    sol = solution()
    ans = sol.search(nums, target)
    print("ans = ", ans)
"""
舉例左半邊沒排序好, 但是右半邊卻排好了的情形
nums = [6, 7, 0, 1, 2, 3, 4, 5]
前提:
左右兩邊一定有一邊沒排好

判斷四種情形:
左半邊排序好了, 且target也在左邊
左半邊排序好了, 但target在右半邊
右半邊排序好了, 且target也在右邊
右半邊排序好了, 但target在左半邊
"""
