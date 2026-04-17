def guess(num : int) -> int:
    pick = 7
    if num > pick:
        return -1
    elif num < pick:
        return 1
    else:
        return 0
        
class Solution:
    def guessNumber(self, n: int) -> int:
        return self.guessNumberRec(1, n)

    def guessNumberRec(self, left, right) -> int:

        middle = left + (right - left) // 2
        result = guess(middle)
        if result > 0:
            left = middle + 1
            print(f"left {left}, right {right}, middle {middle}")
            return self.guessNumberRec(left, right) #注意要加上return
        elif result < 0:
            right = middle - 1
            print(f"left {left}, right {right}, middle {middle}")
            return self.guessNumberRec(left, right) #注意要加上return
        else:
            return middle

        
if __name__ == "__main__":
    upper_limit = 10
    sol = Solution()
    ans = sol.guessNumber(upper_limit)
    print(ans)

"""
參考
中文版, 分而治之, 遞歸BinarySearch
https://www.youtube.com/watch?v=TWpumg75Kmo

guess方法沒有寫在任何類別裡面, 在python中是可以被允許的嗎??
現在寫在 solution.py:1 的 guess，是「模組層級函式」，
也就是直接定義在檔案裡、不屬於任何 class。這種寫法在 Python 很常見，而且很正常。
"""