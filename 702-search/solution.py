class ArrayReader:
    def get(self, index: int) -> int:
        secret = [-1,0,3,5,9,12]
        try:
            return secret[index]
        except IndexError:
            return 2**31 - 1
class Solution:
    def search(self, reader: 'ArrayReader', target: int) -> int:
        left = 0
        right = 10 ** 4 - 1
        while left <= right:
            middle = left + (right - left) // 2
            result = reader.get(middle)
            print(f"left = {left}, right = {right}, middle = {middle}")
            if result < target:
                left = middle + 1

            elif result > target:
                right = middle - 1

            else:
                ans = middle
                return ans
        return -1
if __name__ == "__main__":
    target = 14
    my_reader = ArrayReader()
    print("ans = ", Solution().search(my_reader, target))
"""
FAQ:
這兩種寫法有什麼差別嗎
def search(self, reader: ArrayReader, target: int)
def search(self, reader: 'ArrayReader', target: int)

這兩種寫法在 Python 3.7+ 的環境下，在運行時 (runtime) 幾乎沒有差別，因為它們都表示 reader 參數的類型提示是 ArrayReader。然而,它們在類型檢查器 (type checker)（如 MyPy的工作方式上以及在某些特定場景下略有不同。

主要的區別在於第二種寫法使用了字符串字面量 (string literal) 來表示類型，這稱為前向引用 或延遲評估
1. def search(self, reader: ArrayReader, target: int)
    類型提示方式： 直接引用 ArrayReader 這個類。
    要求： 在定義 search 方法時，ArrayReader 這個類必須已經被定義。
    優點： 直觀、直接。
    缺點： 如果 ArrayReader 類是在 Solution 類之後才定義的（例如，在同一個文件中，但定義順序顛倒），或者存在循環引用（A 引用 B，B 引用 A），那麼這種直接引用會導致 NameError。
2. def search(self, reader: 'ArrayReader', target: int)
    類型提示方式： 使用字符串 'ArrayReader' 來表示類型。
    要求： 在定義 search 方法時，'ArrayReader' 是一個字符串，Python 不會立即去解析它是否是一個有效的類名。類型檢查器會延遲到後續才解析這個字符串。
    優點：
        解決前向引用問題： 允許你在 ArrayReader 類定義之前就引用它。這在類之間有循環引用或定義順序問題時非常有用。
        避免循環依賴： 當兩個類互相引用對方時，使用字符串引用可以打破立即的循環依賴。
    缺點：
        稍微不那麼直觀，因為它是一個字符串。
        在運行時，Python 會在執行時將這個字符串解析為實際的類型。
什麼時候使用字符串字面量？
    前向引用： 當一個類在定義時需要引用一個尚未定義的類（通常是同一個文件中的另一個類，或者有循環依賴）。
    Python 3.6 及更早版本： 在 Python 3.7 之前，如果存在前向引用，必須使用字符串字面量。
    Python 3.7+ 的延遲評估： 從 Python 3.7 開始，PEP 563 引入了對類型註解的延遲評估。這意味著即使是直接引用，Python 運行時也不會立即解析它們。你可以在文件開頭添加 from __future__ import annotations 來啟用這個行為，這樣即使沒有字符串引用，也可以處理前向引用。
總結
    reader: ArrayReader： 直接引用，要求 ArrayReader 在此處已定義。
    reader: 'ArrayReader'： 字符串引用，解決前向引用問題，允許 ArrayReader 在此處之後定義。
在你的例子中，ArrayReader 類是先於 Solution 類定義的，所以兩種寫法都可以工作。然而，在 LeetCode 等平台或大型程式碼庫中，使用 'ArrayReader' 這種字符串形式是非常常見的習慣，因為它提供了更大的靈活性，並避免了潛在的 NameError 問題，特別是在類定義順序不確定或有循環依賴的情況下。  
"""
