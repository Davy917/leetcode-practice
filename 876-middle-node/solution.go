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
	node := &ListNode{val, nil}
	if ll.head == nil {
		ll.head = node
		ll.tail = node
	} else {
		ll.tail.Next = node
		ll.tail = node
	}
	ll.size++
}

func middleNode(head *ListNode) *ListNode {
	i, k := head, head
	size := 1
	for i.Next != nil {
		i = i.Next
		size++
	}
	for j := 0; j < size/2; j++ {
		k = k.Next
	}
	return k
}
func main() {
	head := []int{1}
	ll := LinkedList{}
	for _, v := range head {
		ll.Append(v)
	}
	middleNode(ll.head)
}

//思路: 第一次遍歷先得到總長度, 第二次遍歷時走到一半就直接 return
