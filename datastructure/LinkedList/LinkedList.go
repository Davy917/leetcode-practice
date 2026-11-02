package LinkedList

import "fmt"

type LinkedList struct {
	Head *ListNode
	Size int
}

func Constructor() LinkedList {
	return LinkedList{nil, 0}
}

func (this *LinkedList) Get(index int) int {
	if index < 0 || index >= this.Size {
		return -1
	}
	cur := this.Head
	k := 0
	for k < index && cur.Next != nil {
		cur = cur.Next
		k++
	}
	return cur.Val
}

func (this *LinkedList) AddAtHead(val int) {
	this.AddAtIndex(0, val)
}
func (this *LinkedList) AddAtTail(val int) {
	this.AddAtIndex(this.Size, val)
}
func (this *LinkedList) AddAtIndex(index int, val int) {
	if index > this.Size {
		return
	}
	dummy := &ListNode{-1, this.Head}
	newNode := ListNode{val, nil}
	cur := dummy
	j := 0
	for j < index {
		cur = cur.Next
		j++
	}
	if index == 0 {
		dummy.Next = &newNode
		newNode.Next = this.Head
		this.Head = &newNode
	} else {
		temp := cur.Next
		cur.Next = &newNode
		newNode.Next = temp
	}
	this.Size++
}

func (this *LinkedList) DeleteAtIndex(index int) {
	if index < 0 || index >= this.Size {
		return
	}
	dummy := &ListNode{-1, this.Head}
	cur := dummy
	j := 0
	for j < index {
		cur = cur.Next
		j++
	}

	if index == 0 {
		this.Head = this.Head.Next
		dummy.Next = this.Head
	} else {
		if cur.Next != nil && cur.Next.Next != nil {
			cur.Next = cur.Next.Next
		} else {
			cur.Next = nil
		}
	}
	this.Size--
}

func (this *LinkedList) BuildLinkedList(nums []int) *ListNode {
	for _, num := range nums {
		this.AddAtTail(num)
	}
	return this.Head
}

func (this *LinkedList) PrintList(Head *ListNode) {
	fmt.Print("[")
	cur := Head
	for cur != nil {
		fmt.Printf("%d ", cur.Val)
		cur = cur.Next
	}
	fmt.Print("]\n")
}

/*
改編自 707-my-linked-list\solution.go
*/
