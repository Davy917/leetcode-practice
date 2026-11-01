package main

import "fmt"
type ListNode struct{
	Val int
	Next *ListNode
}
type MyLinkedList struct {
	head *ListNode
	size int
}
func Constructor() MyLinkedList {
	return MyLinkedList{nil, 0}
}
func (this *MyLinkedList) Get(index int) int {
    if index < 0 || index >= this.size{
		return -1
	}
	cur := this.head
	k := 0
	for k < index && cur.Next != nil {
		cur = cur.Next
		k++
	}
	return  cur.Val
}
func (this *MyLinkedList) AddAtHead(val int)  {
	this.AddAtIndex(0, val)
}
func (this *MyLinkedList) AddAtTail(val int)  {
	this.AddAtIndex(this.size, val)
}
func (this *MyLinkedList) AddAtIndex(index int, val int)  {
	if index > this.size{
		return
	}	
	dummy := &ListNode{-1, this.head}
	newNode := ListNode{val, nil}
	cur := dummy
	j := 0
	for j < index{
		cur = cur.Next
		j++
	}
	if index == 0 {
		dummy.Next = &newNode
		newNode.Next = this.head
		this.head = &newNode
	} else {
		temp := cur.Next
		cur.Next = &newNode
		newNode.Next = temp
	}
	this.size++
}

func (this *MyLinkedList) DeleteAtIndex(index int)  {
    if index < 0 || index >= this.size{
		return
	}
	dummy := &ListNode{-1, this.head}
	cur := dummy
	j := 0
	for j < index{
		cur = cur.Next
		j++
	}

	if index == 0{
		this.head = this.head.Next
		dummy.Next = this.head
	} else {
		if cur.Next != nil && cur.Next.Next != nil {
			cur.Next = cur.Next.Next
		} else{
			cur.Next = nil
		}
	}
	this.size--
}
func main(){
	ll := Constructor()
	ll.AddAtTail(1)
	ll.AddAtTail(2)
	ll.AddAtIndex(2, 3)
	ll.AddAtHead(0)
	ll.AddAtIndex(1, 4)
	ll.DeleteAtIndex(1)
	fmt.Println(ll.Get(1))
}

/*
自己寫的版本
可以簡化:
AddAtHead
AddAtTail
AddAtIndex
把前兩者的功能搭建在AddAtIndex, 增加代碼可維護性

DeleteAtIndex 也套用 AddAtIndex (dummy + 迴圈遍歷)
所以邏輯是一樣的, 一開始只需要專注寫AddAtIndex, 其它就不會有太大問題
*/