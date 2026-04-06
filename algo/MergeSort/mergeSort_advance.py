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
        print("start =", start, "middle = ", middle, "end = ", end)
        self.mergeSort_advance(arr, start, middle, result)
        print("middle + 1 = ", middle + 1, ",end = ", end)
        self.mergeSort_advance(arr, middle + 1, end, result)
        #則一使用
        self.merge(arr, start, end, result)#why??
        self.merge_lite(arr, start, end, result)
      
    def merge(self, arr, start, end, result):
        print("in merge")
        middle = (start + end) // 2
        #arr1
        start1 = start
        end1 = middle
        #arr2
        start2 = middle + 1
        end2 = end
        #index
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

    def merge_lite(self, arr, start, end, result):
        print("in merge lite")
        middle = (start + end) // 2
if __name__ == "__main__":
    arr = [2, 6, 1, 3, 5, 4]
    mergeSort.Sort(arr)
    print(arr)
