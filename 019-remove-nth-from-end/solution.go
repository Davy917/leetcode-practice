// 思路看java版本
package main

import (
	"gopractice/datastructure/LinkedList"
)

type ListNode = LinkedList.ListNode

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	if head.Next == nil {
		return nil
	}
	dummy := &ListNode{Val: -1, Next: head}
	l, r := dummy, dummy
	for i := 0; i < n && r.Next != nil; i++ {
		r = r.Next
	}
	for r.Next != nil {
		r = r.Next
		l = l.Next
	}
	l.Next = l.Next.Next
	return dummy.Next //注意不是return head
}
func main() {
	nums := []int{1, 2, 3, 4, 5}
	ll := LinkedList.Constructor()
	head := ll.BuildLinkedList(nums)
	ll.PrintList(head)                      //執行前
	ll.PrintList(removeNthFromEnd(head, 2)) //執行後
}

/*
思路與python版本一致
019-remove-nth-from-end/solution.py

注意第39行, 一開始寫的是return head, 自己沒發現錯了
*/
