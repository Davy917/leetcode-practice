package main

import "fmt"

func openLock(deadends []string, target string) int {
	if target == "0000" {
		return 0
	}

	dead := map[string]bool{} //dead用來記錄, 那些數字不能走
	for _, s := range deadends {
		dead[s] = true //不能走的為true
	}
	if dead["0000"] {
		return -1 //如果一開始就dead, 直接結束
	}

	get := func(cur string) (result []string) {
		s := []byte(cur) //[48, 48, 48, 48]
		for i, v := range s {
			s[i] = v - 1
			if s[i] < '0' { //'0' 的整數值是 48
				s[i] = '9'
			}
			result = append(result, string(s))
			s[i] = v + 1
			if s[i] > '9' { //'9' 的整數值是 57
				s[i] = '0'
			}
			result = append(result, string(s))
			fmt.Printf("result = %v\ns = %s\n", result, s)
			s[i] = v //把s[i] reset
		}
		return
	}

	step := 0
	queue := []string{"0000"}
	visited := map[string]bool{"0000": true}
	for len(queue) > 0 {
		layerSize := len(queue)
		step++
		for i := 0; i < layerSize; i++ {
			cur := queue[0]
			queue = queue[1:]
			next := get(cur)
			for _, new := range next {
				if !visited[new] && !dead[new] {
					if new == target {
						return step
					}
					queue = append(queue, new)
					visited[new] = true
				}
			}
		}
	}
	return -1
}

func main() {
	deadends := []string{"0201", "0101", "0102", "1212", "2002"}
	target := "0202"
	openLock(deadends, target)
}

/*
leetbook解答:
https://leetcode.cn/problems/open-the-lock/description/


get函式說明:
get 會回傳從當前四位密碼能一步到達的所有狀態（每一位各 +1 / -1，會循環 0↔9），回傳值長度為 2 * len(status)。

舉例:
假設我們在 "0000" 調用了 get, 我們會得到 ["1000", "9000", "0100", "0900", "0010", "0090", "0001", "0009"]
陣列中這8個元素就是一步可以到達的, 這就是get的作用, 把當前數字給它, 它會返回下一步能走到的8個數值

最容易看不懂的 if s[i] < '0'

在 Go 裡 '0', '9' 可視為數值（rune/byte），
所以 s[i] < '0' 比較的是 47 < 48；當 b == '0' 時 b-1 == 47，會觸發 if，再把那位設回 '9'（57）。

當 b 等於字元 '0'（ASCII 值 48）時，b - 1 == 47。
因為在你的程式 b 是 byte，所以 48 - 1 = 47；此時 if s[i] < '0' 會成立，程式才把該位設回 '9'。

總結:
這題與其它題不同之處在於, 它不是在陣列中去做bfs, 而是在"0000" ~ "9999"
queue不再是裝座標, 而是直接放置string
visited不再是二維陣列, 而是map[string]bool
get 巧妙地確定了接下來的 8 步會走哪


相似題:
279-num-squares\solution.go
*/
