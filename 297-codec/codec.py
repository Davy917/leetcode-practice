from datastructure import TreeNode
class Codec:
    def serialize(self, root):
        result: list = []
        def dfs(Node):
            if Node is None:
                result.append("null")
                return
            result.append(str(Node.val))
            dfs(Node.left)
            dfs(Node.right)
        dfs(root)
        return ",".join(result) #此時得到完整字串 "1,2,null,null,3,4,null,null,5,null,null"
    
    def deserialize(self, data):
        def dfs(data):
            val = data.pop(0)
            if val == "null":
                return None
            new_node = TreeNode(int(val))
            new_node.left = dfs(data)
            new_node.right = dfs(data)
            return new_node
        d = data.split(',')
        return dfs(d)
    
    """
    Example:
    1, 2, null, null, 3, 4, null, null, 5, null, null

    扣友提供解答
    https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/solutions/606716/297-er-cha-shu-de-xu-lie-hua-yu-fan-xu-l-647c/

    [FAQ]
    問題一
    def當中又有一個def這種寫法有沒有專有名詞?
    有的，這種寫法叫做巢狀函式（Nested Function），也稱為內部函式（Inner Function）。

    def serialize(self, root):
        result = []
        def dfs(node):          # ← 這就是巢狀函式
            ...
        dfs(root)
        return ",".join(result)

    dfs 就是定義在 serialize 內部的函式。
    相關概念：閉包（Closure）
    當巢狀函式引用了外層函式的變數時，就會形成閉包。例如上面的 dfs 讀取並修改了外層的 result 串列，所以 dfs 就是一個閉包。

    為什麼這裡適合用巢狀函式？
        result 串列只需要在 serialize 中存在，不需要暴露給外部
        巢狀函式可以直接存取外層變數，不需要額外傳遞參數
        邏輯封裝在一起，可讀性更好
        
    問題二
    ",".join(result) 執行前的result是一組怎樣的數據結構
    DFS 跑完之後result 是：
    ["1", "2", "null", "null", "3", "4", "null", "null", "5", "null", "null"]
    其實result 本身沒有逗號，它只是一個普通的 Python 串列：
    串列裡每個元素是獨立的字串，元素之間並沒有逗號。

    ",".join(result) 做的事情是：用逗號把串列中的所有元素串成一個字串。
    結果: "1,2,null,null,3"
    逗號是在 join 的當下才被加進去的，不是 result 本身帶有的。

    問題三
    split(',')之後, 會把 "1,2,null,null,3,4,null,null,5,null,null" 變成什麼?
    split(",") 會把字串按逗號切開，回傳一個字串串列：
    ["1", "2", "null", "null", "3", "4", "null", "null", "5", "null", "null"]

    注意每個元素仍然是字串型別，不是整數。所以在反序列化時，你需要：
    判斷是否為 "null" → 代表空節點
    否則用 int() 轉成整數 → 作為節點值
    """