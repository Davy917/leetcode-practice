package main
import (
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func findTilt(root *TreeNode) int {
	result := 0
	dfs(root, &result)
	return result
}
func dfs(node *TreeNode, result *int) int {
	if node == nil{
		return  0
	}	
	// 因為 result 在這裡已經是指標 (*int) 了，直接傳下去即可，不用再加 &
	leftSum := dfs(node.Left, result)
	rightSum := dfs(node.Right, result)
	slope := abs(leftSum - rightSum)
	*result += slope
	return leftSum + rightSum + node.Val.(int)
}

func abs(x int) int {
    if x < 0 {
        return -x
    }
    return x
}

/*
*result += slope：若要修改指標指向的變數數值，必須在變數前加上 *（解引用，Dereference）。  

result 儲存的是記憶體地址。
*result 代表該地址裡實際存放的整數。因此 *result += slope 就能真正改到 findTilt 裡的 result。
*/