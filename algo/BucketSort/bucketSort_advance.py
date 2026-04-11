"""
空間複雜度優化版
"""
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
        

if __name__ == "__main__":
    arr = [55, 80, 22, 60, 18, 90, 40, 5, 70, 30]
    bucketSort.bucketSort_basic(arr)
    print(arr)

"""
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/phtz1j/
"""