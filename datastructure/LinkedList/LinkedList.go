package linkedlist

type LinkedList struct{
	head *ListNode
	tail *ListNode
	size int
}

func NewLinkedList() *LinkedList {
	return &LinkedList{}
}

func (ll *LinkedList) AddAtTail(val int) {
	newNode := &ListNode{Val: val}
	if ll.size == 0 {
		ll.head = newNode
		ll.tail = newNode
	}
	ll.tail.Next = newNode
	ll.tail = newNode
	ll.size++
}

func (ll *LinkedList) BuildLinkedList(nums []int) (head *ListNode){
	ll.head = nil
	for _, num := range nums {
		ll.AddAtTail(num)
		ll.size++
	}
	return ll.head
}