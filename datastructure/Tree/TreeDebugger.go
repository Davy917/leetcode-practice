package Tree

import (
	"fmt"
	"strings"
)

var index int

func BuildPreorderTree(preorder []any) *TreeNode {
	index = 0
	fmt.Printf("開始構建樹，輸入序列: %v\n", preorder)
	return recBuildPreorderTree(preorder, 0)
}

func recBuildPreorderTree(preorder []any, depth int) *TreeNode {
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
	root.Left = recBuildPreorderTree(preorder, depth+1)

	fmt.Printf("%s -> 進入 %d 的右子樹\n", indent, val)
	root.Right = recBuildPreorderTree(preorder, depth+1)
	return root
}
func BuildLevelOrderTree(levelOrder []any) *TreeNode {
	if levelOrder == nil {
		return nil
	}
	root := &TreeNode{Val: levelOrder[0]}
	nodes := []*TreeNode{root} //空切片

	for i := 1; i < len(levelOrder); i++ {
		var newNode *TreeNode
		if levelOrder[i] != nil {
			newNode = &TreeNode{Val: levelOrder[i]}
		}
		parentIndex := (i - 1) / 2
		parent := nodes[parentIndex]

		if parent == nil {
			nodes = append(nodes, nil)
			continue
		}

		if i%2 == 1 {
			parent.Left = newNode
		} else {
			parent.Right = newNode
		}
		nodes = append(nodes, newNode)
	}
	return root
}

//測試請到 datastructure/Tree/Demo

/*
BuildLevelOrderTree FAQ:
[問題 1]
比較:
root := TreeNode{Val: levelOrder[0]}
root := &TreeNode{Val: levelOrder[0]}

1. 值類型
root := TreeNode{Val: levelOrder[0]}
root 是一個 TreeNode 結構體
類型是 TreeNode
修改 root.Left 會修改副本，不會影響原對象

2. 指針類型
root := &TreeNode{Val: levelOrder[0]}
root 是一個指向 TreeNode 的指針
類型是 *TreeNode
修改 root.Left 會直接修改原對象

結論：
二叉樹節點通常定義為 *TreeNode 類型
應該使用 &TreeNode{} 創建節點
返回值也應該是 *TreeNode 類型

[問題 2]
45 行為什麼make裡面寫的是*TreeNode 而不是&TreeNode

make 中的 *TreeNode 是類型聲明
nodes := make([] *TreeNode, len(levelOrder))
             	^^^^^^^^^ 這裡是在說：
            	 "我要創建一個切片，裡面的元素類型是 *TreeNode（指針類型）"

&TreeNode{} 是值創建
root := &TreeNode{Val: levelOrder[0]}
		 ^^^^^^^^^^^^^^^^^^^^^^^^^^^ 這裡是在說：
		 "創建一個 TreeNode 實例，並返回它的地址（指針）"

make([] *TreeNode, ...) 中的 *TreeNode 是類型
&TreeNode{} 中的 & 是操作符，用於取地址
這就是為什麼一個用 *，另一個用 &！

相關內容:
LanguagePractice/GoPractice/structPractice_v2.go
LanguagePractice/GoPractice/pointer.go

[問題 3]
var newNode *TreeNode 看起來很陌生, 請問是什麼意思?

var	變量聲明關鍵字
newNode	變量名稱
*TreeNode	變量類型（指向 TreeNode 的指針）

效果：
創建一個名為 newNode 的變量
類型是 *TreeNode（TreeNode 指針）
初始值是 nil（指針類型的零值）

寫法 1：聲明但不初始化（推薦用於可能為 nil 的情況）
var newNode *TreeNode
newNode = nil

寫法 2：聲明並初始化為空結構體
newNode := &TreeNode{}
newNode 指向一個 TreeNode 實例，Val=0, Left=nil, Right=nil

寫法 3：聲明並初始化帶值
newNode := &TreeNode{Val: 5}
newNode 指向一個 TreeNode 實例，Val=5
*/
