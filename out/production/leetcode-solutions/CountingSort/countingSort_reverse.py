class countingSort:
    @classmethod
    def countingSort_reverse(cls, arr):
        if arr is None or len(arr) <= 1:
            return arr
        max = arr[0]
        min = arr[0]
        for i in range(1, len(arr)):
            if arr[i] > max:
                max = arr[i]
            elif arr[i] < min:
                min = arr[i]
        total_range = max - min + 1

        counting = [0] * total_range
        for element in arr:
            idx = element - min #why min? 手動跑一次arr就知道了
            counting[idx] += 1
        print(counting)

        #精簡寫法
        counting[0] -= 1 #why? 見sop
        for i in range(1, total_range):
            counting[i] += counting[i-1]
        print(counting)

        """
        直覺寫法如下:
        # 1) 先把 counting 變成「≤ 該值的累計個數」
        for i in range(1, total_range):
            counting[i] = counting[i] + counting[i - 1]
        print(counting)
        
        # 2) 再把它變成「最後索引」（0-based）
        #    last_index = (累計個數) - 1
        for i in range(total_range):
            counting[i] -= 1
        print(counting)
        """


        result = [0] * len(arr)
        #精簡寫法
        for i in range(len(arr)-1, -1, -1):
            result[counting[arr[i] - min]] = arr[i]
            counting[arr[i] - min] -= 1
        """
        直覺寫法:
        for i in range(len(arr)-1, -1, -1):
            val = arr[i]          # 當前元素
            idx = val - min       # 在 counting 裡的偏移位置
            result[counting[idx]] = val   # 放到對應的輸出位置
            counting[idx] -= 1            # 下一個相同值往前一格
        """
        
        for i in range(len(arr)):
            arr[i] = result[i]

if __name__ == "__main__":
    arr = [8, 7, 2, 8, 6, 8, 2]
    arr2 = [7, 8, 9, 7, 6, 7, 6, 8, 6, 6]
    countingSort.countingSort_reverse(arr)
    print(arr)

"""
[0, 0, 0, 0, 0, 0, 0]
[2, 0, 0, 0, 1, 1, 3]
[1, 1, 1, 1, 2, 3, 6]
 ↑
val = 2
"""
"""
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/ozyo63/
"""
