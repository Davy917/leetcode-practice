package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func buildTree(inorder []int, postorder []int) *TreeNode {
	m := make(map[int]int)
	for i, v := range inorder {
		m[v] = i
	}
	fmt.Println(m)
	return recursion(postorder, 0, len(postorder)-1, m, 0, len(inorder)-1)
}
func recursion(postorder []int, postLeft int, postRight int, m map[int]int, inLeft int, inRight int) *TreeNode {
	if postLeft > postRight || inLeft > inRight {
		return nil
	}
	rootVal := postorder[postRight]
	root := &TreeNode{Val: rootVal}
	pIndex := m[rootVal]
	root.Left = recursion(postorder, postLeft, pIndex-1-inLeft+postLeft, m, inLeft, pIndex-1)
	root.Right = recursion(postorder, pIndex-inLeft+postLeft, postRight-1, m, pIndex+1, inRight)
	return root
}
func main() {
	inorder := []int{9, 3, 15, 20, 7}
	postorder := []int{9, 15, 7, 20, 3}
	buildTree(inorder, postorder)
}

/*
inorder:
[inLeft, pIndex-1][pIndex][pIndex+1, inRight]
postorder:
[postLeft, pIndex - 1 - inLeft + postLeft][pIndex -  inLeft + postLeft, postRight - 1][postRight]

105, 106題, 寫了很多遍但是從來沒有在真正意義上理解

[遞歸樹]
buildTree([9,3,15,20,7], [9,15,7,20,3])
│
└─ recursion(post=[9,15,7,20,3], postL=0, postR=4, inL=0, inR=4)
   root=3 (postorder[4])
   pIndex=1 (inorder中3的位置)
   │
   ├─ Left: recursion(post=[9,15,7,20,3], postL=0, postR=0, inL=0, inR=0)
   │   root=9 (postorder[0])
   │   pIndex=0
   │   ├─ Left: recursion(... postL=0, postR=-1) → nil
   │   └─ Right: recursion(... postL=0, postR=-1) → nil
   │
   └─ Right: recursion(post=[9,15,7,20,3], postL=1, postR=3, inL=2, inR=4)
       root=20 (postorder[3])
       pIndex=3
       │
       ├─ Left: recursion(post=[9,15,7,20,3], postL=1, postR=1, inL=2, inR=2)
       │   root=15 (postorder[1])
       │   pIndex=2
       │   ├─ Left: nil
       │   └─ Right: nil
       │
       └─ Right: recursion(post=[9,15,7,20,3], postL=2, postR=2, inL=4, inR=4)
           root=7 (postorder[2])
           pIndex=4
           ├─ Left: nil
           └─ Right: nil
*/
