/*
BFS看js 自己寫的
DFS看Go 官解
 */
const TreeNode = require('../datastructure/Tree/TreeNode');
const buildLevelOrderTree_v2 = require('../datastructure/Tree/TreeDebugger_v2')

/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var largestValues = function(root) {
    let ans = []
    if (root === null)
        return ans
    let queue = [root]
    while (queue.length > 0){
        let level = []
        let levelSize = queue.length
        for (let i = 0; i < levelSize; i++) {
            let curNode = queue.shift()
            level.push(curNode.val)
            if (curNode.left != null)
                queue.push(curNode.left)
            if (curNode.right != null)
                queue.push(curNode.right)
        }
        let levelMaxNum = Math.max(...level)
        ans.push(levelMaxNum)
    }
    return ans
};
if (require.main === module){
    const levelOrder = [1,3,2,5,3,null,9]
    const root = buildLevelOrderTree_v2(levelOrder)
    console.log("Ans = ", largestValues(root))
}