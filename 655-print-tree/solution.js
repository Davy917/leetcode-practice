const TreeNode = require('../datastructure/Tree/TreeNode')
const buildLevelOrderTree_v2 = require('../datastructure/Tree/TreeDebugger_v2')
/**
 * @param {TreeNode} root
 * @return {string[][]}
 */
var printTree = function(root) {
    function calDepth(root) {
        if (root === null)
            return 0
        const leftDepth = calDepth(root.left) + 1
        const rightDepth = calDepth(root.right) + 1
        return Math.max(leftDepth, rightDepth)
    }

    function dfs(res, root, r, c, height){
        res[r][c] = root.val.toString()
        if(root.left)
            dfs(res, root.left, r+1, c-Math.pow(2, height-r-1), height)
        if(root.right)
            dfs(res, root.right, r+1, c+Math.pow(2, height-r-1), height)
    }
    const height = calDepth(root) - 1
    const m = height + 1;
    const n = Math.pow(2, height + 1) - 1;
    let res = Array.from({ length: m }, () => Array(n).fill(""));
    dfs(res, root, 0, Math.floor((n-1) /2), height)
    return res
};
if(require.main === module){
    const levelOrder = [1,2,3,null,4]
    const root = buildLevelOrderTree_v2(levelOrder)
    console.log("Ans = ", printTree(root))
}
/*
看官解後再自行理解的
https://leetcode.cn/problems/print-binary-tree/solutions/1763780/shu-chu-er-cha-shu-by-leetcode-solution-cnxu/

[FAQ]
問題一 如果只有一個節點, 那麼這棵樹的height是多少??
    樹只有 1 層，m = 1
    因此 height = 0
    這道題的 height 是以「邊數」來計算的（根節點深度為 0）

問題二 拆解 const res = Array.from({ length: m }, () => Array(n).fill(""));
const res = []; //step 1
for (let i = 0; i < m; i++) { //step 2
    const row = new Array(n); //step 3
    for (let j = 0; j < n; j++) { //step 4
        row[j] = "";
    }
    res.push(row); //step 5
}
step1. 先建立一個空箱子（外層陣列），用來裝每一列 (row)
step2. 用一個迴圈跑 m 次，代表建立 m 列
step3. 每一列都是一個長度為 n 的新陣列
step4. 用迴圈把這一列的 n 個格子都填上空字串 ""
step5. 把填好的一列放進我們的 outer 陣列中

為什麼原本的程式碼可以這樣寫？（逐段解析）
const res = Array.from({ length: m }, () => Array(n).fill(""));

1. Array(n).fill("")
    建立一長度為n的陣列, 並用""把裡面填滿
    如果 n=3，它會產生 ["", "", ""]。

2. Array.from({ length: m }, 第二參數)
    Array.from 是 JavaScript 用來「製造陣列」的工具。當我們傳入 { length: m } 時，它會先幫我們準備一個長度為 m 的陣列。

3. () => Array(n).fill("")
    它沒有參數，所以前面寫空括號 ()。
    它只有一行程式碼，所以省略了大括號 {}，並且會自動 return 後面產生的全新陣列。

const res = Array.from({ length: m }, function() {
    return Array(n).fill("");
});

問題三 dfs函式沒有return任何東西, 它的遞歸退出條件是什麼??

是「隱含退出」，不是靠 `return` 值退出。

當走到葉節點時，`left`/`right` 都不存在，兩個 `if` 都不會進去，函式自然結束並回到上一層。  
這就是它的遞迴退出條件。

原理：當遞迴走到「葉子節點」時，root.left 和 root.right 都是 null，兩個 if 都不成立，函數執行完畢後自然結束（隱式 return undefined）。
補充：這種寫法是「副作用型遞迴」（修改 `res`），因此不需要 `return` 結果。`return` 只有在你要把值往上層傳時才必要。
*/