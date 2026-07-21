const TreeNode = require('../datastructure/Tree/TreeNode')
const TreeDebugger = require('../datastructure/Tree/TreeDebugger_v2')
/**
 * @param {TreeNode} root
 * @param {number} x
 * @param {number} y
 * @return {boolean}
 */
var isCousins = function(root, x, y) {
    let deque = []
    deque.push(root)
    while (deque.length > 0){
        let level = []
        let levelSize = deque.length
        for (let i = 0; i < levelSize; i++) {
            let curNode = deque.shift()
            level.push(curNode.val)
            if (curNode.left != null && curNode.right != null)
                if ((curNode.left.val === y || curNode.left.val === x) && (curNode.right.val === y || curNode.right.val === x))
                    return false //是同一個父節點, 非堂兄弟節點
            if (curNode.left != null)
                deque.push(curNode.left)
            if (curNode.right != null)
                deque.push(curNode.right)
        }
        if (level.includes(x) && !level.includes(y) || level.includes(y) && !level.includes(x))
            return false //x, y不在同一層, 非堂兄弟節點
    }
    return true
}

if (require.main === module){
    // 只有當 node solution.js 直接執行時，這段才會跑
    // 如果是 require('./solution.js')，這段不會跑
    // 這樣做的好處是：你可以把 solution.js 當成模組 import 到其他檔案，不會自動執行測試代碼。
    const levelOrder = [1,2,3,null,4,null,5]
    const root = TreeDebugger.buildLevelOrderTree(levelOrder)
    console.log("Ans = ", isCousins(root, 5, 4))
}
/*
堂兄弟節點，需要同時滿足兩個條件：
    深度相同 — x 和 y 在同一層（depth 一樣）。
    父節點不同 — 它們不能是同一個父節點的孩子（不能是親兄弟）。

思路:
先把層序遍歷寫出來, 直接抄107, 再加入堂兄弟節點的判斷邏輯
107-level-order-bottom
 */