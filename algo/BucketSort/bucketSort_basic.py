from InsertSort.insertsort import InsertSort
class BucketSort:
    @classmethod
    def bucket_sort_basic(cls, arr):

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
        gap = true_range / (bucket_amount - 1) #不一定要這樣寫, 可以參考 FAQ-what is gap.md
        buckets = [[0] * len(arr) for _ in range(bucket_amount)] #注意, 不是buckets = [[0] * len(arr)] * bucket_amount
        bucket_length = [0] * bucket_amount

        for val in arr:
            index = int((val - min_val) / gap) #找val屬於哪一個桶
            buckets[index][bucket_length[index]] = val #把val裝進桶中
            bucket_length[index] += 1
            print(f"bucket{index} = ",buckets[index])
            print(f"bucket_length = {bucket_length}")

        #排序每個桶中的數
        result_index = 0
        result = [len(arr)]
        for index in range(0, bucket_amount):
            if(bucket_length[index] == 0):
                continue
            arr_in_bucket = buckets[index][:bucket_length[index]]
            InsertSort.sort(arr_in_bucket)
            result[result_index : result_index + bucket_length[index]] = arr_in_bucket
            result_index += bucket_length[index]
        return result

if __name__ == "__main__":
    nums = [55, 80, 22, 60, 18, 90, 40, 5, 70, 30]
    print(BucketSort.bucket_sort_basic(nums))

"""
執行方式:
cd C:/Users/USER/IdeaProjects/leetcode-practice
python3.12 -m algo.BucketSort.bucketSort_basic

sop:
algo/BucketSort/BucketSort_SOP.md
algo/BucketSort/FAQ-what is gap.md

代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/phtz1j/
講解影片:
https://www.youtube.com/watch?v=8uMEZ7aKICI

gap = true_range / (bucket_amount - 1)
目的是讓 min 對應 0 桶、max 對應最後一桶（bucketAmount-1）。

這種分桶公式下（index = (val - min) / gap）它很合理，但注意 3 件事：
這是 浮點 gap
bucketAmount 必須 > 1
仍建議對 index 做 clamp（浮點誤差可能讓最大值算成 bucketAmount）
"""