"""
代碼參考模板:
algo/BinarySearch/BinarySearch_basic.java
"""
class Solution:
#修正後
    def mySqrt(self, x: int) -> int:
        left = 1
        right = x
        ans = 0
        while left <= right: #注意,為什麼等號
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if middle * middle > x: #注意,不用轉成long
                right = middle - 1
            else:
                ans = middle
                left = middle + 1

        return ans

#修正前
    def mySqrt_origin(self, x: int) -> int:
        left = 1
        right = x
        ans = 0
        while left <= right: #注意,為什麼等號
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if middle * middle > x: #注意,不用轉成long
                right = middle - 1
            elif middle * middle < x:
                left = middle + 1
            else:
                ans = middle
                return middle
            ans = middle #注意不能寫在這裡

        return ans
#好理解的版本
    def mySqrt_realize(self, x: int) -> int:
        left = 1
        right = x
        ans = 0
        while left <= right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if middle * middle > x:
                right = middle - 1
            elif middle * middle < x:
                left = middle + 1
                ans = middle
            else:
                ans = middle
                return middle
        return ans
        
if __name__ == "__main__":
    x = 8
    ans = Solution().mySqrt(x)
    print("ans = ", ans)
    ans_realize = Solution().mySqrt_realize(x)
    print("ans = ", ans)
"""
在 Python 中，整數本身就是任意精度的，不會溢位，所以 middle * middle 已經是「長整數」了，不需要額外轉換。
如果是在寫 Java，則需要加上 (long) middle * middle 來避免 int 溢位。
"""