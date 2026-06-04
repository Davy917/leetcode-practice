// 自己寫的
package main

type ListNode struct {
	val  int
	Next *ListNode
}
type LinkedList struct {
	head *ListNode
	tail *ListNode
	size int
}

func (ll *LinkedList) Append(val int) {
	Node := &ListNode{val, nil}
	if ll.head == nil {
		ll.head = Node
		ll.tail = Node
	} else {
		ll.tail.Next = Node
		ll.tail = Node
	}
	ll.size++
}
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	if head.Next == nil {
		return nil
	}
	dummy := &ListNode{0, head} //& ??
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
	ll := LinkedList{nil, nil, 0}
	head := []int{1, 2, 3, 4, 5}
	for _, v := range head {
		ll.Append(v)
	}
	removeNthFromEnd(ll.head, 2)
}

/*
思路與python版本一致
019-remove-nth-from-end/solution.py

注意第39行, 一開始寫的是return head, 自己沒發現錯了
*/
