package main

import (
	test "gopractice/datastructure/LinkedList"
)

func main() {
	nums := []int{1, 5, 6, 7, 9, 5}
	ll := test.NewLinkedList()
	ll.BuildLinkedList(nums)
	ll.PrintList(ll.Head)
}

/*
執行指令
go run ./datastructure/LinkedList/demo
*/
