package main

import "fmt"

func dailyTemperatures(temperatures []int) []int {
	answer := make([]int, len(temperatures))
	stack := []int{}
	n := len(temperatures)
	for i := 0; i < n; i++ {
		cur := temperatures[i]
		stackSize := len(stack)
		for j := stackSize - 1; j > -1; j-- {
			index := stack[j]
			if cur > temperatures[index] {
				stack = stack[:j]
				answer[index] = i - index
			}
		}
		stack = append(stack, i)
	}
	return answer
}
func main() {
	fmt.Println("Ans = ", dailyTemperatures([]int{73, 74, 75, 71, 69, 72, 76, 73}))
}

/*
思路:
每次得到新溫度, 一定檢查它是否比之前的大
	比之前的大, 剔除舊的--->剔除時要給answer賦值
	比之前的小, 加入此溫度

盯著main的這組測資, 從頭跑一遍所有步驟, 得出下方
stack = [73], answer = [0, 0, 0, 0, 0, 0, 0, 0]
stack = [74], answer = [1, 0, 0, 0, 0, 0, 0, 0]
stack = [75], answer = [1, 1, 0, 0, 0, 0, 0, 0]
stack = [75, 71], answer = [1, 1, 0, 0, 0, 0, 0, 0]
stack = [75, 71, 69],answer = [1, 1, 0, 0, 0, 0, 0, 0]
stack = [75, 72], answer = [1, 1, 0, 2, 1, 0, 0, 0]
stack = [76], answer = [1, 1, 4, 2, 1, 1, 0, 0]
stack = [76, 73] ,answer = [1, 1, 4, 2, 1, 1, 0, 0]

在推導的時候我們沒辦法斷定要用stack, 當時只先命名為list,
後來發現先進去的元素反而先出, 符合棧的 LIFO 概念--->斷定要用單調棧

起初 stack 裡面裝的是值, 後來發現裝index才是正解, 為了方便操作stack裡面就裝index
例如:
index := stack[j] <---這一步的 index 可以映射到answer, temperatures
answer[index]
temperatures[index]


回到最一開始思路, 我們會檢查新溫度 vs stack 中的舊溫度
如新溫度較大, 就要擠掉 stack 中的舊溫度, 並更新answer

Q 如何更新answer?
A 更新answer 最簡單方式如下:
	answer[index] = i - index

這部分當初寫的時候卡很久, 最後靠Leet提示才寫出來

提示如下
想一想：當你從 stack 中 pop 出一個 index，而當前位置是 i，那「需要等幾天」的答案其實可以直接用什麼算出來？

💡 提示：answer[index] 的值跟 i 和 index 的關係是什麼？
*/
