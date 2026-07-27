package main

import (
	"fmt"
	"math"
    "strconv"
	"gopractice/datastructure/Tree"
)
type TreeNode = Tree.TreeNode

func printTree(root *TreeNode) [][]string {
    height := math.MinInt
    var findDepth func(*TreeNode, int)
    findDepth = func(root *TreeNode, depth int){
        if(root == nil){
            return
        }
        if(root.Left == nil && root.Right == nil){
            height = max(height, depth)
            return
        }
        findDepth(root.Left, depth + 1)
        findDepth(root.Right, depth + 1)
    }
    var dfs func([][]string, *TreeNode, int, int)
    dfs = func(res [][]string, root *TreeNode, r int, c int){
        
        if root.Left != nil{
            res[r+1][c - 1 << (height-r-1)] = strconv.Itoa(root.Left.Val.(int))
            dfs(res, root.Left, r+1, c - 1 << (height-r-1))
        }
        if root.Right != nil{
            res[r+1][c + 1 << (height-r-1)] = strconv.Itoa(root.Right.Val.(int))
            dfs(res, root.Right, r+1, c + 1 << (height-r-1))
        }
    }
    findDepth(root, 1)
    height--
    m := height + 1
    n := 1 << (height + 1) - 1
    res := [][]string{}
    for i := 0; i < m; i++ {
        toAdd := []string{}
        for j := 0; j < n; j++ {
            toAdd = append(toAdd, "")
        }
        res = append(res, toAdd)
    }
    res[0][(n-1)/2] = strconv.Itoa(root.Val.(int))
    dfs(res, root, 0, (n-1)/2)
    return res
}
func main(){
	levelOrder := []any{1,2,3,nil,4}
	root := Tree.BuildLevelOrderTree_v2(levelOrder)
	fmt.Println("Ans = ", printTree(root))
}