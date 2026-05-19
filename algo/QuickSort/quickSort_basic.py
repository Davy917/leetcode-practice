class quickSort:
    @classmethod
    def quickSort(cls, arr):
        #選擇一種quickSort執行
        #cls.quickSort_basic(arr, 0, len(arr) - 1)#注意, 這邊要-1
        cls.quickSort_twopointers(arr, 0, len(arr) - 1)

    @classmethod
    def quickSort_basic(cls, arr, start, end):
        if start >= end:
            return
        middle = cls.partition(arr, start, end)#注意, middle是這樣來的
        cls.quickSort_basic(arr, start, middle - 1)
        cls.quickSort_basic(arr, middle + 1, end)

    @classmethod
    def partition(cls, arr, start, end):
        pivot = arr[start]
        left = start + 1
        right = end
        while left < right:
            while left < right and pivot > arr[left]:
                left += 1
            if left != right:
                arr[left], arr[right] = arr[right], arr[left]
                right -= 1
            print("After swap", arr)

        if left == right and arr[right] > pivot:
            right -= 1
        arr[start], arr[right] = arr[right], arr[start]
        return right

    @classmethod
    def quickSort_twopointers(cls, arr, start, end):
        if start >= end:
            return
        middle = cls.partition_twopointers(arr, start, end)
        cls.quickSort_twopointers(arr, start, middle - 1)
        cls.quickSort_twopointers(arr, middle + 1, end)

    @classmethod
    def partition_twopointers(cls, arr, start, end):
        pivot = arr[start]
        left = start + 1
        right = end
        while left < right:
            while left < right and arr[left] < pivot:
                left += 1
            while left < right and arr[right] > pivot:
                right -= 1
            if left < right:
                arr[left], arr[right] = arr[right], arr[left]
                left += 1
                right -= 1
                
        #注意, 在推倒時不要忘了這一句
        if left == right and arr[right] > pivot:
            right -= 1
        arr[start], arr[right] = arr[right], arr[start]
        return right
        
if __name__ == "__main__":
    arr = [4, 2, 7, 1, 6, 3, 5]
    # quickSort.partition_twopointers(arr, 0, len(arr) - 1)
    quickSort.quickSort(arr)
    print(arr)
