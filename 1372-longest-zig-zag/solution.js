const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')

/**
 * @param {TreeNode} root
 * @return {number}
 */
var longestZigZag = function(root) {
    let maxLen = 0
    function dfs(node, direction, curLen){
        if(!node)
            return 0
        maxLen = Math.max(maxLen, curLen)
        if(direction === 0){
            dfs(node.left, 1, curLen + 1)
            dfs(node.right, 0, 1)
        }
        else{
            dfs(node.right, 0, curLen + 1)
            dfs(node.left, 1, 1)
        }
    }
    dfs(root, 0, 0)
    dfs(root, 1, 0)
    return maxLen
};