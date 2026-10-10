"""
go 第一版, 會超時
python 第二版, 優化過後ok
"""
import heapq
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        heap = []
        ugly_nums = [1]
        ugly_set = {1, }
        while len(ugly_nums) < n:
            cur_num = ugly_nums[-1]
            if cur_num * 2 not in ugly_set:
                ugly_set.add(cur_num * 2)
                heapq.heappush(heap, cur_num * 2)
            if cur_num * 3 not in ugly_set:
                ugly_set.add(cur_num * 3)
                heapq.heappush(heap, cur_num * 3)
            if cur_num * 5 not in ugly_set:
                ugly_set.add(cur_num * 5)
                heapq.heappush(heap, cur_num * 5)
            ugly_nums.append(heapq.heappop(heap))
        return ugly_nums[-1]
if __name__ == "__main__":
    print("Ans = ", Solution().nthUglyNumber(10))
"""
自己想的

ugly_nums be like:
[1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24]

找規律:
2 = 1 * 2
3 = 1 * 3
5 = 1 * 5
每個丑數都是之前某個丑數x2或x3或x5的結果
我只要把當前的丑數x2, x3, x5的結果加進去ugly_nums, 重複的不要加

Q: 衍生出另一個問題:
這樣順序會變成[1, 2, 3, 5, 4...]順序錯亂, 要怎麼改正? (此時拿出紙筆開始模擬)
A: 用最小堆!!
ugly_nums = [1], heap = [2, 3, 5] <--- 把1x2, 1x3, 1x5 的結果全部先塞最小堆
ugly_nums = [1, 2], heap = [3, 5] <--- 已經確定2是最小的, 把2取出放入ugly_nums
ugly_nums = [1, 2], heap = [3, 4, 5, 6, 10] <--- 把2x2, 2x3, 2x5 的結果全部先塞最小堆
ugly_nums = [1, 2, 3], heap = [4, 5, 6, 10] <--- 已經確定3是最小的, 把3取出放入ugly_nums
如此循環往復

Q: 衍生出另一個問題:
有重複的不能塞入堆中, 要怎麼檢查當前元素是否已在堆中?
A1:
我們可能會需要手寫堆, 並完成Contains, Push, Pop, heapify等方法
A2:
或是直接用ugly_set檢查是否有重複的
最後選擇A2, 因為A1方案如果堆中元素太多, 可能還是會超時

結論:
ugly_nums 有序地放入丑數
ugly_set 檢查是否重複
heap 給出當前最小丑數

代碼結構:
初始化
heap = []
ugly_nums = [1]
ugly_set = {1, }

while len(ugly_nums) < n:
    curNums = ugly_nums[-1]
    ugly_set 有無包含 curNum * 2
        無-> curNum * 2 加入 ugly_set, heap
    ugly_set 有無包含 curNum * 3
        無-> curNum * 3 加入 ugly_set, heap
    ugly_set 有無包含 curNum * 5
        無-> curNum * 5 加入 ugly_set, heap

    ugly_nums.append(heapq.heappop(heap))

return ugly_nums[-1]

這版提交已經不會超時了, 但還能再優化
264-nth-ugly-number\python improve.md
"""