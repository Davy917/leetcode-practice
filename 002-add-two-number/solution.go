package main

import (
	"gopractice/datastructure/LinkedList"
)

type ListNode = LinkedList.ListNode

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	dummy := &ListNode{Val: -1, Next: nil}
	cur := dummy
	sum, diff := 0, 0
	for l1 != nil || l2 != nil {
		if l1 == nil {
			sum = l2.Val + diff
			l2 = l2.Next
		} else if l2 == nil {
			sum = l1.Val + diff
			l1 = l1.Next
		} else {
			sum = l1.Val + l2.Val + diff
			l1 = l1.Next
			l2 = l2.Next
		}
		if sum >= 10 {
			sum -= 10
			diff = 1
		} else {
			diff = 0
		}
		newNode := &ListNode{Val: sum, Next: nil}
		cur.Next = newNode
		cur = cur.Next
	}
	if diff == 1 { //最後有溢位的話再補上
		cur.Next = &ListNode{Val: 1, Next: nil}
	}
	return dummy.Next
}

func main() {
	ll1 := LinkedList.Constructor()
	ll2 := LinkedList.Constructor()
	head1 := ll1.BuildLinkedList([]int{9, 9, 9, 9, 9, 9, 9})
	head2 := ll2.BuildLinkedList([]int{9, 9, 9, 9})
	ll1.PrintList(addTwoNumbers(head1, head2))
}

/*
自己寫的:

一樣先在紙上畫圖
假設:
[3, 4, 6]
   [7, 6]

題目的意思就是先反轉成這樣:
[6, 4, 3]
   [6, 7]

然後再做加法 643 + 67 = 710

710再反轉成017 return 0-->1-->7

先反轉 -> 從最低位開始做加法 -> 做完加法再反轉

再仔細看一遍會發現
先反轉 -> 從最低位開始做加法 == 不用反轉, 把最高位對齊, 直接從最高位開始做加法

示意圖:
[3, 4, 6]
[7, 6]

原本的資料不用反轉, 把最高位對齊做加法, 有溢位就往右傳遞
最後我們仍然得到0-->1-->7
*/
