"""
底層依然用CountingSort實現, 但是advance版本可以排序負數
不再有 radix_arr, 全部由radix取代節省空間
"""
class radixSort:
    @classmethod
    def radixSort_advance(cls, arr):
        if arr is None:
            return
        max_val = 0
        for val in arr:
            #注意這邊要取絕對值
            if abs(val) > abs(max_val):
                max_val = abs(val)

        max_digit_length = 0
        while max_val > 0:
            max_val = max_val // 10
            max_digit_length += 1

        dev = 1
        for i in range(max_digit_length):
            print("arr = ", arr)
            counting = [0] * 19 #注意
            for val in arr:
                radix = (abs(val) // dev % 10) * (-1 if val < 0 else 1) + 9 #注意, 不是radix = val // dev % 10 + 9
                counting[radix] += 1

            counting[0] -= 1
            for index in range(1, len(counting)):
                counting[index] += counting[index - 1]
            print("counting = ", counting)

            result = [0] * len(arr)
            for index in range(len(arr) - 1, -1, -1):
                radix = (abs(arr[index]) // dev % 10) * (-1 if arr[index] < 0 else 1) + 9
                result[counting[radix]] = arr[index]
                counting[radix] -= 1

            dev *= 10
            arr[0: len(arr)] = result[0: len(result)]

if __name__ == "__main__":
    arr = [520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168]
    radixSort.radixSort_advance(arr)
    print("ans = ", arr)

"""
代碼改編自:
https://leetcode.cn/leetbook/read/sort-algorithms/raydw2/
概念來自:
https://www.youtube.com/watch?v=upyiryNShAs
"""