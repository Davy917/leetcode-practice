package Tree

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
import 之後要再加上這行
type TreeNode = Tree.TreeNode
*/