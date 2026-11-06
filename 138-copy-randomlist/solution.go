/*
第一次練習看java
第二次練習看python
第三次練習看Go (含思路)
*/
package main

import "math"

type Node struct {
	Val    int
	Next   *Node
	Random *Node
}

func copyRandomList(head *Node) *Node {
	pair := map[*Node]*Node{}
	deque := []*Node{}
	dummy := &Node{math.MinInt, head, nil}
	cur := dummy
	for cur.Next != nil {
		cur = cur.Next
		pair[cur] = &Node{cur.Val, nil, nil}
		deque = append(deque, cur.Random)
	}
	copyDummy := &Node{math.MinInt, nil, nil}
	copy := copyDummy
	cur = dummy
	for cur.Next != nil {
		copy.Next = pair[cur.Next]
		copy.Next.Random = pair[deque[0]]
		deque = deque[1:]
		copy = copy.Next
		cur = cur.Next
	}
	return copyDummy.Next
}

/*
自己寫的
思路:
代碼結構
第一個迴圈:
	原節點拷貝一份, 標記對應的拷貝節點是誰 ---> 用字典儲存
	把原節點的random存到deque

// 因為是從頭遍歷, 所以 deque 的random定是按順序排列
// 至此就可以實現: 拷貝節點.random = 字典[隊列[0]]

cur = dummy (cur 重置)

第二個迴圈:
	拷貝節點.Next = 字典[cur.Next]
	拷貝節點.random = 字典[隊列[0]]
	deque[0]出隊
	原鏈表 & 拷貝鏈表指針往下走

這題有非常多種寫法, 個人覺得這樣的思路以及結構是最乾淨易懂的
所以保留這個版本
*/
