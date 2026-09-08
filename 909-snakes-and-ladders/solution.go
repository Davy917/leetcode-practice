package main

import "fmt"

func snakesAndLadders(board [][]int) int {
	rows, cols := len(board), len(board[0])
	area := rows * cols
	arr := make([]int, area+1)
	visited := make([]bool, area+1)
	k, j := 1, 0
	for i := rows - 1; i > -1; i-- {
		if j == 0 {
			for j < cols {
				arr[k] = board[i][j]
				k++
				j++
			}
			j--
		} else {
			for j > -1 {
				arr[k] = board[i][j]
				k++
				j--
			}
			j++
		}
	}
	fmt.Println(arr)
	var bfs func(int) int
	bfs = func(firstIndex int) int {
		minPath := 0
		visited[firstIndex] = true
		deque := []int{firstIndex} //deque裝的是1
		for len(deque) > 0 {
			layerSize := len(deque)
			minPath++
			for i := 0; i < layerSize; i++ {
				curIndex := deque[0] //cur一開始是 1
				deque = deque[1:]
				for next := 1; next < 7; next++ {
					nextIndex := curIndex + next
					nextVal := arr[nextIndex]                 //用來檢查下一個節點有無蛇 or 梯子
					if nextVal == area || nextIndex == area { //可能是徒步或爬梯走到終點, 都算數
						return minPath
					}
					if nextVal == -1 && !visited[nextIndex] { //無蛇 or 梯子
						deque = append(deque, nextIndex)
						visited[nextIndex] = true
					}
					if nextVal != -1 && !visited[nextVal] { //有
						deque = append(deque, nextVal)
						visited[nextVal] = true
					}
				}
			}
		}
		return -1
	}
	return bfs(1)
}
func main() {
	board := [][]int{{-1, -1, -1, -1, -1, -1},
		{-1, -1, -1, -1, -1, -1},
		{-1, -1, -1, -1, -1, -1},
		{-1, 35, -1, -1, 13, -1},
		{-1, -1, -1, -1, -1, -1},
		{-1, 15, -1, -1, -1, -1}}
	fmt.Println("Ans = ", snakesAndLadders(board))
}

/*
自己寫的

先把定義弄清楚:
1. 梯子總是保證前進, 蛇總是保證後退

2. 為什麼題目要寫min(curr + 6, n²) ??
n² 是棋盤的最後一格（終點）。當你接近終點時，curr + 6 很可能超過 n²。

3. 每次移動時，你模擬擲一個六面骰子（點數 1~6），所以只能走到：
curr + 1, curr + 2, curr + 3, curr + 4, curr + 5, curr + 6

模式識別:
求最短路徑使用BFS, 每次往外擴散 1 ~ 6 格, 最先碰到終點當下的 minPath 就是答案

這題其實在考察2個部分
1. 要怎麼活用BFS
2. 如何處理轉行交替二維陣列

我們可以把它拉直成一個一維陣列, 這個一維陣列下標從1 開始
, 我們建一個 n + 1長度的一維陣列, 0那格直接不要用就好, 如此就能用BFS遍歷它了


把問題縮小, 如果我們只看一個積木, 有什麼是我們可以重複做的?

思路:
即使眼前這格有梯子還是需要把所有可能都看過

慣例是:
每當往前走一格都需要嘗試把它的下6格入隊, 只有這樣才能保證結果都看過

有個問題要先解決, 踩過的格子還能再踩嗎??
一定是不能的, 因為沒有意義, 再踩的那一次一定不可能是最短路徑了

bfs逐步列出:
起始在1, 1的值是-1, [3~7, 15]都沒訪問過, 把[3~7, 15]全部入隊 <----------注意!!沒有2, 因為2那一格直接通往15
deque[15, 3, 4, 5, 6, 7]--->此時隊中全是step1可到的方格

接著把15取出, 15的值是-1, 把[16, 13, 18, 19, 20, 21]入隊 <----------注意!!沒有17, 因為17那一格直接通往13
deque[3, 4, 5, 6, 7 |　16, 13, 18, 19, 20, 21]--->此時隊中包含step1, step2可到的方格, 用 | 符號區分

接著把3取出, 3的值是-1, 4~7已訪問過, 僅把8, 9入隊
deque[4, 5, 6, 7 |　16, 13, 18, 19, 20, 21, 8, 9]--->此時隊中包含step1, step2可到的方格, 用 | 符號區分

接著把4取出, 4的值是-1, 5~9已訪問過, 把10入隊
deque[5, 6, 7 |　16, 13, 18, 19, 20, 21, 8, 9, 10]--->此時隊中包含step1, step2可到的方格, 用 | 符號區分

接著把5取出, 5的值是-1, 6~10已訪問過, 把11入隊
deque[6, 7 |　16, 13, 18, 19, 20, 21, 8, 9, 10, 11]--->此時隊中包含step1, step2可到的方格, 用 | 符號區分

接著把6取出, 6的值是-1, 7~11已訪問過, 把12入隊
deque[7 |　16, 13, 18, 19, 20, 21, 8, 9, 10, 11, 12]--->此時隊中包含step1, step2可到的方格, 用 | 符號區分

接著把7取出, 7的值是-1, 8~13已訪問過
deque[16, 13, 18, 19, 20, 21, 8, 9, 10, 11, 12]--->此時隊中全是step2可到的方格

接著把16取出, 16的值是-1, [13, 18~21]已訪問過, 把22入隊 <----------注意!!沒有17, 因為17那一格直接通往13
deque[13, 18, 19, 20, 21, 8, 9, 10, 11, 12 | 22]--->此時隊中包含step2, step3可到的方格

接著把13取出, 13的值是-1, [13, 18, 19]已訪問過, 把35, 15, 16入隊<----------注意!!沒有14, 因為14那一格直接通往35,　同理, 也不會有17
deque[18, 19, 20, 21, 8, 9, 10, 11, 12 | 22, 35, 15, 16]--->此時隊中包含step2, step3可到的方格

後面略過

看完上面的推倒, 就可以很清楚bfs要怎麼寫了
走到新節點:
	這個是最終節點嗎?
		是就返回minPath, 不是就繼續往下
	檢查有無梯子, 蛇
		有:
			梯子或蛇的終點加入deque中, 並且visited要改成true
		無:
			把當前格子座標加入deque中, 並且visited要改成true
*/
