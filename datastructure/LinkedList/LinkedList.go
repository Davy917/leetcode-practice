package LinkedList

import "fmt"

type LinkedList struct{
	Head *ListNode
	Tail *ListNode
	Size int
}

func NewLinkedList() *LinkedList {
	return &LinkedList{}
}

func (ll *LinkedList) AddAtTail(val int) {
	newNode := &ListNode{Val: val}
	if ll.Size == 0 {
		ll.Head = newNode
		ll.Tail = newNode
		ll.Size++
		return
	}
	ll.Tail.Next = newNode
	ll.Tail = newNode
	ll.Size++
}

func (ll *LinkedList) BuildLinkedList(nums []int) (Head *ListNode) {
	ll.Head = nil
	ll.Tail = nil
	ll.Size = 0
	for _, num := range nums {
		ll.AddAtTail(num)
	}
	return ll.Head
}

func (ll *LinkedList) PrintList(Head *ListNode) {
	fmt.Print("[")
	visitor := Head
	for visitor != nil{
		fmt.Printf("%d ", visitor.Val)
		visitor = visitor.Next
	}
	fmt.Print("]\n")
}