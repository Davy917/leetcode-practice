/*
基本解看java
最佳解看python, go
*/
package main

import (
	"fmt"
	"gopractice/datastructure/LinkedList"
)

type ListNode = LinkedList.ListNode

func isPalindrome(head *ListNode) bool {
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}
	if fast.Next != nil { //處理節點總數為偶數的情形
		fast = fast.Next
	}
	tail := reverseList(slow.Next)
	slow.Next = nil
	cur := head
	for tail != nil{
		fmt.Printf("tail = %d, cur = %d\n", cur.Val, tail.Val) // debug
		if tail.Val != cur.Val {
			return false
		}
		tail = tail.Next
		cur = cur.Next
	}
	return true
}
func reverseList(head *ListNode) *ListNode {
	cur := head
	var pre *ListNode
	for cur != nil {
		temp := cur.Next
		cur.Next = pre
		pre = cur
		cur = temp
	}
	return pre
}
func main() {
	ll := LinkedList.NewLinkedList()
	head := ll.BuildLinkedList([]int{1, 2, 3, 2, 1})
	isPalindrome(head)
}

/*
(務必在紙上逐步推倒, 而不是空想)

反轉鏈表直接看這題 206-reverse-list

挑戰:
時間複雜度O(n), 空間複雜度O(1), 不能用雙向鏈表實現

首先:
用快慢指針定位中間點以及尾節點, 想到這步才有機會寫出來

示意圖:
[1, 2, 3, 2, 1]
	   S
	   		 f

把一個鏈表拆成這樣
[1, 2, 3, nil]
[2, 1] <-------這組拿去做反轉鏈表變[1, 2]

最後得到:
[1, 2, 3, nil]
[1, 2]
憑這兩組加工過的資料就能知道原鏈表是否為回文鏈表

最佳解 = 快慢指針 + 反轉鏈表
*/
