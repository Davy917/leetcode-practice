class bucketSort:
    @classmethod
    def bucketSort_basic(cls, arr):
        if arr is None or len(arr) == 0:
            return
        max_num = arr[0]
        min_num = arr[0]
        for i in range(1, len(arr)):
            if arr[i] > max_num:
                max_num = arr[i]
            elif arr[i] < min_num:
                min_num = arr[i]
        true_range = max_num - min_num
        print(max_num, min_num)

        bucket_amount = 100
        gap = true_range / (bucket_amount - 1)
        buckets = [[0] * len(arr) for _ in range(bucket_amount)] #注意, 不是buckets = [[0] * len(arr)] * bucket_amount
        bucket_length = [0] * bucket_amount

        for val in arr:
            index = int((val - min_num) / gap)
            buckets[index][bucket_length[index]] = val
            bucket_length[index] += 1

        index = 0
        for i in range(0, bucket_amount):
            if(bucket_length[i] == 0):
                continue
            arr_in_bucket = buckets[i][:bucket_length[i]]
            bucketSort.insertSort(arr_in_bucket)
            arr[index : index + bucket_length[i]] = arr_in_bucket
            index += bucket_length[i]

    @staticmethod
    def insertSort(arr):
        for index in range(1, len(arr)):
            visitor = index - 1
            cur_val = arr[index]
            while visitor >= 0 and arr[visitor] > cur_val:
                arr[visitor + 1] = arr[visitor]
                visitor -= 1
            arr[visitor + 1] = cur_val

if __name__ == "__main__":
    arr = [887, 522, 14, 133, 533, 987, 881, 453, 6, 23]
    bucketSort.bucketSort_basic(arr)
    print(arr)

"""
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/phtz1j/
"""