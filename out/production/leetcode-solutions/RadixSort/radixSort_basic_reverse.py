"""
底層用CountingSort_Reverse實現
"""
class radix_arrSort:
    @classmethod
    def radix_arrSort_basic_reverse(cls, arr):
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
        radix_arr = [0] * len(arr)
        for i in range(max_digit_length):
            print("arr= ", arr)
            counting = [0] * 10
            for index in range(len(arr)):
                radix_arr[index] = arr[index] // dev % 10
                counting[radix_arr[index]] += 1
            print("radix_arr = ", radix_arr)

            counting[0] -= 1
            for index in range(1, len(counting)):
                counting[index] += counting[index- 1]
            print("counting = ", counting)

            result = [0] * len(arr)
            for index in range(len(arr) - 1, -1, -1):
                result[counting[radix_arr[index]]] = arr[index]
                counting[radix_arr[index]] -= 1
                
            dev *= 10
            arr[0: len(arr)] = result[0: len(result)]

if __name__ == "__main__":
    arr = [520, 211, 438, 888, 7, 111, 985, 666, 996, 233, 168]
    radix_arrSort.radix_arrSort_basic_reverse(arr)
    print("ans = ", arr)

"""
代碼改編自:
https://leetcode.cn/leetbook/read/sort-algorithms/raydw2/
概念來自:
https://www.youtube.com/watch?v=upyiryNShAs
"""