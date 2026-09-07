/**
 * @param {number[][]} grid
 * @return {number}
 */
var closedIsland = function(grid) {
    const directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    var rows = grid.length
    var cols = grid[0].length
    const inGrid = (i, j) => i >= 0 && j >= 0 && i < rows && j < cols
    const isEdge = (i, j) => i === 0 || j === 0 || i === rows - 1 || j === cols - 1
    const bfs = (grid, i, j) => {
        let nearOcean = false
        let deque = [[i, j]]
        grid[i][j] = 1
        while (deque.length > 0){
            let layerSize = deque.length
            for (let k = 0; k < layerSize; k++) {
                let cur = deque.shift()
                let curX = cur[0]
                let curY = cur[1]
                for (let direction of directions){
                    let newX = curX + direction[0]
                    let newY = curY + direction[1]
                    if (inGrid(newX, newY) && grid[newX][newY] === 0){
                        if (nearOcean === false)
                            nearOcean = isEdge(newX, newY)
                        deque.push([newX, newY])
                        grid[newX][newY] = 1
                    }
                }
            }
        }
        if (!nearOcean)
            count++
    }
    var count = 0
    for (let i = 0; i < rows; i++)
        for (let j = 0; j < cols; j++)
            if (!isEdge(i, j) && grid[i][j] === 0)
                bfs(grid, i, j)
    return count
};
if (require.main === module){
    let grid = [[1,1,1,1,1,1,1,0],
        [1,0,0,0,0,1,1,0],
        [1,0,1,0,1,1,1,0],
        [1,0,0,0,0,1,0,1],
        [1,1,1,1,1,1,1,0]]
    console.log("Ans = ", closedIsland(grid))
}
/*
自己寫的, 直接拿相似題來改
相似題:
1020-num-enclaves/solution.py
*/