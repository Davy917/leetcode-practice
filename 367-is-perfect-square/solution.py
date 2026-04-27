"""
參考代碼模板
algo/BinarySearch/BinarySearch_basic
"""
class Solution(object):
    def isPerfectSquare(self, num):
        left = 1
        right = num
        while left <= right:
            middle = left + (right - left) // 2
            if middle * middle > num:
                right = middle - 1
            elif middle * middle < num:
                left = middle + 1
            else:
                return True
        return False
if __name__ =="__main__":
    num = 2 ** 31 - 1
    print("ans = ", Solution().isPerfectSquare(num))
