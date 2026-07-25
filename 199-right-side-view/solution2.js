const TreeNode = require('../datastructure/Tree/TreeNode');
const buildLevelOrderTree_v2 = require('../datastructure/Tree/TreeDebugger_v2')

/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var rightSideView = function(root) {
    let Ans = []
    if(root === null){
        return Ans
    }
    function dfs(root, depth){
        if(root === null)
            return
        if(Ans.length < depth){
            Ans.push(root.val)
        }
        dfs(root.right, depth + 1)
        dfs(root.left, depth + 1)
        return
    }
    dfs(root, 1)
    return Ans
}
if (require.main === module){
    const levelOrder = [1,2,3,4,null,null,null,5]
    const root = buildLevelOrderTree_v2(levelOrder)
    console.log("Ans = ", rightSideView(root))
}
/*
方法一 solution.js 自己寫的
方法二 solution2.js 自己寫的

兩版本共同處是都先遍歷右子樹, 才遍歷左子樹, 先遍歷到的節點代表已經在最右側就可以直接塞進Ans

方法二
root在遞迴時才塞進Ans, 遞迴時塞進Ans的都是當前節點, 如果Ans在當前深度已經有值了, 代表現在這個節點並不是該層最右邊的節點, 就不塞
*/