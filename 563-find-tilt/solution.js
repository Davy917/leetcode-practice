const { range } = require('balanced-match');
const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')
/**
 * @param {TreeNode} root
 * @return {number}
 */
var findTilt = function(root) {
    let result = 0
    function dfs(node){
        if(!node){
            return 0
        }
        let leftSum = dfs(node.left)
        let rightSum = dfs(node.right)
        slope = Math.abs(leftSum - rightSum)
        result += slope
        return leftSum + rightSum + node.val
    }
    dfs(root)
    return result
};
if(require.main === module){
    const levelOrder = [1,2,3]
    const root = TreeDebugger.buildLevelOrderTree(levelOrder)
    console.log("Ans = ", findTilt(root))
}