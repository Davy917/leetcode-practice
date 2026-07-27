const TreeNode = require('../datastructure/Tree/TreeNode');
const buildLevelOrderTree_v2 = require('../datastructure/Tree/TreeDebugger_v2')
/**
 * @param {TreeNode} root
 * @param {TreeNode} subRoot
 * @return {boolean}
 */
var isSubtree = function(root, subRoot) {
    if(root === null && subRoot === null)
        return true
    else if(root === null || subRoot === null)
        return false
    else if(root.val !== subRoot.val)
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
    else
        return isSametree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
};
var isSametree = function(root, subRoot) {
    if(root === null && subRoot === null)
        return true
    else if(root === null || subRoot === null)
        return false
    else if(root.val !== subRoot.val)
        return false
    else
        return isSametree(root.left, subRoot.left) && isSametree(root.right, subRoot.right)
}
if(require.main === module){
    const levelOrder = [3,4,5,1,2]
    const root = buildLevelOrderTree_v2(levelOrder)
    const subTree = [4,1,2]
    const subRoot = buildLevelOrderTree_v2(subTree)
    console.log("Ans = ", isSubtree(root, subRoot))
}
//寫第三次還是沒有寫出來