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
            if abs(val) > abs(max_val):
                max_val = abs(val)

        max_digit_length = 0
        while max_val != 0:
            max_val = max_val // 10
            max_digit_length += 1
            
        print("init arr = ", arr)
        radixSort.radixSort_MSD(arr, 0, len(arr) - 1, max_digit_length)

    @staticmethod
    def radixSort_MSD(arr, start, end, position):

        if start == end or position == 0:
            return
        
        counting = [0] * 19
        dev = pow(10, position - 1)

        for index in range(start, end + 1):
            radix = abs(arr[index]) // dev % 10 * (-1 if arr[index] < 0 else 1) + 9
            counting[radix] += 1
        print("counting = ", counting)

        counting[0] -= 1
        for index in range(1, len(counting)):
            counting[index] += counting[index - 1]
        print("counting after prefix = ", counting)

        counting_copy = [0] * len(counting)
        counting_copy[0: len(counting_copy)] = counting[0: len(counting)]

        result = [0] * (end - start + 1)
        for index in range(end, start - 1, -1):
            radix = abs(arr[index]) // dev % 10 * (-1 if arr[index] < 0 else 1) + 9
            result[counting[radix]] = arr[index]
            counting[radix] -= 1

        arr[start: end + 1] = result #注意 + 1
        print("arr = ", arr)

        for i in range(0, len(counting_copy)):
            bucket_local_start = 0 if i == 0 else counting_copy[i-1] + 1 #為什麼 + 1
            bucket_local_end = counting_copy[i]

            bucketStart = start + bucket_local_start
            bucketEnd = start + bucket_local_end

            if bucketStart < bucketEnd:  # 桶內元素 >= 2 才遞迴
                radixSort.radixSort_MSD(arr, bucketStart, bucketEnd, position - 1)

if __name__ == "__main__":
    arr = [27, 53, 35, 52, 51, 32, 36, 23, 58]
    arr2 = [520, -211, 438, -888, 7, 111, 985, 666, -996, 234, 231]
    radixSort.radixSort(arr)
    print("ans = ", arr)

"""
代碼改編自:
https://leetcode.cn/leetbook/read/sort-algorithms/raydw2/
概念來自:
https://www.youtube.com/watch?v=upyiryNShAs

最後for迴圈該如何解讀:
1) 從頭跑一遍，原始 arr
arr = [27, 53, 35, 52, 51, 32, 36, 23, 58]

值	    十位    radix (=digit+9)
27	    2	    11
53	    5	    14
35	    3	    12
52	    5	    14
51	    5	    14
32	    3	    12
36	    3	    12
23	    2	    11
58	    5	    14

各桶 count
    radix 11 十位=2 : 2個
    radix 12 十位=3 : 3個
    radix 14 十位=5 : 4個

2) counting 做完 prefix sum
counting = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 4, 4, 8, 8, 8, 8, 8]
    如何解讀 counting ??
    舉例:
    radix = 14 十位 = 5
    counting[radix] 的值為8, 代表58, 51, 52, 53會被放在arr 的 8, 7, 6, 5

3) 從右到左把元素丟進 result
    得到： result = [27, 23, 35, 32, 36, 53, 52, 51, 58]
    回寫： arr = [27, 23, 35, 32, 36, 53, 52, 51, 58]

4) 拿著counting_copy 做遞迴
i=11：
    bucket_local_start = counting_copy[10] + 1 = (-1)+1 = 0
    bucket_local_end = counting_copy[11] = 1
    bucket 範圍 = arr[0..1] = [27, 23]
    兩個元素 → 會遞迴排個位數
i=12：
    start = counting_copy[11] + 1 = 1+1 = 2
    end = counting_copy[12] = 4
    bucket 範圍 = arr[2..4] = [35, 32, 36]
    3 個元素 → 會遞迴
i=14：
    start = counting_copy[13] + 1 = 4+1 = 5
    end = counting_copy[14] = 8
    bucket 範圍 = arr[5..8] = [53, 52, 51, 58]
    4 個元素 → 會遞迴
第二輪:
桶 i=11：arr[0..1] = [27, 23]
個位 digit
    27 → 7
    23 → 3
個位數排序後（穩定 counting sort
變成 [23, 27]

所以 arr 前段變： arr = [23, 27, 35, 32, 36, 53, 52, 51, 58]

桶 i=12：arr[2..4] = [35, 32, 36]
個位 digit：

    35 → 5
    32 → 2
    36 → 6
排序後：[32, 35, 36]

arr 變： arr = [23, 27, 32, 35, 36, 53, 52, 51, 58]

桶 i=14：arr[5..8] = [53, 52, 51, 58]
個位 digit：

53 → 3
52 → 2
51 → 1
58 → 8
排序後：[51, 52, 53, 58]

arr 最終： arr = [23, 27, 32, 35, 36, 51, 52, 53, 58]

把上面討論內容整理成一份sop, 叫做radixSort_MSD_SOP, 
"""