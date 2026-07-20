const TreeNode = require('../datastructure/Tree/TreeNode');
const TreeDebugger = require('../datastructure/Tree/TreeDebugger')

/**
 * @param {TreeNode} root
 * @return {number}
 */
var longestZigZag = function(root) {
    let maxLen = 0
    function dfs(node, direction, curLen){
        if(!node)
            return 0
        maxLen = Math.max(maxLen, curLen)
        if(direction === 0){
            dfs(node.left, 1, curLen + 1)
            dfs(node.right, 0, 1)
        }
        else{
            dfs(node.right, 0, curLen + 1)
            dfs(node.left, 1, 1)
        }
    }
    dfs(root, 0, 0)
    dfs(root, 1, 0)
    return maxLen
};
/*

        A(1)
          \
          B(1)
         /    \
       C(1)   D(1)
              /   \
            E(1)  F(1)
              \
              G(1)
                \
                H(1)

dfs(A, 1, 0) (A 準備往右)
├── dfs(B, 0, 1) (A 往右走，成功轉彎)
│   ├── dfs(C, 1, 2) (B 往左走，成功轉彎)
│   │   ├── dfs(null, 0, 3) -> return 0 (C 往右走，成功轉彎，撞到 null)
│   │   └── dfs(null, 1, 1) -> return 0 (C 往左走，重置，撞到 null)
│   └── dfs(D, 0, 1) (B 往右走，重置)
│       ├── dfs(E, 1, 2) (D 往左走，成功轉彎)
│       │   ├── dfs(G, 0, 3) (E 往右走，成功轉彎)
│       │   │   ├── dfs(null, 1, 4) -> return 0 (G 往左走，成功轉彎，撞到 null)
│       │   │   └── dfs(H, 0, 1) (G 往右走，重置)
│       │   │       ├── dfs(null, 1, 2) -> return 0 (H 往左走，成功轉彎，撞到 null)
│       │   │       └── dfs(null, 0, 1) -> return 0 (H 往右走，重置，撞到 null)
│       │   └── dfs(null, 1, 1) -> return 0 (E 往左走，重置，撞到 null)
│       └── dfs(F, 0, 1) (D 往右走，重置)
│           ├── dfs(null, 1, 2) -> return 0 (F 往左走，成功轉彎，撞到 null)
│           └── dfs(null, 0, 1) -> return 0 (F 往右走，重置，撞到 null)
└── dfs(null, 1, 1) -> return 0 (A 往左走，重置，撞到 null)


自己想不到, 看官解之後再自行理解
https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree/solutions/147425/er-cha-shu-zhong-de-zui-chang-jiao-cuo-lu-jing-b-2/

思路:
首先要想到, 可以利用direction來控制方向

接著, 如果碰壁了怎麼辦?
1. 往另一個方向走
2. 重置curLen
*/ 