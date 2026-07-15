const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')
/**
 * @param {TreeNode} root
 * @return {TreeNode[]}
 */
var findDuplicateSubtrees = function(root) {
    const map = new Map()
    const Ans = new Set()
    function dfs(node){
        if(node === null)
            return ""
        let leftVal = dfs(node.left)
        let rightVal = dfs(node.right)
        let key = String(node.val) + "(" + leftVal + ")(" + rightVal + ")"
        if(map.has(key)){
            Ans.add(map.get(key)) // 加入第一次出現的那個節點
        }
        else{
            map.set(key, node)
        }
        return key
    }  
    dfs(root)
    return [...Ans]
};
if(require.main === module){
    const levelOrder = [1,2,3,4,null,2,4,null,null,4]
    const root = TreeDebugger.buildLevelOrderTree(levelOrder)
    console.log("Ans = ", findDuplicateSubtrees(root))
}
/*
4
    key = 4()()
2
    key = 2(4()())()
4
    key = 4()()
2
    key = 2(4()())()
4
    key = 4()()
3
    key = 3(2(4()())())(4()())
1
    key = 1(2(4()())())(3(2(4()())())(4()()))

FAQ:
第17行為何是  Ans.add(map.get(key))
    當 key 已存在於 m 時，應該加入的是 m 中記錄的第一次出現的節點，而不是當前節點：
    這樣不管子樹出現多少次，s 裡面始終只會存同一個節點引用，確保每類重複子樹只回傳一棵。

第25行 return [...Ans] 是什麼意思
    1. 遍歷 Set 中的所有元素
    2. 將它們逐一放入一個新陣列中
    3. 回傳這個陣列

執行代碼:
node ./652-find-duplicate-subtrees/solution.js
*/