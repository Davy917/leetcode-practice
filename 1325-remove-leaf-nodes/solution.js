const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')
/**
 * @param {TreeNode} root
 * @param {number} target
 * @return {TreeNode}
 */
var removeLeafNodes = function(root, target) {
    if(root === null)
        return null
    root.left = removeLeafNodes(root.left, target)
    root.right = removeLeafNodes(root.right, target)
    if(root.val === target && root.left === null && root.right === null)
        return null
    else
        return root
};
if(require.main === module){
    const levelOrder = [1,2,3,2,null,2,4]
    const target = 2
    const root = TreeDebugger.buildLevelOrderTree(levelOrder)
    console.log("Ans = ", removeLeafNodes(root, target))
}