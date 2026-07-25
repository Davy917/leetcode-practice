#自己寫的
from typing import Optional
from datastructure  import TreeNode
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        def find_total(root) -> int:
            if root is None:
                return 0
            return find_total(root.left) + find_total(root.right) + root.val

        def dfs(root) -> int:
            nonlocal max_result
            if root is None:
                return 0
            left_sum = dfs(root.left)
            right_sum = dfs(root.right)
            cur_sum = left_sum + right_sum + root.val
            result = (total - cur_sum) * cur_sum
            max_result = max(max_result, result)
            return cur_sum
        max_result = float('-inf')
        total = find_total(root)
        dfs(root.left)
        dfs(root.right)
        return max_result % ((10 ** 9) + 7)
"""
不接受時間複雜度n平方的解法
如何高效地求出每個節點為根的子樹和？

寫兩個遞歸函式:
第一個算整顆樹的和是多少, 並把值存起來
第二個算當前root如果被分出去, 其乘積是多少
把每個節點的乘積都看過一遍, 回傳最大的那一個

如何計算乘積?
偽代碼:
result = (整顆的和 - 分出去的和) * 分出去的和
max(max_result, result)

注意:
第12行如果沒宣告 nonlocal max_result 會怎樣 ?
python會以為第 19 行的算式是要重新宣告一個 max_result 
但等號右邊的 max(max_result, result) 又試圖讀取它——此時這個區域變數還沒被賦值，所以會拋出 UnboundLocalError。

所以寫 nonlocal max_result 是要告訴 Python 你要修改的是外層的這個 max_result 變數
"""