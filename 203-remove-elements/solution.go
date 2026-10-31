/*
第一次練習版本java, python
第二次自己寫的go
*/
package main
import (
	"gopractice/datastructure/LinkedList"
)
type ListNode = LinkedList.ListNode
func removeElements(head *ListNode, val int) *ListNode {
    dummy := &ListNode{Val: -1, Next: head}
    cur := dummy
    for cur.Next != nil{
        for cur.Next.Val == val{
            if cur.Next.Next != nil{
                cur.Next = cur.Next.Next
            } else{
                cur.Next = nil
                return dummy.Next
            }
        }
        cur = cur.Next
    }
    return dummy.Next
}
func main(){
	ll := LinkedList.NewLinkedList()
	head := ll.BuildLinkedList([]int {7,7,7,7})
	ll.PrintList(head)
	removeElements(head, 7)
}


/*
自己寫的
只用一個for迴圈遇到 head = [7,7,7,7] 這組就會陣亡

第二個for迴圈:
    處理連續移除的情況
最後直接讓cur移動到下一個安全的節點

代碼結構:
for 下個節點還有東西嗎?
    for 下個節點需要刪除嗎?
        需要
            檢查下下個節點是否存在
                存在
                    刪掉之後, 繼續循環
                不存在
                    走到邊界了, 刪掉之後直接return
        不需要  
            退出for迴圈
    移動到下一個節點

沒有畫圖的情況下很難抽像, 建議配合畫圖
*/