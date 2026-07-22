/*
BFS看js 自己寫的
DFS看Go 自己寫的
 */
const TreeNode = require('../datastructure/Tree/TreeNode');
const buildLevelOrderTree_v2 = require('../datastructure/Tree/TreeDebugger_v2')

/**
 * @param {TreeNode} root
 * @return {number}
 */
var findBottomLeftValue = function(root) {
    let queue = [root]
    let level = []
    while (queue.length > 0){
        level = []
        let levelSize = queue.length
        for (let i = 0; i < levelSize; i++) {
            let curNode = queue.shift()
            level.push(curNode.val)
            if (curNode.left != null)
                queue.push(curNode.left)
            if (curNode.right != null)
                queue.push(curNode.right)
        }
    }
    return level[0]
};
if (require.main === module){
    const levelOrder = [1, 2, 3, 4, null, 5, 6, null, null, 7]
    const root = buildLevelOrderTree_v2(levelOrder)
    console.log("Ans = ", findBottomLeftValue(root))
}