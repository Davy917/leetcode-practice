const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')
/**
 * @param {TreeNode} root
 * @return {number}
 */
var maxSumBST = function(root) {
    let [curSum, maxSum] = [0, 0]
    function dfs(root){
        if (root === null)
            return 0;
        let leftSum = dfs(root.left)
        let rightSum = dfs(root.right)
        const isBST = valid(root, -Infinity, Infinity)
        if (isBST){
            curSum = leftSum + rightSum + root.val
            maxSum = Math.max(curSum, maxSum)
        }
        else
            return 0

        return curSum
    }
    function valid(root, lower, upper){
        if (root === null)
            return true
        if (root.val <= lower || root.val >= upper)
            return false
        let isLeftBST = valid(root.left, lower, root.val)
        let isRightBST = valid(root.right, root.val, upper)
        return isLeftBST && isRightBST
    }
    dfs(root)
    return maxSum
};
/*
看過每個節點為root時的最大鍵值和, 回傳統計到的最大值

檢查以當前節點為root, 它是不是一顆BST
是 計算curSum
不是 回傳0

細節:
寫一個valid, 並確保只要這樣呼叫, 就能判定 root 是否為BST
const isBST = valid(root, -Infinity, Infinity)

valid寫法直接拿這題來用
098-is-valid-BST/solution.js

這版拿去提交會超時, 先看完improve.md
1373-max-sum-BST/improve.md
再看:
1373-max-sum-BST/solution.go
*/