/* 上, 下, 左, 右, 左上, 右上, 左下, 右下 */
var rows;
var cols;
const directions = [[-1, 0], [1, 0], [0, -1], [0 ,1], [-1, -1], [-1, 1], [1, -1], [1, 1]]

/**
 * @param {number[][]} grid
 * @return {number}
 */
var shortestPathBinaryMatrix = function(grid) {
    rows = grid.length
    cols = grid[0].length
    let ans = -1;
    let color = Array(rows).fill(null).map(() => Array(cols).fill(0))
    if (grid[0][0] === 0 && grid[rows-1][cols-1] === 0) //頭尾都是 0 才進 bfs
    {
        if (rows === 1 && cols === 1) //特殊情境 1 * 1矩陣, 直接回傳 1 就好
            return 1
        ans = bfs(grid, color, 0, 0, 1)
    }
    return ans !== -1 ? ans : -1
};
var bfs = function (grid, color, i, j, curLen) {
    let queue = []
    queue.push([i, j])
    while (queue.length > 0){
        curLen++
        let layerSize = queue.length
        for (let k = 0; k < layerSize; k++) //要多加這一層判斷, 否則底下的 testCase 會出錯
        {
            let cur = queue.shift()
            let curX = cur[0]
            let curY = cur[1]
            for (let direction of directions){
                let newX = curX + direction[0]
                let newY = curY + direction[1]
                if (inGrid(newX, newY) && grid[newX][newY] === 0 && color[newX][newY] === 0){
                    queue.push([newX, newY])
                    color[newX][newY] = 1
                    if (newX === rows - 1 && newY === cols - 1)
                        return curLen
                }
            }
        }
    }
    return -1
}
const inGrid = (i, j) => i >= 0 && j >= 0 && i < rows && j < cols
if(require.main === module){
    let grid = [[0,0,0],[1,1,0],[1,1,0]]
    console.log("Ans = ", shortestPathBinaryMatrix(grid))
}
/*
自己寫出來的
思路:
bfs要做什麼?
bfs需要往 8 個方向搜索, 只有當搜索到 0 才需要處理

重點問題:
要怎麼統計最短路徑, 才能確保 bfs 一定能夠返回最短路徑 ?
突破點:
因為 bfs 每次只會往外擴散一格, 每擴散一次就curLen++, 最先走到目的地, 當下curLen自然就會是最短路徑了

另外, 這句是很關鍵的判斷, 含意是要先確定該格數值為0, 並且沒被訪問過, 才往那一格走去
if (inGrid(newX, newY) && grid[newX][newY] === 0 && color[newX][newY] === 0)

有把這層防護寫好, 上面那個突破點就是水到渠成, 所以 bfs 拿來解最短路徑題型特別適合
*/