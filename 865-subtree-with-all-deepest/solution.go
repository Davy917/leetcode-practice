package main

import "gopractice/datastructure/Tree"

type TreeNode = Tree.TreeNode

func subtreeWithAllDeepest(root *TreeNode) (Ans *TreeNode) {
	var findMaxDepth func(*TreeNode) int
	findMaxDepth = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		leftDepth := findMaxDepth(root.Left)
		rightDepth := findMaxDepth(root.Right)
		return max(leftDepth, rightDepth) + 1
	}
	var dfs func(*TreeNode, int, int) bool
	dfs = func(root *TreeNode, curDepth int, maxDepth int) bool {
		if root == nil {
			return false
		}
		if curDepth == maxDepth {
			Ans = root
			return true
		}
		leftHasDeepest := dfs(root.Left, curDepth+1, maxDepth)
		rightHasDeepest := dfs(root.Right, curDepth+1, maxDepth)

		if leftHasDeepest == true && rightHasDeepest == true {
			Ans = root
			return true
		} else if leftHasDeepest == true {
			return true
		} else if rightHasDeepest == true {
			return true
		}
		return false
	}
	maxDepth := findMaxDepth(root)
	dfs(root, 1, maxDepth)
	return
}

/*
找到最深節點的深度, 把它存下來, 二次遍歷會用到

關鍵:
如何找到包含所有最深節點的root?
宣告hasDeepest, 並用dfs preOrder遍歷,
當前節點非最深節點, 繼續遍歷
當前節點是最深節點, hasDeepest回傳true

想像:
宣告Ans, 用來存放可能是答案的節點
遍歷完左右兩邊之後
左右兩邊皆true, 該節點納入Ans
只有一邊回傳true, 往上回傳true

思考一下：
為什麼在curDepth == maxDepth 的時候更新 Ans 能保證正確？
如果最深節點只有一個, 那麼答案就是他自己, 找到最深節點時, 該節點應該直接被納入考慮範圍

為什麼在「兩側都有」的時候更新 Ans 能保證正確？
因為這代表當前節點就是最深節點的 LCA，而且越晚（越深）觸發這個條件的節點，就會是最小（最深）的那個。

名詞
LCA（Lowest Common Ancestor）最低共同祖先
*/

/*
自己在寫的時候忽略了 "思考一下" 提及的那兩點, 寫成底下這樣, 所以沒能獨立完成
} else if leftHasDeepest == true {
    Ans = root.Left   // ❌ 這裡會把之前找到的正確答案蓋掉
    return false
}
*/
