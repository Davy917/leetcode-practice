package main

import (
	"fmt"
	linkedlist "gopractice/datastructure/LinkedList"
)

func main(){
	dll := linkedlist.NewDoublyLinkedList()
	dll.Append(1)
	dll.Append(2)
	dll.Append(3)
	fmt.Println(dll.ToSlice())
	dll.AppendLeft(4)
	fmt.Println(dll.ToSlice())
	dll.RemoveTail()
	fmt.Println(dll.ToSlice())
	dll.RemoveHead()
	fmt.Println(dll.ToSlice())
}
/*
執行指令
go run ./datastructure/LinkedList/demo
*/