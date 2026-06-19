package Tree

import (
	"fmt"
	"strings"
)
var index int
func BuildTree(preorder []any) *TreeNode {
	index = 0
	fmt.Printf("開始構建樹，輸入序列: %v\n", preorder)
	return myBuildTree(preorder, 0)
}

func myBuildTree(preorder []any, depth int) *TreeNode {
	indent := strings.Repeat(" ", depth)
	if index >= len(preorder) {
		return nil
	}
	curVal := preorder[index]
	index++
	// 在 Go 中，any 類型的 nil 可以直接與 nil 比較
	if curVal == nil {
		fmt.Printf("%s遇到 nil，返回上一層\n", indent)
		return nil
	}
	// 型別斷言：將 any 轉換回 int
	val := curVal.(int)
	fmt.Printf("%s創建節點: %d (index: %d)\n", indent, val, index-1)
	root := &TreeNode{Val: val}

	fmt.Printf("%s -> 進入 %d 的左子樹\n", indent, val)
	root.Left = myBuildTree(preorder, depth+1)

	fmt.Printf("%s -> 進入 %d 的右子樹\n", indent, val)
	root.Right = myBuildTree(preorder, depth+1)
	return root
}
//測試請到 datastructure/Tree/Demo