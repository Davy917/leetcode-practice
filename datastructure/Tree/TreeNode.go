package Tree

type TreeNode struct {
	Val   any
	Left  *TreeNode
	Right *TreeNode
}

/*
import寫
import "gopractice/datastructure/Tree"
import 之後要再加上這行
type TreeNode = Tree.TreeNode
*/