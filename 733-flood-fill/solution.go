package main

import (
	"fmt"
)

var diretions = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
var rows = 0
var cols = 0

func bfs(image [][]int, sr int, sc int, color int, rows int, cols int) [][]int {
	target := image[sr][sc]
	if color == target {
		return image
	}
	deque := [][]int{{sr, sc}}
	image[sr][sc] = color
	for len(deque) > 0 {
		layerSize := len(deque)
		for i := 0; i < layerSize; i++ {
			cur := deque[0]
			deque = deque[1:]
			curX := cur[0]
			curY := cur[1]
			for _, diretion := range diretions {
				newX := curX + diretion[0]
				newY := curY + diretion[1]
				if inGrid(newX, newY, rows, cols) && image[newX][newY] == target {
					toAdd := []int{newX, newY}
					deque = append(deque, toAdd)
					image[newX][newY] = color
				}
			}
		}
	}
	return image
}
func floodFill(image [][]int, sr int, sc int, color int) [][]int {
	rows = len(image)
	cols = len(image[0])
	return bfs(image, sr, sc, color, rows, cols)
}

func inGrid(sr int, sc int, rows int, cols int) bool {
	return sr >= 0 && sc >= 0 && sr < rows && sc < cols
}
func main() {
	image := [][]int{{0, 0, 0}, {0, 0, 0}}
	fmt.Println("Ans = ", floodFill(image, 0, 0, 0))
}

/*
自己寫的
相似題:
1034-color-border\solution.js

上面的 testcase 中 target == color, 這種基本上是沒有意義的, 但是又會造成無窮迴圈
所以這種情境下可以直接return image, 雖然這不是最嚴謹的寫法

如果要追求嚴謹應該要寫一個 visited 記錄所有座標的遍歷狀況,等所有座標的遍歷過確定image沒有變, 再返回image

詢問Leet如何簡化:
你目前 bfs 的參數有 6 個，確實偏多。Go 裡面常見的簡化方式有兩種：

方法一：閉包（Closure）— 最推薦

把 bfs 定義為 floodFill 內部的匿名函式，直接捕獲外層變數：

func floodFill(image [][]int, sr int, sc int, color int) [][]int {
    rows := len(image)
    cols := len(image[0])
    target := image[sr][sc]
    if color == target {
        return image
    }

    var bfs func(int, int)  // 只需要傳入座標
    bfs = func(sr int, sc int) {
        // 可以直接使用 image, color, target, rows, cols
        // ...
    }

    bfs(sr, sc)
    return image
}


這樣 bfs 只需要接收 (sr, sc) 兩個參數，其餘都從外層作用域讀取。

方法二：結構體（Struct）

把共用狀態封裝成 struct，bfs 變成方法：

type filler struct {
    image  [][]int
    color  int
    target int
    rows   int
    cols   int
}

func (f *filler) bfs(sr, sc int) {
    // 透過 f.image, f.color 等存取
}


## 兩者比較

| | 閉包 | 結構體 |
|---|---|---|
| 程式碼量 | 少 | 較多 |
| 適合場景 | 單一函式內使用 | 多個函式共享狀態 |
| 可讀性 | 簡潔 | 結構清晰 |

對於這題來說，閉包最合适，因為狀態只在 floodFill 內部使用，不需要額外定義型別。

你可以試試用閉包重構看看，只把 (sr, sc) 作為參數傳入，其他變數都從外層捕獲。
*/
