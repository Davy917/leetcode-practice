/*
自己寫的
第一次練習看java
指針 + 廣度遍歷看python
棧 + 深度遍歷看go
*/
package main

type Node struct {
	Val   int
	Prev  *Node
	Next  *Node
	Child *Node
}

func flatten(root *Node) *Node {
	dummy := &Node{Val: 0, Prev: nil, Next: root, Child: nil}
	cur := dummy
	stack := []*Node{}
	for cur != nil {
		if cur.Child != nil {
			if cur.Next != nil {
				stack = append(stack, cur.Next)
			}
			cur.Child.Prev = cur
			cur.Next = cur.Child
			cur.Child = nil
		}
		if cur.Next == nil && len(stack) > 0 {
			n := len(stack) - 1
			connect := stack[n]
			stack = stack[:n]
			cur.Next = connect
			connect.Prev = cur
		}
		cur = cur.Next
	}
	return dummy.Next
}
