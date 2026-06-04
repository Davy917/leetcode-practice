package main

import "fmt"

type ListNode struct {
	val  int
	Next *ListNode
}

type LinkedList struct {
	head *ListNode
	tail *ListNode
	size int
}

func newLinkedList() *LinkedList {
	return &LinkedList{}
}

func (ll *LinkedList) append(val int) {
	node := ListNode{val: val}
	if ll.head == nil {
		ll.head = &node
		ll.tail = &node
	} else {
		ll.tail.Next = &node
		ll.tail = &node
	}
	ll.size++
}

func hasCycle(head *ListNode) bool {
	if head == nil {
		return false
	}
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil { //注意先後順序
		slow = slow.Next
		fast = fast.Next.Next
		if slow == fast {
			return true
		}
	}
	return false
}
func main() {
	ll := newLinkedList()
	ll.append(1)
	ll.append(2)
	ll.append(0)
	ll.append(4)
	//手動製造環形鏈表
	//ll.tail.Next = ll.head.Next
	fmt.Println("Ans = ", hasCycle(ll.head))
}

/*
golang linkedList實作
datastructure/LinkedList/doublyLinkedList.go

第37行有個容易忽略的問題
fast.Next != nil && fast.Next.Next != nil
順序寫反的話會出 bug
*/
