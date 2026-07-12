const TreeNode = require('../datastructure/Tree/TreeNode');

/**
 * @param {TreeNode} root
 * @return {number}
 */
var maxDepth = function(root) {
    if (root === null)
        return 0
    let leftDepth = maxDepth(root.left) + 1
    let rightDepth = maxDepth(root.right) + 1
    return Math.max(leftDepth, rightDepth)
};