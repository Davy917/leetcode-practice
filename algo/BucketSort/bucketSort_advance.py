"""
空間複雜度優化版
"""
from ..InsertSort.InsertSort import insertSort as sort

class bucketSort:
    def __init__(self):
        self.bucket_amount = 10
        
    def bucketSort_advance(self, arr):

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

        if true_range == 0:
            return
        
        gap = true_range / (self.bucket_amount - 1)
        buckets = [[] for _ in range(self.bucket_amount)]
        print(buckets)

        #裝桶
        for val in arr:
            index = int((val - min_val) / gap) #注意不是 //gap
            buckets[index].append(val)

        print("buckets = ", buckets)

        last_sorted = 0
        for index in range(0, self.bucket_amount):
            if not buckets[index]:
                continue
            sort.insertSort(buckets[index])
            print(f"bucket{index} = ", buckets[index])
            arr[last_sorted: last_sorted + len(buckets[index])] = buckets[index]#注意, 不是buckets[:len(buckets[i])]
            last_sorted += len(buckets[index])
        
if __name__ == "__main__":
    arr = [55, 80, 22, 60, 18, 90, 40, 5, 70, 30]
    bucket_sort = bucketSort()
    bucket_sort.bucketSort_advance(arr)
    print(arr)

"""
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/phtz1j/

執行方式:
cd C:/Users/USER/IdeaProjects/leetcode-practice
python3.12 -m algo.BucketSort.bucketSort_advance

#注意事項
different between:
index = int((val - min_val) / gap)
index = (val - min_val) // gap


重點差 3 個：

1. 取整規則不同
    int(x): 截斷小數，朝 0 靠近
    例如 int(3.9)=3, int(-3.9)=-3
    //: 向下取整（floor）
    例如 3.9 // 1 = 3.0, -3.9 // 1 = -4.0

2. 結果型別可能不同
    int((val - min_val) / gap) 的結果是整數 int，可直接拿來當 list index
    (val - min_val) // gap 如果 gap 是 float，結果通常是 float（像 3.0），直接當 index 會報錯（list index 必須是 int）

3. 在這個 bucket sort 情境
    val - min_val 幾乎都 >= 0，所以兩者數值常常看起來一樣
    但 // 可能得到 float，不適合直接索引
    建議用 int(...)，並加邊界保護避免浮點誤差

    建議寫法：
    index = min(self.bucket_amount - 1, int((val - min_val) / gap))
    這樣可避免 max_val 因浮點誤差落到不存在的桶位。

different between:說明兩者的差異
arr[last_sorted: last_sorted + len(buckets[i])] = buckets[i]
arr[last_sorted: last_sorted + len(buckets[i])] = buckets[:len(buckets[i])]

buckets[i]
是「第 i 個桶的內容」。
型別是單一 list（例如 [40, 55, 60]）。
這是你要的行為：把第 i 桶排序後的元素，寫回 arr 對應區段。

buckets[:len(buckets[i])]
是「前 N 個桶」，其中 N = len(buckets[i])。
buckets 本身是「list of lists」，所以這行拿到的是多個桶（例如 [[5, 18], [], [22], ...]），不是元素值。
會把 arr 塞成巢狀 list，資料結構壞掉，排序結果錯誤。
"""