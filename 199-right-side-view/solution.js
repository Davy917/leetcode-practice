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
    Ans.push(root.val)
    function dfs(root, depth){
        if(root === null)
            return
        if(Ans.length <= depth){ //符合條件代表當前層應該塞值
            if(root.right != null) //右邊有塞右邊
                Ans.push(root.right.val)
            else if(root.left != null) //右邊沒有塞左邊
                Ans.push(root.left.val)
        }
        dfs(root.right, depth + 1)
        dfs(root.left, depth + 1)
        return
    }
    dfs(root, 1)
    return Ans
};
if (require.main === module){
    const levelOrder = [1,2,3,4,null,null,null,5]
    const root = buildLevelOrderTree_v2(levelOrder)
    console.log("Ans = ", rightSideView(root))
}
/*
方法一 solution.js 自己寫的
方法二 solution2.js 自己寫的

兩版本共同處是都先遍歷右子樹, 才遍歷左子樹, 先遍歷到的節點代表已經在最右側就可以直接塞進Ans

方法一
root會預先塞進Ans, 遞迴時把root.right 或root.left塞進Ans, 這版本的邏輯會比較繞
*/