import Tree.*;
class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.getVal() > p.getVal() && root.getVal() > q.getVal())
            return lowestCommonAncestor(root.getLeft(), p, q);
        if (root.getVal() < p.getVal() && root.getVal() < q.getVal())
            return lowestCommonAncestor(root.getRight, p, q);
        return root;
    }
}
/*
看了答案才知道

[FAQ]
p, q 分別在 root 左右兩側要怎麼解決?

一個大於 root，一個小於 root（或者其中一個就是 root 本身)
這代表 p 和 q 開始「分家」了（一個在左，一個在右），或者其中一個就是當前的 root。
此時的 root 就是我們要找的「最近公共祖先」！ 直接返回目前的 root。

為什麼 BST 的 p, q 在左右兩側, root就直接是答案?
要理解「為什麼 p 和 q 在左右兩側，當前的 root 就直接是答案」，我們可以從**「公共祖先（Ancestor）」與「最近（Lowest）」**這兩個定義來拆解。  

1. curNode 絕對是他們的「公共祖先」
因為 p 在左子樹，q 在右子樹，如果我們從 curNode 出發：  

往左走一定能找到 p。
往右走一定能找到 q。
這代表 curNode 同時是 p 和 q 的祖先，這點毫無疑問。

2. curNode 為什麼是「最近（最低）」的？（重點）
「最近」的意思是：從 curNode 再往下走，還能不能找到另一個更低的公共祖先？  

答案是：絕對不可能。  

1. 如果我們往左子樹走（例如走到 curNode.left）：  

    根據 BST 的定義，左子樹的所有節點都比 curNode 小。
    雖然我們離 p 更近了，但我們永遠失去了找到 q 的機會（因為 q 比 curNode 大，它在右邊，左子樹裡絕對沒有 q）。
    所以左子樹的任何節點都不可能是 q 的祖先，更不可能是「公共」祖先。

2. 同理，如果我們往右子樹走（走到 curNode.right）：  

    我們能找到 q，但永遠找不到 p（因為 p 在左邊）。
    右子樹的任何節點都不可能是 p 的祖先。

結論
只要 p 和 q 分道揚鑣（一個往左、一個往右），這個分叉點就是他們最後的交集。  

一旦你跨過這個分叉點往任何一邊走，你就必定會「顧此失彼」，再也無法同時遇到兩個人。因此，這個分叉點 curNode 就是他們的最近公共祖先（LCA）。  

補充：如果其中一個節點就是 curNode 本身呢？
例如：p 就是 curNode 本身，而 q 在 curNode 的右邊。  

    1. 根據題意，一個節點可以是它自己的祖先。
    2. 如果我們往右走去尋找 q，我們就漏掉了 p（因為 p 在上面）。
    3. 所以，此時的 curNode（也就是 p）也直接就是答案。這同樣符合「分叉點即答案」的邏輯。
*/