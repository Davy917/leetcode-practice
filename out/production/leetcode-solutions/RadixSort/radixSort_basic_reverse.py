#底層用CountingSort_Reverse實現
class radixSort:
    @classmethod
    def radixSort_basic_reverse(cls, arr):
        if arr is None:
            return
        max_num = arr[0]
        for val in arr:
            if val > max_num:
                max_num = val
        
        max_digit_length = 0
        while max_num > 0:
            max_num = max_num // 10
            max_digit_length += 1

        dev = 1
        radix = [0] * len(arr)
        for i in range(max_digit_length):
            print("arr= ", arr)
            counting = [0] * 10
            for index in range(len(arr)):
                radix[index] = arr[index] // dev % 10
                counting[radix[index]] += 1
            print("radix = ", radix)

            counting[0] -= 1
            for index in range(1, len(counting)):
                counting[index] += counting[index- 1]
            print("counting = ", counting)

            result = [0] * len(arr)
            for index in range(len(arr) - 1, -1, -1):
                result[counting[radix[index]]] = arr[index]
                counting[radix[index]] -= 1
                
            dev *= 10
            arr[0: len(arr)] = result[0: len(result)]

if __name__ == "__main__":
    arr = [520, 211, 438, 888, 7, 111, 985, 666, 996, 233, 168]
    radixSort.radixSort_basic_reverse(arr)
    print("ans = ", arr)

"""
代碼改編自:
https://leetcode.cn/leetbook/read/sort-algorithms/raydw2/
概念來自:
https://www.youtube.com/watch?v=upyiryNShAs
"""