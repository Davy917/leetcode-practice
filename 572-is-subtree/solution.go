package main
import(
	"fmt"
	"gopractice/datastructure/Tree"
)
type TreeNode = Tree.TreeNode
func isSubtree(root *TreeNode, subRoot *TreeNode) bool {
	if root == nil && subRoot == nil {
		return true
	} else if root == nil || subRoot == nil {
		return false
	} else if root.Val != subRoot.Val {
		return isSubtree(root.Left, subRoot) || isSubtree(root.Right, subRoot)
	} else {
		return  (isSameTree(root.Left, subRoot.Left) && isSameTree(root.Right, subRoot.Right)) ||  isSubtree(root.Left, subRoot) || isSubtree(root.Right, subRoot)
	}
}

func isSameTree(p *TreeNode, q *TreeNode) bool {
    if p == nil && q == nil {
		return true
	} else if p == nil || q == nil {
		return false
	} else if p.Val != q.Val {
		return false
	} else {
		return isSameTree(p.Left, q.Left) && isSameTree(p.Right, q.Right)
	}
}

func main(){
	root := []any {3,4,5,1,2,nil,nil,nil,nil,0}
	subRoot := []any {4,1,2}
	rootNode := Tree.BuildTree(root)
	subRootNode := Tree.BuildTree(subRoot)
	fmt.Println("Ans = ", isSubtree(rootNode, subRootNode))
}