from collections import defaultdict, deque
class countingSort:
    @classmethod
    def countingSort_stage1(cls, arr):
        #建立長度為9的數組
        counting = [0] * 9
        for element in arr:
            counting[element - 1] += 1 #細品
            print(counting)
        index = 0
        for i in range(0, 9):
            while counting[i] != 0:
                arr[index] = i + 1
                index += 1
                counting[i] -= 1

    @classmethod
    def CountingSort_stage2(cls, arr):
        """
        兩種寫法則一
        records = [deque() for _ in range(9)]
        records = defaultdict(deque)
        """
        records = defaultdict(deque)
        counting = [0] * 9

        for element in arr:
            idx = element - 1
            counting[idx] += 1
            records[idx].append(element)

        write = 0
        for i in range(0, 9):
            while counting[i] != 0:
                arr[write] = records[i].popleft() # 與 records.get(i).popleft() 等價
                write += 1
                counting[i] -= 1

    @classmethod
    def countingSort_basic(cls, arr):
        counting = [0] * 9
        for element in arr:
            idx = element - 1
            counting[idx] += 1
        print(counting)

        precount = 0
        for i in range(0, len(counting)):
            temp = counting[i]
            counting[i] = precount
            precount += temp
        print(counting)

        result = [0] * len(arr)
        for element in arr:
            position = counting[element - 1]
            result[position] = element
            counting[element - 1] += 1 #注意, 應該要自己想出來

        for i in range(len(arr)):
            arr[i] = result[i]

if __name__ == "__main__":
    arr = [8, 7, 1, 2, 8, 6, 8, 2]
    arr2 = [7, 8, 9, 7, 6, 7, 6, 8, 6, 6]
    #countingSort.countingSort_stage1(arr)
    countingSort.countingSort_basic(arr2)
    print(arr2)

"""
countingSort_basic 倒數第二個迴圈Step by Step

[ , , , , 7, , , , , ]
[ , , , , 7, , , 8, , ]
[ , , , , 7, , , 8, , 9]
[ , , , , 7, 7, , 8, , 9]


[6, 6, 6, 6, 7, 7, 7, 8, 8, 9]
"""
"""
CountingSort_stage2 解析:
records = [deque() for _ in range(9)] 這行是在一次建立 9 個彼此獨立的 queue（用 collections.deque 來當 queue），
對應計數排序裡數字 1~9 的 9 個桶子（bucket）。

等價的「一般寫法」是：
from collections import deque

records = []
for _ in range(9):
    records.append(deque())

1. range(9)
    會產生 0, 1, 2, 3, 4, 5, 6, 7, 8
    這裡的值本身不重要，只是要「重複做 9 次」。

2. for _ in range(9)
    _ 代表「我不會用到這個迴圈變數」。
    也就是：做 9 次就好，不在乎每次的 index。

3. deque()
    每做一次就建立一個新的空 deque 物件（你可以把它想成一個 queue）。

4. 外面的 [...]（list comprehension）
    把這 9 次產生的 deque() 收集成一個 list。
"""