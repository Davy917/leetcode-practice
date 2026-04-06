class mergeSort:
    @classmethod
    def Sort(cls, arr):
        if arr is None:
            return
        result = []
        mergeSort().mergeSort_advance(arr, 0, len(arr) - 1, result)#why not self.mergeSort_advance
    def mergeSort_advance(self, arr, start, end, result):
        if start >= end:
            return
        middle = (start + end) // 2
        print("start =", start, "middle = ", middle, "end = ", end)
        self.mergeSort_advance(arr, start, middle, result)
        print("middle + 1 = ", middle + 1, ",end = ", end)
        self.mergeSort_advance(arr, middle + 1, end, result)
        self.merge(arr, start, end, result)
      
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
        while index1 <= end1 and index2 <= end2: #注意有等號
            if arr[index1] <= arr[index2]:
                result.append(arr[index1])
                index1 += 1
            else:
                result.append(arr[index2])
                index2 += 1
        # if index1 < end1:
        #     result.extend(arr[index1:])

        # if index2 < end2:
        #     result.extend(arr[index2:])
        print(result)
        return result

if __name__ == "__main__":
    arr = [2, 6, 1, 3, 5, 4]
    mergeSort.Sort(arr)
    print(arr)
