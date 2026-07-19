package main
import "fmt"
import "gopractice/datastructure/Tree"
type TreeNode = Tree.TreeNode

func findDuplicateSubtrees(root *TreeNode) []*TreeNode {
	s := map[*TreeNode]struct{}{}
	m := map[string]*TreeNode{}

	var dfs func(*TreeNode)string
	dfs = func(root *TreeNode) string {
		if root == nil {
			return ""
		}
		leftVal := dfs(root.Left)
		rightVal := dfs(root.Right)
		var key = fmt.Sprintf("%d(%s)(%s)", root.Val.(int), leftVal, rightVal)
		_, exist := m[key]
		if exist {
		s[m[key]] = struct{}{}
		} else {
			m[key] = root
		}
		return key
	}
	dfs(root)
	var Ans []*TreeNode
	for k, _:= range s{
		Ans = append(Ans, k)
	}
	return Ans
}
func main(){
	levelOrder := []any {1,2,3,4,nil,2,4,nil,nil,4}
	root := Tree.BuildLevelOrderTree_v2(levelOrder)
	fmt.Println("Ans = ", findDuplicateSubtrees(root))
}