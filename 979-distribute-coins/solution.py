#靈神解答
from typing import Tuple
from typing import Optional
from datastructure import TreeNode
class Solution:
    def distributeCoins(self, root: Optional[TreeNode]) -> int:
        def dfs(node: Optional[TreeNode]) -> Tuple[int, int]:
            if node is None:
                return 0, 0
            coins_left, nodes_left = dfs(node.left)
            coins_right, nodes_right = dfs(node.right)
            coins = coins_left + coins_right + node.val
            nodes = nodes_left + nodes_right + 1
            nonlocal ans
            ans += abs(coins - nodes)
            return coins, nodes
        ans = 0
        dfs(root)
        return ans

"""
靈神解答
https://leetcode.cn/problems/distribute-coins-in-binary-tree/solutions/2343262/tu-jie-mei-you-si-lu-jin-lai-miao-dong-p-vrni/

Tuple講解
https://github.com/jackfrued/Python-100-Days/blob/master/Day01-20/10.%E5%B8%B8%E7%94%A8%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B9%8B%E5%85%83%E7%BB%84.md

[FAQ]
什麼是 nonlocal ?
nonlocal 用於在巢狀函式（nested function） 中聲明某個變數來自外層（非全域）作用域，使得內層函式可以修改外層函式的變數。

dfs 返回值如果用list取代Tuple是可行的嗎 ?
可以，但不建議。

你回傳的 (coins, nodes) 是固定兩個值，結構不會變 → 適合用 Tuple。
List 暗示內容可能會被修改或長度會變，在這裡並不合理。


第10, 11 行看起來很陌生, 拆解的寫法是什麼 ?
這是 Python 的 元組解包（tuple unpacking） 語法。
result = dfs(node.left)    # result 是一個 tuple: (coins, nodes)
coins_l = result[0]        # 取第一個元素
nodes_l = result[1]        # 取第二個元素

兩者本質都是：函式回傳一個包含多個值的結構，然後分別取出賦給不同變數。
題解中用的簡寫只是語法糖，讓程式碼更簡潔。
"""