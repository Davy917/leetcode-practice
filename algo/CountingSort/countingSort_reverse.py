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
            idx = element - min #why min?? test arr by hand will know
            counting[idx] += 1
        print(counting)
        counting[0] -= 1 #why?
        for i in range(1, total_range):
            counting[i] += counting[i-1]
        print(counting)
        result = [0] * len(arr)
        for i in range(len(arr)-1, -1, -1):
            position = counting[arr[i] - min]
            result[position] = arr[i]
            counting[arr[i] - min] -= 1
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
[0, 2, 2, 2, 2, 3, 4]
 ↑
val = 2
"""
"""
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/ozyo63/
"""
