class mergeSort:
    @classmethod
    def Sort(cls, arr):
        if arr is None:
            return
        result = [0] * len(arr) #注意不能寫result = []
        mergeSort().mergeSort_advance(arr, 0, len(arr) - 1, result) #why not self.mergeSort_advance?? 因為 Sort 方法沒有 self 參數（它是類方法）。

    def mergeSort_advance(self, arr, start, end, result):
        if start >= end:
            return
        middle = (start + end) // 2
        print(f"start = {start}, middle = {middle}, end = {end}")
        self.mergeSort_advance(arr, start, middle, result)
        print(f"middle + 1 = {middle + 1}, end = {end}")
        self.mergeSort_advance(arr, middle + 1, end, result)

        #擇一使用
        # self.merge(arr, start, end, result)
        self.merge_simply(arr, start, end, result)
      
    def merge(self, arr, start, end, result):
        print("in merge")
        middle = (start + end) // 2

        start1 = start
        end1 = middle

        start2 = middle + 1
        end2 = end

        index1 = start1 #注意
        index2 = start2 #注意
        resultindex = start1

        while index1 <= end1 and index2 <= end2: #注意有等號
            if arr[index1] <= arr[index2]:
                result[resultindex] = arr[index1]
                resultindex += 1
                index1 += 1
            else:
                result[resultindex] = arr[index2]
                resultindex += 1
                index2 += 1

        while index1 <= end1:
            result[resultindex] = arr[index1]
            resultindex += 1
            index1 += 1
          
        while index2 <= end2:
            result[resultindex] = arr[index2]
            resultindex += 1
            index2 += 1
        arr[start : end + 1] = result[start : end + 1] #切片賦值, 注意是 end + 1

    def merge_simply(self, arr, start, end, result):

        print("in merge lite")
        middle = (start + end) // 2
        start2 = middle + 1
        index1 = start
        index2 = start2

        while index1 <= middle and index2 <= end: #注意有等號
            if arr[index1] <= arr[index2]:
                result[index1 + index2 - start2] = arr[index1]
                index1 += 1
            else:
                result[index1 + index2 - start2] = arr[index2]
                index2 += 1

        while index1 <= middle:
            result[index1 + index2 - start2] = arr[index1]
            index1 += 1
        while index2 <= end:
            result[index1 + index2 - start2] = arr[index2]
            index2 += 1

        arr[start: end + 1] = result[start: end + 1]

if __name__ == "__main__":
    arr = [2, 6, 1, 3, 5, 4]
    mergeSort.Sort(arr)
    print(arr)

"""
為什麼是index1 + index2 - start2 ?
實際跑一次就知道
假設初始:
[1, 2, 6, 3, 4, 5]
index1 = 0
index2 = 3

Loop1:
result[0 + 3 - 3] = arr[index1]
result = [1]
index1 = 1
index2 = 3

Loop2:
result[1 + 3 - 3] = arr[index1]
result = [1, 2]
index1 = 2
index2 = 3

Loop3:
result[2 + 3 - 3] = arr[index2]
result = [1, 2, 3]
index1 = 2
index2 = 4

Loop4:
result[2 + 4 - 3] = arr[index2]
result = [1, 2, 3, 4]
index1 = 2
index2 = 5

Loop5:
result[2 + 5 - 3] = arr[index2]
result = [1, 2, 3, 4, 5]
index1 = 2
index2 = 6

Loop6:
result[2 + 6 - 3] = arr[index1]
result = [1, 2, 3, 4, 5, 6]
index1 = 3
index2 = 6

自己的見解:
要知道每次被操作的都是 index1/index2
所以result[]裡面放的勢必也要是 index1/index2, 否則result沒辦法隨著迴圈迭代
而放了index1/index2 之後還要能對到正確位置就只能寫成 index1 + index2 - start2
"""