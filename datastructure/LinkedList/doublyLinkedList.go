package linkedlist

type DoublyLinkedNode struct{
	val int
	next *DoublyLinkedNode
	prev *DoublyLinkedNode
}
type DoublyLinkedList struct{
	head *DoublyLinkedNode
	tail *DoublyLinkedNode
	size int
}

func NewDoublyLinkedList() *DoublyLinkedList {
	return &DoublyLinkedList{}
}

//func (接收者) 方法名(參數) 回傳型別
func (dll *DoublyLinkedList) Append(val int) {
	node := &DoublyLinkedNode{val: val}
	if dll.head == nil{
		dll.head = node
		dll.tail = node
	}else{
		node.prev = dll.tail
		dll.tail.next = node
		dll.tail = node
	}
	dll.size++	
}

func (dll *DoublyLinkedList) AppendLeft(val int){
	node := &DoublyLinkedNode{val: val}
	if dll.head == nil{
		dll.head = node
		dll.tail = node
	}else{
		node.next = dll.head
		dll.head.prev = node
		dll.head = node
	}
	dll.size++
}

func (dll *DoublyLinkedList) RemoveTail() {
	if dll.tail == nil{
		return
	}
	dll.tail = dll.tail.prev
	dll.tail.next = nil
	dll.size--
}

func (dll *DoublyLinkedList) RemoveHead(){
	if dll.head == nil{
		return
	}
	dll.head = dll.head.next
	dll.head.prev = nil
	dll.size--
}



func (dll *DoublyLinkedList) Len() int {
	return dll.size
}

func (dll *DoublyLinkedList) ToSlice() (result []int){
	curNode := dll.head
	for curNode != nil{
		result = append(result, curNode.val)
		curNode = curNode.next
	}
	return
}
/*
到 main.go 執行
datastructure/LinkedList/demo/main.go

接收器函式
LanguagePractice/GoPractice/reciverFunc.go
*/