class Solution:
    def reverseVowels(self, s: str) -> str:
        def isVowel(ch: str) -> bool:
            return ch in "aeiouAEIOU"
        s = list(s)
        l, r = 0, len(s) - 1
        while l < r:
            while l < r and not isVowel(s[l]):
                l+=1
            while l < r and not isVowel(s[r]):
                r-=1
            s[l], s[r] = s[r], s[l]
            l += 1
            r -= 1
        return "".join(s)
if __name__ == "__main__":
    s = "IceCreAm"
    print("Ans = ", Solution().reverseVowels(s))

    
    """
    join 是 Python 字串很常用的方法，用來把「多個字串」串成一個字串。

    基本語法：

    ```python
    separator.join(iterable)
    ```

    - `separator`：分隔符號（例如 `" "`, `","`, `""`）
    - `iterable`：可迭代物件，裡面每個元素都必須是字串（像 list、tuple）

    例子：

    ```python
    words = ["I", "love", "Python"]
    result = " ".join(words)   # "I love Python"
    ```

    ```python
    chars = ["a", "b", "c"]
    result = "".join(chars)    # "abc"
    ```

    在你目前的程式中：

    ```python
    return "".join(s)
    ```

    這行的意思是：
    1. 你先把原本字串轉成 list（方便交換字元）
    2. 處理完後，`s` 是字元陣列，例如 `['A', 'm', 'e', 'r']`
    3. 用 `""` 當分隔符，把它們接回一個完整字串 `"Amer"`

    補充重點：
    - `join` 是「字串物件」的方法，不是 list 的方法。
    - 比起用 `+` 反覆拼接，`join` 在大量字串組合時通常更有效率。
    """