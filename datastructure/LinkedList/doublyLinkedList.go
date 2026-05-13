package main

import (
	"fmt"
)
type DoublyLinkedNode struct{
	val int
	next *DoublyLinkedNode
	prev *DoublyLinkedNode
}
type DoublyLinkedList struct{
	head *DoublyLinkedNode
	tail *DoublyLinkedNode
	size int
}

func NewDoublyLinkedList() *DoublyLinkedList {
	return &DoublyLinkedList{}
}

//func (接收者) 方法名(參數) 回傳型別
func (dll *DoublyLinkedList) Append(val int) {
	node := &DoublyLinkedNode{val: val}
	if dll.head == nil:
		
}

func main() {
	list := &DoublyLinkedList{}
	dll := NewDoublyLinkedList()
	fmt.Println(list, dll)
}
/*
go run ./datastructure/LinkedList
*/