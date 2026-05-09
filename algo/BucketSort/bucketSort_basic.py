from ..InsertSort.insertSort import insertSort as sort
class bucketSort:
    @classmethod
    def bucketSort_basic(cls, arr):

        if arr is None or len(arr) == 0:
            return
        
        max_val = arr[0]
        min_val = arr[0]
        for i in range(1, len(arr)):
            if arr[i] > max_val:
                max_val = arr[i]
            elif arr[i] < min_val:
                min_val = arr[i]
        true_range = max_val - min_val
        if true_range == 0:  # 所有元素相同，無需排序
            return

        bucket_amount = 10
        gap = true_range / (bucket_amount - 1) #為什麼-1, 見sop
        buckets = [[0] * len(arr) for _ in range(bucket_amount)] #注意, 不是buckets = [[0] * len(arr)] * bucket_amount
        bucket_length = [0] * bucket_amount

        for val in arr:
            index = int((val - min_val) / gap) #找val屬於哪一個桶
            buckets[index][bucket_length[index]] = val #把val裝進桶中
            bucket_length[index] += 1
            print(f"bucket{index} = ",buckets[index])
            print(f"bucket_length = {bucket_length}")

        #排序每個桶中的數
        index = 0
        for i in range(0, bucket_amount):
            if(bucket_length[i] == 0):
                continue
            arr_in_bucket = buckets[i][:bucket_length[i]]
            sort.insertSort(arr_in_bucket)
            arr[index: index + bucket_length[i]] = arr_in_bucket
            index += bucket_length[i]

if __name__ == "__main__":
    arr = [55, 80, 22, 60, 18, 90, 40, 5, 70, 30]
    bucketSort.bucketSort_basic(arr)
    print(arr)

"""
執行方式:
cd C:/Users/USER/IdeaProjects/leetcode-practice
python3.12 -m algo.BucketSort.bucketSort_basic

sop:
algo/BucketSort/BucketSort_SOP.md
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/phtz1j/
講解影片:
https://www.youtube.com/watch?v=8uMEZ7aKICI
"""