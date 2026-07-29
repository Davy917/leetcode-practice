/**
 * @param {TreeNode} root
 * @param {number} key
 * @return {TreeNode}
 */
var deleteNode = function(root, key) {
    if(!root)
        return null
    if(root.val > key){
        root.left = deleteNode(root.left, key)
        return root
    }
    if(root.val < key){
        root.right = deleteNode(root.right, key)
        return root
    }
    if(root.val === key){
        if(!root.left && !root.right)
            return null
        if(!root.right)
            return root.left
        if(!root.left)
            return root.right
        //左右皆有
        //step1
        let successor = root.right
        while(successor.left)
            successor = successor.left
        //step2
        root.right = deleteNode(root.right, successor.val)
        //step3
        successor.right = root.right;
        successor.left = root.left;
        return successor
    }
    return root
};
/*
試著考慮每種情況, 再把文字轉成代碼
root為空
    返回null
當前節點值 > 目標節點值
    往左走
當前節點值 <　目標節點值
    往右走

找到目標節點後, 檢查
    左右皆無
        刪除該節點, 即返回空
    左有
        root.left視為新樹根, 返回root.left
    右有
        root.right視為新樹根, 返回root.right
    左右皆有
        step1.
        successor = root.right左轉走到底
        step2.
        successor從原來的樹上移除
        step3.
        successor取代root, 即返回successor

step1.
什麼是successor??
successor是root.right子樹中最小值
successor = 最右邊, 最小值
右邊每個元素  >  successor > 左邊每個元素
上面的判斷式得知successor取代root,一定不會錯

step2.
信任遞規
根據step1, 我們可以確定step1左邊為null, 我們直接調用deleteNode
此時會走進右有, 或左右皆無的情境, 然後返回空, 或返回root.right
,最後successor會被刪掉 (看底下圖解)

step3.
successor取代root


圖解:
      10 (要刪除)
     /  \
    5    15 (root.right)
        /  \
       12   18
        \
         13


  deleteNode(以15為根的子樹, 12)：
  
      15                    15
     /  \       →          /  \
    12   18               13   18
     \
      13
  
  回傳 15（子樹的新根）
  root.right = 15  ← 刪除 12 後的右子樹

      12 ← successor 取代了 10
     /  \
    5    15
        /  \
       13   18
*/