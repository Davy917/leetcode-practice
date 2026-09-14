package main

import (
	"fmt"
)

func numSquares(n int) int {
	get := func(n int) (result []int) {
		i := 1
		j := 3
		for i <= n {
			result = append(result, i)
			i += j
			j += 2
		}
		return
	}

	deque := get(n)
	if deque[len(deque)-1] == n { //如果 n 本身就是完全平方數, 直接返回 1
		return 1
	}
	visited := make(map[int]bool)
	for _, v := range deque {
		visited[v] = true
	}

	fmt.Printf("deque = %v, visited = %v\n", deque, visited)
	step := 0
	for len(deque) > 0 {
		step++
		layerSize := len(deque)
		for i := 0; i < layerSize; i++ {
			cur := deque[0]
			deque = deque[1:]
			next := get(n - cur)
			for _, val := range next {
				new := cur + val
				if new == n {
					step++
					return step //找到答案
				}
				if !visited[new] { //該組合未被嘗試過, 應該入隊
					deque = append(deque, new)
					visited[new] = true
				}
			}
		}
	}
	return -1 //理論上不會走到這
}
func main() {
	fmt.Println("Ans = ", numSquares(12))
}

/*
自己寫的

盯著這一組測資：
输入：n = 12
输出：3
解释：12 = 4 + 4 + 4

假設 n = 12, 能被用來相加的squareNum = [1, 4, 9]
先試
9 + 1 + 1 + 1
再試
4 + 4 + 4 <---答案

cur = 0 當前累計的總數
cur + 4, cur < n, 此時 9 已經不可能用了
cur + 4, cur < n
cur + 4, cur == n

每一次都可以把1, 4, 9, 納入考慮範圍, 可以確定的是每次一定從[1, 4, 9]當中選一個加進cur
[1, 4, 9] 怎麼來的, 完全平方數最小一定從1開始, 接著是2 x 2 = 4, 3 x 3 = 9.....以此類推
根據上述推論, 寫個 get 方法, 輸入n, 返回小於等於 n 的所有squareNums

Q: get方法怎麼寫最簡單?
A: 找規律
squareNums = [1, 4, 9, 16...]
差值 = [3, 5, 7...]
get方法就是透過這個差值規律拼湊出小於等於 n 的所有squareNums

回到numSquares函式
Q: bfs該怎麼寫?
A: 一開始我們可以從[1, 4, 9]出發, 所以一定是多源bfs, 假設我選了從 4 出發, 下一步一定不可能走到 9 因為 4 + 9 = 13 已大於 12
	從 4 出發下一步只能選1 跟 4,這時我想到可以利用 get 方法

Q: 如何使用get方法?
A: 我走了 4, 那距離終點12的距離為12 - 4 = 8,  我把 8 傳入get, 他會回給我[1, 4]正好符合這題需求,
	cur 代表, 我現在已經走多遠, n - cur 代表距離目標還多遠, 所以就這樣調用 get(n - cur) <---Line34

Q: visited的作用?
A: 假設你在某一步發現, 我現在 + 未來, 即將走出的距離為 8, 但 8 曾經在某個時間點已經走過了, 那你現在走的這條路一定不可能是最短路徑
	此時 visited[8] = True, 就代表 8 不應該被入隊, 因為它可能已經在隊中, 或是已經走過了(出隊了)

建議自己在紙上畫路線圖, 以及deque是怎麼變化的, 搭配這邊的歸納總結

相似題:
752-open-lock\solution.go
*/
