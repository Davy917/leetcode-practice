/*
自己寫的基本解java
自己寫的最佳解go
*/
package main

import (
	"gopractice/datastructure/LinkedList"
)

type ListNode = LinkedList.ListNode

func getIntersectionNode(headA, headB *ListNode) (result *ListNode) {
	fst, sec := headA, headB
	fstFlag, secFlag := false, false
	for true {
		if fst == sec {
			return fst
		}
		fst = fst.Next
		sec = sec.Next
		if fst == nil && fstFlag == false {
			fst = headB
			fstFlag = true
		}
		if sec == nil && secFlag == false {
			sec = headA
			secFlag = true
		}
	}
	return result
}

/*
自己寫的
時間O(m+n), 空間O(n)很容易就能想到
時間O(m+n), 空間O(1)要怎麼寫?

Step1:
需要用雙指針, 大概率會是
if A指針 == B指針
	return當前節點值

Step2:
只能從遍歷方式下手:
在紙上畫出
[1, 9, 1, 2, 4]
	  [3, 2, 4] 已知2, 4 是相交節點

再根據Step1的推測, 要能夠滿足(A指針 == B指針)只能把兩個鏈表並在一起, 如下:
[1, 9, 1, 2, 4, 3, 2, 4]
[3, 2, 4, 1, 9, 1, 2, 4]

然後我們就會發現2, 4確實在尾部重疊了, 再多測幾組也是這個現象

Step3:
可以得出結論:
第一指針, 先走A鏈表, 再走B鏈表
第二指針, 先走B鏈表, 再走A鏈表

兩指針走的距離會相同:
A + B == B + A

如有相交節點, 通常會在尾部或遍歷的過程中顯現, 也就是(A指針 == B指針)
如此一來就是靠著設計遍歷順序來達到, 時間O(m+n), 空間O(1)

用其它語言寫的時候可以試試遞歸的寫法
*/
