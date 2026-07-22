/*
BFS看js 自己寫的
DFS看Go
 */
const TreeNode = require('../datastructure/Tree/TreeNode')
const buildLevelOrderTree_v2 = require('../datastructure/Tree/TreeDebugger_v2')
/**
 * @param {TreeNode} root
 * @param {number} val
 * @param {number} depth
 * @return {TreeNode}
 */
var addOneRow = function(root, val, depth) {
    if (depth === 1){
        let newNode = new TreeNode(val)
        newNode.left = root
        return newNode
    }
    let queue = [root]
    let curDepth = 0
    while (queue.length > 0){
        curDepth++
        let levelSize = queue.length
        for (let i = 0; i < levelSize; i++) {
            let [nextLeft, nextRight] = [null, null]
            let curNode = queue.shift()
            if (curDepth === depth - 1){
                //Step1
                if (curNode.left != null)
                    nextLeft = curNode.left
                if (curNode.right != null)
                    nextRight = curNode.right
                //Step2
                curNode.left = new TreeNode(val)
                curNode.right= new TreeNode(val)
                //Step3
                curNode.left.left = nextLeft
                curNode.right.right = nextRight
            }
            if (curNode.left != null)
                queue.push((curNode.left))
            if (curNode.right != null)
                queue.push(curNode.right)
        }
    }
    return root
};
if (require.main === module){
    const levelOrder = [4,2,6,3,1,5]
    const root = buildLevelOrderTree_v2(levelOrder)
    addOneRow(root, 1, 2)
}
/*
自己寫的
思路:
由上至下遍歷, 在 depth - 1 那層停下來, 執行插入

需要思考的是如何插入?
Step1. 先把 curNode.left, curNode.right 存起來, 避免等一下找不到
Step2. 再把 curNode.left, curNode.right 插入題目要求的節點
Step3. 再把step1. 存起來的那兩個節點賦值給 左新節點的左邊, 右新節點右邊

depth - 1 的每個節點都要做一次上述的動作, 就能達到題目要求
 */