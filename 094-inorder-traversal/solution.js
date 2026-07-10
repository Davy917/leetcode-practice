const TreeNode = require('../datastructure/Tree/TreeNode');

/**
 * @param {TreeNode} root
 * @return {number[]}
 */
let num = []
var inorderTraversal = function(root) {
    if (root === null)
        return num
    inorderTraversal(root.left)
    num.push(root.val)
    inorderTraversal(root.right)
    return num
};