package main

import "gopractice/datastructure/Tree"

type TreeNode = Tree.TreeNode

func insertIntoMaxTree(root *TreeNode, val int) *TreeNode {
	if root == nil {
		return &TreeNode{val, nil, nil}
	}
	if val > root.Val {
		return &TreeNode{val, root, nil}
	} else {
		root.Right = insertIntoMaxTree(root.Right, val)
		return root
	}
}
