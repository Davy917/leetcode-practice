package main

import (
	"gopractice/datastructure/LinkedList"
)

type ListNode = LinkedList.ListNode

func mergeTwoLists(list1 *ListNode, list2 *ListNode) *ListNode {
	var cur *ListNode
	fst, sec := list1, list2
	dummy := &ListNode{Val: -101, Next: nil}
	cur = dummy
	for fst != nil && sec != nil {
		if fst.Val == sec.Val {
			cur.Next = fst
			cur = cur.Next
			fst = fst.Next
			cur.Next = sec
			cur = cur.Next
			sec = sec.Next
		} else if fst.Val < sec.Val {
			cur.Next = fst
			cur = cur.Next
			fst = fst.Next
		} else {
			cur.Next = sec
			cur = cur.Next
			sec = sec.Next
		}
	}
	if fst == nil {
		cur.Next = sec
	}
	if sec == nil {
		cur.Next = fst
	}
	return dummy.Next
}
func main() {
	ll1 := LinkedList.Constructor()
	ll2 := LinkedList.Constructor()
	head1 := ll1.BuildLinkedList([]int{1, 7, 8})
	head2 := ll2.BuildLinkedList([]int{0, 2, 3})
	ll1.PrintList(mergeTwoLists(head1, head2))
}

/*
自己寫的

思路:
先預設這題不需要用到額外空間, 開始設計遍歷的走法

初始化:
cur = d
   f
d, 1, 7, 8
   s
d, 0, 2, 3
f, s 就是cur 要去的地方

step1:
0 < 1 所以 c 到0, s往前走一步
   f
d, 1, 7, 8
   c  s
d, 0, 2, 3

step2:
1 < 2 所以 c 到1, f往前走一步
   c  f
d, 1, 7, 8
      s
d, 0, 2, 3

step3:
2 < 7 所以 c 到2, s往前走一步
      f
d, 1, 7, 8
      c  s
d, 0, 2, 3

step4:
3 < 7 所以 c 到3, s往前走一步走到null
      f
d, 1, 7, 8
         c  s
d, 0, 2, 3 null

退出迴圈
可以發現c走過的路徑數字一定由小到大排列, 如此就能夠把兩條鏈表由小到大排列
狀態圖畫出來之後代碼的輪廓也就出來了
*/
