const directions = [[0, -1], [0, 1], [-1, 0], [1, 0]]
var rows
var cols
/**
 * @param {character[][]} grid
 * @return {number}
 */
var numIslands = function(grid) {
    rows = grid.length
    cols = grid[0].length
    let color = Array(rows).fill(null).map(() => Array(cols).fill(0))

    let result = 0
    for (let i = 0; i < rows; i++)
        for (let j = 0; j < cols; j++)
            if (grid[i][j] === '1' && color[i][j] === 0){
                bfs(grid, color, i, j)
                result++
            }
    return result
}
var bfs = function (grid, color, i, j){
    color[i][j] = 1
    let queue = []
    queue.push([i, j])
    while (queue.length > 0){
        let layerSize = queue.length
        for (let k = 0; k < layerSize; k++) {
            let cur = queue.shift()
            let curX = cur[0]
            let curY = cur[1]
            for (let direction of directions){
                let newX = curX + direction[0]
                let newY = curY + direction[1]
                if (inGrid(newX, newY) && grid[newX][newY] === '1' && color[newX][newY] === 0){
                    queue.push([newX, newY])
                    color[newX][newY] = 1
                }
            }
        }
    }
}

const inGrid = (i, j) => i >= 0 && j >= 0 && i < rows && j < cols
if(require.main === module){
    let grid = [
        ['1','1','1','1','0'],
        ['1','1','0','1','0'],
        ['1','1','0','0','0'],
        ['0','0','0','0','0']
    ]
    console.log("Ans = ", numIslands(grid))
}
/*
自己寫的
相似題:
695-max-area-of-island/solution.go
 */