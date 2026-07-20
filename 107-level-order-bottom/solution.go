package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
	"slices"
)
type TreeNode = Tree.TreeNode
func levelOrderBottom(root *TreeNode) (result [][]int) {
	if root == nil {
		return result
	}
	deque := []*TreeNode{}
	deque = append(deque, root)

	for len(deque) > 0 {
		level := []int{}
		levelSize := len(deque)
		for i := 0; i < levelSize; i++ {
			curNode := deque[0]
			deque = deque[1:]
			level = append(level, curNode.Val.(int))
			if curNode.Left != nil {
				deque = append(deque, curNode.Left)
			}
			if curNode.Right != nil {
				deque = append(deque, curNode.Right)
			}
		}
		result = append(result, level)
	}
	slices.Reverse(result)
	return
}
func main(){
	levelOrder := []any{3, 9, 20, nil, nil, 15, 7}
	root := Tree.BuildLevelOrderTree_v2(levelOrder)
	fmt.Println("Ans = ", levelOrderBottom(root))
}

/*
Go 的 for 迴圈條件會每次重新計算

i < len(deque) 是每一次迭代都會重新計算的。當你在迴圈內執行：
deque = deque[1:]              // 長度 -1
deque = append(deque, ...)     // 長度 +1 或 +2
len(deque) 會即時變化，導致這個迴圈永遠無法正常結束（取出 1 個、加入 1~2 個，長度不會歸零）。

所以需要用 levelSize 把 deque 當下的長度先存起來, 在拿它來做為內層迴圈的執行次數
*/