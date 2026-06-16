package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func buildTree(preorder []int, inorder []int) *TreeNode {
	m := make(map[int]int)
	for i, v := range inorder {
		m[v] = i
	}
	fmt.Println("map = ", m)
	return myBuildTree(preorder, 0, len(preorder)-1, m, 0, len(inorder)-1)
}

func myBuildTree(preorder []int, preLeft int, preRight int, m map[int]int, inLeft int, inRight int) *TreeNode {
	if preLeft > preRight || inLeft > inRight {
		return nil
	}
	rootVal := preorder[preLeft]
	root := &TreeNode{Val: rootVal}
	pIndex := m[rootVal]
	root.Left = myBuildTree(preorder, preLeft+1, pIndex-inLeft+preLeft, m, inLeft, pIndex-1)
	root.Right = myBuildTree(preorder, pIndex-inLeft+preLeft+1, preRight, m, pIndex+1, inRight)
	return root
}
func main() {
	preorder := []int{3, 9, 20, 15, 7}
	inorder := []int{9, 3, 15, 20, 7}
	fmt.Println("Ans = ", buildTree(preorder, inorder))
}

/*
preorder
[preLeft][preLeft + 1, pIndex - inLeft + preLeft][pIndex - inLeft + preLeft + 1, preRight]

indorder
[inLeft, pIndex - 1][pIndex][pIndex + 1, inRight]

關鍵在26, 27兩行, 有想到的話這題才有機會寫出來
*/
