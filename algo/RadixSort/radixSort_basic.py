"""
底層用CountingSort實現
"""
class radixSort:
    @classmethod
    def radixSort_basic(cls, arr):
        if arr is None:
            return
        maxVal = 0
        for val in arr:
            if val > maxVal:
                maxVal = val
        
        maxDigitLength = 0
        while maxVal != 0:
            maxVal = maxVal // 10
            maxDigitLength += 1

        dev = 1

        for i in range(0, maxDigitLength):

            print("arr = ", arr)
            radix_arr = []
            counting = [0] * 10

            for val in arr:
                radix = val // dev % 10 #映射出當前位數的真實數值
                radix_arr.append(radix)
                counting[radix] += 1
            print("radix_arr = ", radix_arr)

            preSum = 0
            for index in range(0, len(counting)):
                temp = counting[index]
                counting[index] = preSum
                preSum += temp
            print("counting = ", counting)

            """
            自己想出來的第一版:
            result = [0] * len(arr)
            index = 0
            for val in radix_arr:
                result[counting[val]] = arr[index]
                counting[val] += 1
                index += 1
            改成如下精簡版
            """
            result = [0] * len(arr)
            for index in range(len(arr)):
                position = counting[radix_arr[index]]
                result[position] = arr[index]
                counting[radix_arr[index]] += 1

            print("result = ", result)
            arr[0: len(arr)] = result[0: len(result)]
            dev *= 10

if __name__ == "__main__":
    arr = [520, 211, 438, 888, 7, 111, 985, 666, 996, 233, 168]
    radixSort.radixSort_basic(arr)
    print("ans = ", arr)

"""
i = 0
Before
0, 1, 8, 8, 7, 1, 5, 6, 6, 3, 8
After
0, 1, 1, 3, 5, 6, 6, 7, 8, 8, 8
counting
1, 2, 0, 1, 0, 1, 2, 1, 3, 0
0, 1, 3, 3, 4, 4, 5, 7, 8, 11

i = 1
Before
2, 1, 3, 8, 0, 1, 8, 6, 9, 3, 6
After
0, 1, 1, 2, 3, 3, 6, 6, 8, 8, 9
counting
1, 2, 1, 2, 0, 0, 2, 0, 2, 1
0, 1, 3, 4, 6, 6, 6, 8, 8, 10

代碼改編自:
https://leetcode.cn/leetbook/read/sort-algorithms/raydw2/
概念來自:
https://www.youtube.com/watch?v=upyiryNShAs
"""