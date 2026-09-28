package main

import "fmt"

func canVisitAllRooms(rooms [][]int) bool {
	visited := make([]bool, len(rooms))
	var dfs func(num int)
	dfs = func(num int) {
		if visited[num] == true {
			return
		}
		visited[num] = true
		for _, key := range rooms[num] {
			dfs(key)
		}
	}
	dfs(0)
	fmt.Println(visited)
	for _, v := range visited {
		if v == false {
			return false
		}
	}
	return true
}

func main() {
	rooms := [][]int{{1, 3}, {3, 0, 1}, {2}, {0}}
	fmt.Println("Ans = ", canVisitAllRooms(rooms))
}

/*
我們可以看出rooms是一個鄰接表--->聯想到並查集, dfs

思路大概是:
我們嘗試走遍所有房間, 有走過的標為true, 如最後還有房間為false, 代表結果為 false
另外這題可能形成自環, 自環不能列入統計

dfs試試:
退出條件--->發現有環

看一下當前這組測資大概會怎麼走?
0--->1--->3--->0--->發現環, 回到rooms[1]
0--->1--->0--->發現環, 回到rooms[1]
0--->1--->1--->發現環, 回到rooms[0]
0--->3--->發現環, 回到rooms[0]
全部走完了, 檢查result發現2號房為false

看完上面的推演就可以開始設計dfs
代碼結構
dfs(房號)
	for 房裡的鑰匙 : 房號
		dfs(鑰匙)

要怎麼檢查是否成環?
用visited來記錄

另外討論:
它是圖論中的哪種圖? 用dfs or 並查集?
841-can-visit-all-rooms\dfs vs union_find.md
*/
