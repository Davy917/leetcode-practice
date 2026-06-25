// 照抄java版本並改寫
package main

import (
	"fmt"
	"strconv"
	// "strconv"
	"gopractice/datastructure/Tree"
	"strings"
)

type TreeNode = Tree.TreeNode
type Codec struct {
}

func Constructor() Codec {
	return Codec{}
}

func (this *Codec) serialize(root *TreeNode) string {
	if root == nil {
		return "null"
	}
	left := this.serialize(root.Left)
	right := this.serialize(root.Right)
	return fmt.Sprintf("%d,%s,%s", root.Val, left, right)
}

func (this *Codec) deserialize(data string) *TreeNode {
	if data == "" {
		return nil
	}
	list := strings.Split(data, ",")
	fmt.Println("list = ", list)
	return this.rdeserialize(&list)
}
func (this *Codec) rdeserialize(list *[]string) *TreeNode {
	if len(*list) == 0 {
		return nil
	}
	val := (*list)[0]
	*list = (*list)[1:]
	if val == "nil" {
		return nil
	}
	valInt, _ := strconv.Atoi(val)
	root := &TreeNode{Val: valInt}
	root.Left = this.rdeserialize(list)
	root.Right = this.rdeserialize(list)
	return root
}
func main() {
	nums := []any{1, 2, nil, nil, 3, 4, nil, nil, 5, nil, nil}
	root := Tree.BuildPreorderTree(nums)
	ser := Constructor()
	deser := Constructor()
	data := ser.serialize(root)
	deser.deserialize(data)
}

/*
照抄java版本並改寫
297-codec/codec.java

FAQ:
介紹 strings.Split() 方法
strings.Split() 實際上是把字串分割成若干個「子字串」，然後把這些子字串裝在「一個」切片（Slice）裡面返回。

40~41 行為什麼是 (*list)[0]，而不是 list[0]？
1. list 的型態是 *[]string： 它是一個「指向切片（Slice）的指標」，而不是切片本身。
2. 需要先「解引用（Dereference）」： 如果我們要拿到切片裡的內容，必須使用 * 符號來解引用。所以 *list 代表「切片本身」。
3. 優先級問題： 在 Go 語言中，索引運算子 [] 的優先級比解引用運算子 * 還要高。
		1. 如果你寫 *list[0]，Go 會解讀成 *(list[0])。這代表「先去拿指標 list 的第 0 個元素，然後對它做解引用」。但 list 只是個指標，根本沒有第 0 個元素，所以編譯會報錯。
		2. 為了強迫 Go 先做解引用，我們必須用括號把 *list 包起來，寫成 (*list)，然後再去拿它的第 0 個元素 [0]。
所以 (*list)[0] 的意思就是：「先取得 list 指向的那個切片，再拿出該切片的第 0 個元素」。

45 行是什麼意思
這行程式碼的作用是將字串轉成整數，並處理 Go 語言的多重回傳值。
我們拆解成三個部分來看：
1. strconv.Atoi(val)
	1. strconv 是 Go 的標準庫，專門處理字串轉換（String Conversion）。
	2. Atoi 代表 Ascii to of integer（將 ASCII 字串轉為整數）。例如把字串 "123" 轉成整數 123。
2. sreconv會有兩個回傳值, 分別代表
	1. 轉換後的整數（int）
	2. 錯誤訊息（error）—— 如果你傳入一個不能轉成數字的字串（例如 "abc"），它就會回傳錯誤。

	它的函數宣告長這樣：
	func Atoi(s string) (int, error)

3. 為什麼要用底線 _？
在 Go 語言中，所有宣告的變數都必須被使用，否則編譯會失敗。
如果我們寫 valInt, err := strconv.Atoi(val)，但後面沒有用到 err，編譯器就會報錯。
	1. 底線 _ 是 Go 的「空白識別碼（Blank Identifier）」。它就像一個垃圾桶，用來接收我們「不需要、不想處理」的回傳值。
	2. 在 LeetCode 這題中，我們很確定除了 "nil" 之外，其他的字串一定都是合法的數字字串（例如 "1", "2"），轉換絕對不會失敗。
	3. 因此，我們用 valInt 接收轉換後的整數，並用 _ 直接丟棄錯誤訊息（error）。
*/
