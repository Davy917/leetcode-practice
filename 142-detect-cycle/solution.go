// hashset 請看 detectCycle_v2
package main

import "fmt"

type ListNode struct {
	val  int
	Next *ListNode
}

type LinkedList struct {
	head *ListNode
	tail *ListNode
	size int
}

func (ll *LinkedList) append(val int) {
	node := ListNode{val: val}
	if ll.head == nil {
		ll.head = &node
		ll.tail = &node
	} else {
		ll.tail.Next = &node
		ll.tail = &node
	}
	ll.size++
}

// map寫法, 自己寫的
func detectCycle(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	m := map[*ListNode]int{}
	index := 0
	cur := head
	m[cur] = index
	for cur.Next != nil {
		cur = cur.Next
		index += 1
		_, exist := m[cur]
		if !exist {
			m[cur] = index
		} else {
			return cur
		}
	}
	return nil
}

// 官方版 set寫法 + 優化
func detectCycle_v2(head *ListNode) *ListNode {
	m := map[*ListNode]struct{}{}
	cur := head
	for cur != nil {
		_, exist := m[cur]
		if exist {
			return cur
		}
		m[cur] = struct{}{}
		cur = cur.Next
	}
	return nil
}

func main() {
	ll := LinkedList{}
	head := []int{3, 2, 0, 4}
	for _, v := range head {
		ll.append(v)
	}
	//手動製造環形鏈表
	//ll.tail.Next = ll.head.Next
	fmt.Println("Ans = ", detectCycle(ll.head))
	fmt.Println("Ans = ", detectCycle_v2(ll.head))
}
