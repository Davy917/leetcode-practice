#最初版, Hash Map + 大小為 K 的最小堆
from collections import defaultdict
class Solution:
    def heapify(self, nums: list[int], n: int, parent: int):
            if parent >= n:
                return
            left_child = parent * 2 + 1
            right_child = parent * 2 + 2
            smallest = parent
            if left_child < n and nums[left_child] < nums[smallest]:
                smallest = left_child
            if right_child < n and nums[right_child] < nums[smallest]:
                smallest = right_child
            if smallest != parent:
                nums[parent], nums[smallest] = nums[smallest], nums[parent]
                self.heapify(nums, n, smallest)

    def topKFrequent(self, nums: list[int], k: int) -> list[int]:

        freq = [0] * 20001
        for num in nums:
            if num >= 0:
                freq[num] += 1
            else:
                freq[abs(num) + 10000] += 1 #-1放freq[10001], -2放[10002]...

        min_heap = [0] * k
        d = defaultdict(list)
        for i in range(len(freq)):
            if freq[i] > 0:
                if i <= 10000:
                    d[freq[i]].append(i)
                else:
                    d[freq[i]].append(-i+10000)
                if freq[i] > min_heap[0]:
                    min_heap[0] = freq[i]
                    self.heapify(min_heap, k, 0)
        print(d)
        ans = []
        for num in min_heap:
            ans.append(d[num].pop())

        return ans

if __name__ == "__main__":
    nums = [1,2,2,1,1,3,5,5,5,5,6,6,6,6]
    print(Solution().topKFrequent(nums, 3))

"""
自己寫的:
一定要看過nums, 所有元素才有辦法確定前k 個 topFrequent是誰
提示:
-10000 <= nums[i] <= 10000
得出:
用大小為 20001 的陣列freq, 可統計完, 每個數字出現幾次
下標 0 ~ 10000, 放置 0 ~ 10000 出現幾次
下標 10001 ~ 20001, 放置 -1 ~ -10000 出現幾次

例如:
freq = [0, 3, 2, 1, 0, 4, 4]
[0]次, [3]次, [2]次, [1]次, [0]次, [4]次, [4]次
0出現, 1出現, 2出現,  3出現, 4出現, 5出現, 6出現

有了freq之後
1. 把freq轉成一個字典
2. 過程中可以順便入堆

字典的用途是希望可以記錄:
出現3次的有誰
出現2次的有誰
出現1次的有誰
出現4次的有誰

{3: [1], 2: [2], 1: [3], 4: [5, 6]}

用此字典, 可得到出現最多次的前幾名是誰

具體作法--->heap
heap邏輯:
用大小為k的最小堆
堆頂是最小freq, 也就是下一個freq比它大, 我們可以直接把堆頂換掉, 換掉之後再做一次heapify

假設k = 3, 初始heap = [0, 0, 0]
讀到3, heap = [3, 0, 0], heapify後[0, 0, 3]
讀到2, heap = [2, 0, 3], heapify後[0, 2, 3]
讀到1, heap = [1, 2, 3], heapify後[1, 2, 3]
讀到4, heap = [4, 2, 3], heapify後[2, 3, 4]
讀到4, heap = [4, 3, 4], heapify後[3, 4, 4]

heap = [3, 4, 4]
把字典鍵為3的倒出來--->1
把字典鍵為4的倒出來--->5
把字典鍵為4的倒出來--->6

得到答案[1, 5, 6]

優化思路:
347-top-k-frequent\python improve.md
"""