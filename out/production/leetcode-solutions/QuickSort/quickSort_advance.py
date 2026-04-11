class quickSort:
    @staticmethod
    def quickSort(arr):
        quickSort.quickSort_advance(arr, 0, len(arr) - 1)

    @staticmethod
    def quickSort_advance(arr, start, end):
        if start >= end:
            return
        middle = quickSort.partition_advance(arr, start, end)
        quickSort.partition_advance(arr, start, middle - 1)
        quickSort.partition_advance(arr, middle + 1, end)

    @staticmethod
    def partition_advance(arr, start ,end):
        left = start
        right = end
        while left < right:
            while left < right and arr[right] >= arr[start]:
                right -= 1
            while left < right and arr[left] <= arr[start]:
                left += 1
            arr[left], arr[right] = arr[right], arr[left]
        arr[start], arr[right] = arr[right], arr[start]
        return right
"""
錯誤示範
    @staticmethod
    def partition_advance(arr, start, end):
        pivot = arr[start]
        left = start + 1
        right = end
        while left < right:
            while left < right and arr[right] >= pivot:
                right -= 1
            while left < right and arr[left] <= pivot:
                left += 1
            if left < right:
                arr[left], arr[right] = arr[right], arr[left]
                left -= 1
                right += 1
        arr[start], arr[right] = arr[right], arr[start]
        return right
        """
if __name__ == "__main__":
    arr = [4, 2, 7, 1, 6, 3, 5]
    quickSort.quickSort(arr)
    print("arr = ", arr)
"""
1. 情況一：pivot = arr[start], left = start (此版本)  
    1. 內部 while 迴圈條件：
        1. while left < right and arr[left] <= pivot: (左指針向右找 大於 pivot 的元素，遇到等於 pivot 的也要繼續移動。)
        2. while left < right and arr[right] >= pivot: (右指針向左找 小於 pivot 的元素，遇到等於 pivot 的也要繼續移動。)
    2. 等號的重要性： 這個等號 (<= 和 >=) 是為了避免在存在大量重複元素時陷入無限循環。
        如果沒有等號，當 left 指針遇到一個等於 pivot 的元素時，它會停下來，而 right 指針也可能遇到一個等於 pivot 的元素停下來。
        這樣 left 和 right 都無法移動，但 left < right 仍然成立，導致無限循環。加上等號，可以確保指針在遇到等於 pivot 的元素時也能繼續移動，直到找到真正需要交換的元素，或者指針交叉。
    3. 交換後指針處理： 在 if left < right: 內部執行交換後，不需要額外移動 left 和 right。外層的 while left < right 迴圈會再次執行，並再次判斷內部 while 迴圈條件，自動讓指針繼續移動。

2. 情況二：pivot = arr[start], left = start + 1 (quickSort_twopointers的版本)  
    1. 內部 while 迴圈條件：
        1.while left <= end and arr[left] < pivot: (左指針向右找 大於等於 pivot 的元素，遇到小於 pivot 的要繼續移動。)
        2.while right >= start and arr[right] > pivot: (右指針向左找 小於等於 pivot 的元素，遇到大於 pivot 的要繼續移動。)
    2. 等號的重要性： 在這種情況下，內部 while 迴圈的等號通常是不加的 (< 和 >)。
        因為指針的目標是找到「越界」的元素（即 left 找到第一個 >= pivot 的，right 找到第一個 <= pivot 的），一旦找到就停下進行交換。如果加上等號，可能會讓指針越過它真正應該交換的元素。
    3. 交換後指針處理： 在 if left < right: 內部執行交換後，必須執行 left += 1 和 right -= 1。這是因為交換後，left 和 right 指向的元素已經歸位，需要讓指針繼續向內縮小搜索範圍。
    4. 最終交換： 這種寫法在 while left < right: 循環結束後，通常是 arr[start] (pivot) 與 arr[right] 進行交換。
"""
