#from typing import Dict
class Solution1122:
    def subarraySum(self, nums: list[int], k: int) -> int:
        #初始化
        count = 0
        pre = 0
        map : dict[int, int] = {0: 1}

        for i in range(len(nums)):

            pre += nums[i]
            print("pre = ", pre)

            #注意這邊count不是單純+1
            # count += 1 if pre - k in map else 0

            count += map[pre - k] if pre - k in map else 0
            print("count = ", count)

            map[pre] = map.get(pre, 0) + 1
            print("map = ", map)

        return count

if __name__ == "__main__":
    sol = Solution1122()
    #nums = [3, 4, 7, 2, -3, 1, 4, 2]
    nums = [1, 1, 1]
    k = 2
    print(sol.subarraySum(nums, k))

"""
舉例:
nums = [3, 4, 7, 2, -3, 1, 4, 2]
pre = [3, 7, 14, 16, 13, 14, 18, 20]
k=7

初始化
pre = 0
map = {(0,1)}
count = 0

       ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 3
pre - k = -4
map = {(0,1), (3,1)}
#-4不存在map中，繼續遍歷
count = 0


          ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 7
pre - k = 0
map = {(0,1), (3,1), (7,1)}
#0存在map中，count+1，繼續遍歷
count = 1

             ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 14
pre - k = 7
map = {(0,1), (3,1), (7,1), (14,1)}
#7存在map中，count+1，繼續遍歷
count = 2

                 ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 16
pre - k = 9
map = {(0,1), (3,1), (7,1), (14,1), (16,1)}
#9不存在map中，繼續遍歷
count = 2

                     ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 13
pre - k = 6
map = {(0,1), (3,1), (7,1), (14,1), (16,1), (13, 1)}
#6不存在map中，繼續遍歷
count = 2

                         ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 14
pre - k = 7
map = {(0,1), (3,1), (7,1), (14,2), (16,1), (13, 1)}
#7存在map中，count+1，繼續遍歷
count = 3

                             ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 18
pre - k = 11
map = {(0,1), (3,1), (7,1), (14,2), (16,1), (13,1), (18,1)}
#11不存在map中，繼續遍歷
count = 3

                                 ▼
pre = [3, 7, 14, 16, 13, 14, 18, 20]
pre = 20
pre - k = 13
map = {(0,1), (3,1), (7,1), (14,2), (16,1), (13,1), (18,1), (20,1)}
#13存在map中，count+1，遍歷完成
count = 4

pre - k 的意义：这个检查的意义在于，如果 pre - k 存在于 Map 中，说明之前在某个点的累积和是 pre - k。
由于当前的累积和是 pre，这意味着从那个点到当前点的子数组之和恰好是 k（因为 pre - (pre - k) = k）。

如何使用这个信息：如果 pre - k 在 Map 中，那么 pre - k 出现的次数表示从不同的起始点到当前点的子数组和为 k 的不同情况。
这是因为每一个 pre - k 都对应一个起点，使得从那个起点到当前点的子数组和为 k。

因此，每当我们找到一个 pre - k 存在于 Map 中时，我们就把它的计数（即之前这种情况发生的次数）加到 count 上，
因为这表示我们又找到了相应数量的以当前元素结束的子数组，其和为 k。

"""