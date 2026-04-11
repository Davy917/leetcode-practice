#底層依然用CountingSort實現, 但是advance版本可以排序負數
class radixSort:
    @classmethod
    def radixSort_advance(cls, arr):
        max_num = arr[0]
        for val in arr:
            #注意這邊要取絕對值
            if abs(val) > abs(max_num):
                max_num = abs(val)

        max_digit_length = 0
        while max_num > 0:
            max_num = max_num // 10
            max_digit_length += 1

        dev = 1
        for i in range(max_digit_length):
            #print("arr = ", arr)
            counting = [0] * 19 #注意
            for val in arr:
                radix = (abs(val) // dev % 10) * (-1 if val < 0 else 1) + 9
                print(radix)
                counting[radix] += 1
            print(counting)


        dev *= 10
if __name__ == "__main__":
    arr = [520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168]
    radixSort.radixSort_advance(arr)

"""
当数组中存在负数时，我们就不能简单的计算数组的最大值了，而是要计算数组中绝对值最大的数，也就是数组中最长的数
在获取基数的步骤，将计算出的基数加上 
9
9，使其与 counting 数组下标一一对应

42826
"""