package main

import "fmt"

func slice() {

	//宣告array以及slice
	array := [5]int{1, 2, 3, 4, 5}
	var s1 []int = array[0:len(array)] //這裡只是引用, 實際上還是指向底層array
	//var s1 []int = array[:]上面那行可以簡寫成這樣
	s1[0] = 0
	fmt.Println(array)

	s2 := s1[1:]
	s2[0] = 0
	fmt.Println(array)

	//長度與容量
	var s3 []int
	fmt.Println("s3 == nil??", s3 == nil)
	s3 = make([]int, 3, 5) //代表長度為3, 但是最長可以達到長度5
	fmt.Printf("len(s3) = %v, cap(s3) = %v\n", len(s3), cap(s3))
	s3 = make([]int, 3) //不寫cap, 默認容量與len相同
	fmt.Printf("len(s3) = %v, cap(s3) = %v\n", len(s3), cap(s3))

	//長度與容量/最簡寫
	s4 := []int{1, 2, 3} //由系統自動創建底層數組
	fmt.Println("s4 = ", s4)

	//追加元素
	s1 = append(s1, 6, 7, 8) //底層創建一個新數組不再引用原數組
	fmt.Printf("array = %v, s1 = %v\n", array, s1)

	s5 := append(s1, s2...)
	fmt.Println("s5 = ", s5)

	//複製數組
	s6 := []int{1, 1}
	copy(s5, s6) //容量能接收多少, 就接收多少
	fmt.Println("s5 = ", s5)
	str := "hello world"
	fmt.Printf("str = %s\n", str)
	for i, v := range str {
		fmt.Printf("str[%d] = %c\t", i, v)
	}
	fmt.Println()
	key := selectByKey("register", "log in", "log off")
	fmt.Println("key = ", key)
}

func selectByKey(text ...string) (key int) {
	for i, v := range text {
		fmt.Printf("%v, %v\n", i+1, v)
	}
	fmt.Println("Please select number")
	fmt.Scanln(&key)
	return // key 已經是回傳變數，return 不寫值也可以
}

/*
FAQ
什麼是nil??
GoPractice 的 `nil` 與 Java 的 `null` 及 Python 的 `None` 概念相似，但有差異：

			|  GoPractice `nil` | Java `null` | Python `None` |
| 適用類型 	| slice、map、channel、pointer、function、interface | 所有引用類型 | 任何類型 |
| 是否有類型 	| 有（帶類型的 nil） | 無類型 | 無類型（單例） |
| 零值		| slice/map 等的零值就是 `nil` | 引用類型默認 `null` | 需顯式賦值 |

**關鍵差異：**

- GoPractice 中 `nil` 是帶類型的，例如 `(*int)(nil) != ([]int)(nil)` 在 interface 比較時會有差異
- GoPractice 的 `nil` slice（如你代碼中的 `s3`）仍然可以對其執行 `len()`、`append()` 等操作，不會 panic，這點比 Java `null` 更安全
- Java `null` 呼叫任何方法都會拋 `NullPointerException`，Python `None` 也類似

你代碼第 14\~15 行的 `var s3 []int` 就是 slice 的零值，等於 `nil`，這在 GoPractice 中是完全合法的狀態。

什麼是make??

make([]int, 3, 5)
make([]Type, len, cap)

Go 語言中的 ... 符號（在 Go 官方術語中稱為 Ellipsis，省略號） 1 2，
在功能與概念上與 JavaScript 的擴展運算子（Spread）以及其餘運算子（Rest）非常相似。  

詳細內容可以直接看
LanguagePractice/JSPractice/basic.js

影片:
https://www.youtube.com/watch?v=pnwNpeH_06I&list=PLBjZhzRvV2ChPTPNDx_apHdKa9Ha7LVpN&index=20

什麼是...符號?
詳見擴展運算子說明
LanguagePractice/Compare4Language/Spread operator.md
*/
