const TreeNode = require('../datastructure/Tree/TreeNode');

/**
 * @param {TreeNode} root
 * @return {number}
 */

var diameterOfBinaryTree = function(root) {
    var dfs = function (node){
        if (node === null)
            return 0
        let leftLen = dfs(node.left)
        let rightLen = dfs(node.right)
        maxLen = Math.max(maxLen, leftLen + rightLen + 1)
        return Math.max(leftLen, rightLen) + 1
    }
    let maxLen = 1
    dfs(root)
    return maxLen - 1
};
//maxLen 統計的實際上是節點個數, 不是邊長