"""
建議先把bucketSort學完再來學radixSort_MSD
"""
class radixSort:
    @staticmethod
    def radixSort(arr):
        if arr is None:
            return
        max_val = 0
        
        for val in arr:
            if val > max_val:
                max_val = val

        max_digit_length = 0
        while max_val != 0:
            max_val = max_val // 10
            max_digit_length += 1
            
        radixSort.radixSort_MSD(arr, 0, len(arr) - 1, max_digit_length)

    @staticmethod
    def radixSort_MSD(arr, start, end, position):

        print("arr = ", arr)

        if start == end or position == 0:
            return
        
        counting = [0] * 19
        dev = pow(10, position - 1)
        result = [0] * (end - start + 1)

        for index in range(start, end + 1):
            radix = abs(arr[index]) // dev % 10 * (-1 if arr[index] < 0 else 1) + 9
            counting[radix] += 1
        print(counting)

        counting[0] -= 1
        for index in range(1, len(counting)):
            counting[index] += counting[index - 1]
        print(counting)

        counting_copy = [0] * len(counting)
        counting_copy[0: len(counting_copy)] = counting[0: len(counting)]

        for index in range(end, start - 1, -1):
            radix = abs(arr[index]) // dev % 10 * (-1 if arr[index] < 0 else 1) + 9
            result[counting[radix]] = arr[index]
            counting[radix] -= 1

        arr[start:end+1] = result #注意
        print("arr = ", arr)

        for i in range(0, len(counting_copy)):
            bucket_local_start = 0 if i == 0 else counting_copy[i-1] + 1
            bucket_local_end = counting_copy[i]

            bucketStart = start + bucket_local_start
            bucketEnd = start + bucket_local_end

            if bucketStart < bucketEnd:  # 空桶跳過
                radixSort.radixSort_MSD(arr, bucketStart, bucketEnd, position - 1)

if __name__ == "__main__":
    arr = [520, -211, 438, -888, 7, 111, 985, 666, -996, 234, 231]
    radixSort.radixSort(arr)
    print("ans = ", arr)