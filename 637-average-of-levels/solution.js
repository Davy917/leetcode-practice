/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var averageOfLevels = function(root) {
    let ans = []
    let deque = []
    deque.push(root)

    while (deque.length > 0){
        let levelSize = deque.length
        let levelSum = 0
        for (let i = 0; i < levelSize; i++) {
            let curNode = deque.shift()
            levelSum += curNode.val
            if (curNode.left != null)
                deque.push(curNode.left)
            if (curNode.right != null)
                deque.push(curNode.right)
        }
        ans.push(levelSum / levelSize)
    }
    return ans
};
/*
拿107題的代碼來改即可
107-level-order-bottom
 */