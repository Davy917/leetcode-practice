package main

import (
	"fmt"
	linkedlist "gopractice/datastructure/LinkedList"
)

func main(){
	// DoublyLinkedList 測試
	// dll := linkedlist.NewDoublyLinkedList()
	// dll.Append(1)
	// dll.Append(2)
	// dll.Append(3)
	// fmt.Println(dll.ToSlice())
	// dll.AppendLeft(4)
	// fmt.Println(dll.ToSlice())
	// dll.RemoveTail()
	// fmt.Println(dll.ToSlice())
	// dll.RemoveHead()
	// fmt.Println(dll.ToSlice())

	nums := []int{1, 5, 6, 7, 9, 5}
	ll := linkedlist.NewLinkedList()
	ll.BuildLinkedList(nums)
}
/*
執行指令
go run ./datastructure/LinkedList/demo
*/