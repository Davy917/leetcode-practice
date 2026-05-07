"""
底層用CountingSort_Reverse實現
"""
class radixSort:
    @classmethod
    def radixSort_reverse(cls, arr):
        if arr is None:
            return
        max_val = 0
        for val in arr:
            if val > max_val:
                max_val = val
        
        max_digit_length = 0
        while max_val > 0:
            max_val = max_val // 10
            max_digit_length += 1

        dev = 1
        for digit in range(max_digit_length):
            counting = [0] * 19

            for value in arr:
                print("value", value // dev  % 10)
                print("abs(value) ", abs(value) // dev % 10)
                radix = abs(value) // dev % 10 * (-1 if value < 0 else 1) + 9
                counting[radix] += 1

            counting[0] -= 1
            for index in range(1, len(counting)):
                counting[index] += counting[index- 1]
            print("counting after prefix = ", counting)

            result = [0] * len(arr)

            for index in range(len(arr) - 1, -1, -1):
                radix = radix = abs(value) // dev % 10 * (-1 if value < 0 else 1) + 9
                result[counting[radix]] = arr[index]
                counting[radix] -= 1

            # 由前往後遍歷會遇到什麼問題? 就用註解中的for迴圈跑{211, 221}, 就能夠看到問題
            # for _, value in enumerate(arr):
            #     radix = abs(value) // dev % 10 * (-1 if value < 0 else 1) + 9
            #     result[counting[radix]] = value
            #     counting[radix] -= 1
                
            dev *= 10
            arr[0: len(arr)] = result[0: len(result)]

if __name__ == "__main__":
    arr = [520, 211, 438, 888, 7, 111, 985, 666, 996, 233, 168]
    arr2 = [211, 221]
    radixSort.radixSort_reverse(arr2)
    print("ans = ", arr2)

"""
代碼改編自:
https://leetcode.cn/leetbook/read/sort-algorithms/raydw2/
概念來自:
https://www.youtube.com/watch?v=upyiryNShAs
"""
