const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')

/**
 * @param {TreeNode} root
 * @return {number}
 */
var longestZigZag = function(root) {
    const maxZigZag = 0
    function dfs(node){
        if(node === null)
            return 0
        leftlen = dfs(root.left)
        rightlen = dfs(root.right)
        curZigZag = leftlen + rightlen
        maxZigZag = Math.max(maxZigZag, curZigZag)
        return root
    }
    dfs(root)
    return maxZigZag - 1
};
if(require.main === module){
    const levelOrder = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
    const root = TreeDebugger.buildLevelOrder(levelOrder)
    console.log("Ans = ", longestZigZag(root))
}
/*
    確認每個節點的最大交錯路徑, 返回最大的那個
*/