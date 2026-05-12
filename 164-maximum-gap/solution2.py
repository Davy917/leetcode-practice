class Solution_v2:
    def maximumGap(self, nums: list[int]) -> int:
        max_val = max(nums)
        min_val = min(nums)

        gap = max(1, (max_val - min_val) // (len(nums) - 1)) #官方解法命名為d
        bucket_amount = (max_val - min_val) // gap + 1 #官方解法命名為bucket_size

        # 每個 bucket 存 [桶內最小值, 桶內最大值] [-1, -1] 表示空桶
        buckets = [[-1, -1] for _ in range(bucket_amount)]
        print(f"gap = {gap}, bucketAmount = {bucket_amount}\nbuckets = {buckets}\n")

        for num in nums:
            index = (num - min_val) // gap
            if buckets[index][0] == -1:
                buckets[index][0] = num
                buckets[index][1] = num
            else:
                buckets[index][0] = min(buckets[index][0], num)
                buckets[index][1] = max(buckets[index][1], num)

        print("After sync, buckets = ", buckets)
        ans = 0
        prev = -1
        for index in range(bucket_amount):
            if buckets[index][0] == -1:
                continue
            if prev != -1:
                ans = max(ans, buckets[index][0] - buckets[prev][1])
            prev = index
        return ans
if __name__ == "__main__":
    arr = [55, 12, 80, 22, 14, 60, 18, 90, 16, 40, 5, 70, 30, 17, 0, 99]
    print("Ans = ", Solution_v2().maximumGap(arr))
"""
力扣題解:
https://leetcode.cn/leetbook/read/sort-algorithms/ph60kd/

列表推倒式:
LanguagePractice/PythonPractice/list_comprehension.py
164-maximum-gap/list_comprehension.md

gap 以及 bucket_amount的算法:

"""

