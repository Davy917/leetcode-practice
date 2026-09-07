/**
 * @param {number[][]} grid
 * @param {number} row
 * @param {number} col
 * @param {number} color
 * @return {number[][]}
 */
var colorBorder = function(grid, row, col, color) {
    const directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    var rows = grid.length
    var cols = grid[0].length
    //helper
    const inGrid = (i, j) => i >= 0 && j >= 0 && i < rows && j < cols
    const isEdge = (curX, curY) => {
        for (const direction of directions) {
            let newX = curX + direction[0]
            let newY = curY + direction[1]
            if (inGrid(newX, newY) && !visited[newX][newY] && grid[curX][curY] !== grid[newX][newY])
                return true
        }
        return curX === 0 || curY === 0 || curX === rows - 1 || curY === cols - 1
    }
    const bfs = (row, col) => {
        const target = grid[row][col]
        let deque = [[row, col]]
        visited[row][col] = true
        if (isEdge(row, col)){ grid[row][col] = color }
        while (deque.length > 0){
            let layerSize = deque.length
            for (let i = 0; i < layerSize; i++) {
                let cur = deque.shift()
                let curX = cur[0]
                let curY = cur[1]
                for (const direction of directions) {
                    let newX = curX + direction[0]
                    let newY = curY + direction[1]
                    if (inGrid(newX, newY) && !visited[newX][newY] && grid[newX][newY] === target){
                        deque.push([newX, newY])
                        visited[newX][newY] = true
                        if (isEdge(newX, newY))
                            grid[newX][newY] = color
                    }
                }
            }
        }
    }

    let visited = Array(rows).fill(null).map(() => Array(cols).fill(false))
    bfs(row, col)
    return grid
};
if (require.main === module){
    let grid = [[1,2,2],[2,3,2]]
    console.log("Ans = ", colorBorder(grid, 0, 1, 3))
}
/*
相似題:
733比較簡單建議先看
733-flood-fill\solution.go

自己寫的
釐清題意:
请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色。

示例:
grid = [[2, 1, 2, 2],
        [2, 2, 3, 2]]
row = 0, col = 0, color = 3

上面這組測資的結果是這樣
[3, 1, 2, 2]
[3, 3, 3, 2]

根據題意我們只找 grid[0][0] 跟它連在一起的座標的邊來進行上色

題目中關於邊的定義
(1)是在grid的四條邊上
(2)與其它連通分量接壤的地方也是邊

所以 isEdge 的寫法需要包含上述兩項, 因為多了(2)的關係, isEdge的寫法會相對複雜
如何處理(2) ??
分成三步:
1. grid[newX][newY] 必須在界內這是基本, 用inGrid判斷
2. 再來是這個 grid[newX][newY] 必須是沒有被訪問過的, 因為一旦訪問過, 就有可能被41行改值
    那就會導致 isEdge 判定結果失真, 這部份我們用visited來處理, 這是最容易忽略的

3. 再來就是如果 cur 跟 new 的值不一致, 代表這個 cur 一定在邊上, 這是最容易想到的

其實isEdge 中的 (2) 的邏輯就是把一部分 bfs 的入隊邏輯再拿出來用而已, 所以原理是相通的
不懂的話先把bfs看懂, isEdge先理解成一個可以判定邊界的API, 等最後再來看 isEdge 就會明白了
*/