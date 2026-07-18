/*
前序遍歷看js
中序遍歷看java
後序便利看golang
 */
const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isValidBST = function(root) {
    function dfs(root, lower, upper){
        if (!root)
            return true
        if (root.val <= lower || root.val >= upper)
            return false
        let isLeftBST = dfs(root.left, lower, root.val)
        let isRightBST = dfs(root.right, root.val, upper)
        return isLeftBST && isRightBST
    }
    return dfs(root, -Infinity, Infinity)
};

/*
官解:
https://leetcode.cn/problems/validate-binary-search-tree/solutions/230256/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/

[遞歸樹]
前序遍歷
dfs(node=5, lower=-∞, upper=∞)
├── dfs(left=1, lower=-∞, upper=5)
│   ├── dfs(left=null, lower=-∞, upper=1) -> return true
│   └── dfs(right=null, lower=1, upper=5) -> return true
│   └── return true && true = true
│
└── dfs(right=4, lower=5, upper=∞)
    └── 檢查: root.val(4) <= lower(5) 為 true
    └── return false

後序遍歷
dfs(node=5, lower=-Inf, upper=Inf)
├── dfs(left=1, lower=-Inf, upper=5)
│   ├── dfs(left=null, ...) -> true
│   ├── dfs(right=null, ...) -> true
│   └── 檢查: 1 <= -Inf || 1 >= 5 (false) -> return true
│
└── dfs(right=4, lower=5, upper=Inf)
    ├── dfs(left=3, lower=5, upper=4)
    │   ├── dfs(left=null, ...) -> true
    │   ├── dfs(right=null, ...) -> true
    │   └── 檢查: 3 <= 5 || 3 >= 4 (true) -> return false
    │
    ├── dfs(right=6, lower=4, upper=Inf)
    │   ├── dfs(left=null, ...) -> true
    │   ├── dfs(right=null, ...) -> true
    │   └── 檢查: 6 <= 4 || 6 >= Inf (false) -> return true
    │
    └── 檢查: 4 <= 5 || 4 >= Inf (true) -> return false && (false && true) = false

總計結果: true && false = false

主要區別
遍歷深度：前序遍歷是「剪枝」邏輯（Early Return），一旦父節點非法，直接返回 false，不再訪問子節點。後序遍歷會訪問整棵樹，即使父節點非法，也會完整地遍歷其下方的所有子節點。
遞歸樹行為：後序的代碼比前序多訪問了 node=3 和 node=6（在前序中，訪問 node=4 時若發現其非法，遞歸直接終止，不會進入其左右子樹）。在後序中，雖然最終結果均正確（返回 false），但修改後的寫法效率更低。
*/