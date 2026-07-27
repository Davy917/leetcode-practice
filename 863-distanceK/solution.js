const TreeNode = require('../datastructure/Tree/TreeNode');
const buildLevelOrderTree_v2 = require('../datastructure/Tree/TreeDebugger_v2')
/**
 * @param {TreeNode} root
 * @param {TreeNode} target
 * @param {number} k
 * @return {number[]}
 */

function findNode(root, val) {
  if (!root) return null
  if (root.val === val) return root
  return findNode(root.left, val) || findNode(root.right, val)
}

var distanceK = function(root, target, k) {
    const parents = new Map()
    let ans = []
    const findParents = root => {
        if(root.left){
            parents.set(root.left.val, root)
            findParents(root.left)
        }
        if(root.right){
            parents.set(root.right.val, root)
            findParents(root.right)
        }
    }
    const dfs = (node, from, depth, k) => {
        if(node == null)
            return
        if(depth === k){
            ans.push(node.val)
            return
        }
        if(node.left !== from)
            dfs(node.left, node, depth + 1, k)
        if(node.right !== from)
            dfs(node.right, node, depth + 1, k)
        if(parents.get(node.val) !== from)
            dfs(parents.get(node.val), node, depth + 1, k)
    }
    findParents(root)
    dfs(target, null, 0, k)
    return ans
};
if (require.main === module){
    const levelOrder = [3,5,1,6,2,0,8,null,null,7,4]
    const root = buildLevelOrderTree_v2(levelOrder)
    const target = findNode(root, 5)
    console.log("Ans = ", distanceK(root, target, 2))
}
/*
        3
       / \
      5   1
     / \ / \
    6  2 0  8
       / \
      7   4

核心:
如何把target變成根節點?
從 root 出發做一次 DFS，用一個 Map 記錄每個節點的父節點

把 target 當作根之後，每個節點有三個方向可以走（左、右、父）。
from 就是告訴下一步：「我是從 from 這個節點過來的，不要再走回去」。

from = 上一站，每走一步就把當前節點設為下一步的 from，確保只往前走、不走回頭路。

[遞歸樹]
dfs(5, null, 0)
├── dfs(6, 5, 1)
│   ├── 6.left = null → 跳過
│   ├── 6.right = null → 跳過
│   └── parents.get(6) = 5 == from → 跳過
│
├── dfs(2, 5, 1)
│   ├── dfs(7, 2, 2) → depth=2 == k ✅ → ans.push(7)
│   ├── dfs(4, 2, 2) → depth=2 == k ✅ → ans.push(4)
│   └── parents.get(2) = 5 == from → 跳過
│
└── dfs(3, 5, 1)
    ├── 3.left = 5 == from → 跳過
    ├── dfs(1, 3, 2) → depth=2 == k ✅ → ans.push(1)
    └── parents.get(3) = undefined → 跳過


const 的真正意思
const 鎖定的是綁定（binding），不是值（value）。
const parents = new Map();

這行做了兩件事：
    1. 建立一個新的 Map 物件（存在記憶體某處）
    2. 把 parents 這個名字綁定到那個物件的位址

const parents = new Map();

parents = new Map();  // ❌ TypeError: Assignment to constant variable.
parents = [];         // ❌ 重新賦值，禁止

parents.set(1, 'a');  // ✅ 沒有重新賦值，只是操作原本那個 Map
parents.set(2, 'b');  // ✅ 同上
*/