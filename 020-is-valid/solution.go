package main

import (
	"fmt"
)

func isValid(s string) bool {
	stack := make([]string, 0, len(s))
	for _, char := range s {
		toAdd := string(char)
		if len(stack) > 0 {
			n := len(stack) - 1 //stack最後一個元素的下標
			lastIndex := stack[n]
			fmt.Printf("lastIndex = %s toAdd= %s\n", lastIndex, toAdd) // debug
			switch lastIndex {
			case "(":
				if toAdd == ")" {
					stack = stack[:n]
				} else {
					stack = append(stack, toAdd)
				}
			case "{":
				if toAdd == "}" {
					stack = stack[:n]
				} else {
					stack = append(stack, toAdd)
				}
			case "[":
				if toAdd == "]" {
					stack = stack[:n]
				} else {
					stack = append(stack, toAdd)
				}
			default:
				stack = append(stack, toAdd)
			}
		} else {
			stack = append(stack, toAdd)
		}
	}
	if len(stack) == 0 {
		return true
	}
	return false
}
func main() {
	fmt.Println("Ans = ", isValid("()[]{}"))
}

/*
自己寫的
toAdd: 即將塞入的字元
每次往stack加入 toAdd 前都檢查stack的最後元素是不是跟 toAdd 匹配
匹配: toAdd不加入stack, 並且把stack 最後元素移除
不匹配: toAdd加入stack

最後:
s的元素都被判定完
	stack有殘留元素, return False
	stack無殘留元素, return True

代碼結構:
stack內是否有元素?
	stack內有元素--->要檢查是否匹配
		"("---> 預期toAdd是")"才能匹配
		"{"---> 預期toAdd是"}"才能匹配
		"["---> 預期toAdd是"]"才能匹配
	stack內無元素--->直接加入toAdd
*/
