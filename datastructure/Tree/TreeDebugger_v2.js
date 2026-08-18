const TreeNode = require('./TreeNode');

/**
 *
 * @param levelOrder number[]
 * @return TreeNode
 */
function buildLevelOrderTree_v2(levelOrder){
    if (levelOrder === null || levelOrder.length === 0 || levelOrder[0] === null)
        return null
    const root = new TreeNode(levelOrder[0])
    let queue = [root]
    let index= 1
    while (queue.length > 0 && index < levelOrder.length){
        let levelSize = queue.length
        for (let i = 0; i < levelSize; i++) {
            let curNode = queue.shift()

            if (index < levelOrder.length){
                if (levelOrder[index] != null){
                    curNode.left = new TreeNode(levelOrder[index])
                    queue.push(curNode.left)
                }
                index++
            }

            if (index < levelOrder.length){
                if (levelOrder[index] != null){
                    curNode.right = new TreeNode(levelOrder[index])
                    queue.push(curNode.right)
                }
                index++
            }
        }
    }
    return root
}
module.exports = buildLevelOrderTree_v2

/*
正確import方式看
993-is-cousins/solution.js
 */