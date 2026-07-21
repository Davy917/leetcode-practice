const TreeNode = require('../datastructure/Tree/TreeNode')
/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
var zigzagLevelOrder = function(root) {
    let ans = []
    if (root === null)
        return ans
    let queue = []
    let layer = 1
    queue.push(root)
    while (queue.length > 0){
        let num = []
        let layerSize = queue.length
        for (let i = 1; i <= layerSize; i++) {
                let curNode = queue.shift()
                num.push(curNode.val)
                if (curNode.left != null)
                    queue.push(curNode.left)
                if (curNode.right != null)
                    queue.push(curNode.right)
        }
        if (layer % 2 === 1)
            ans.push(num)
        else
            ans.push(num.reverse())
        layer++
    }
    return ans
};
/*
拿107題的代碼來改即可
107-level-order-bottom
 */