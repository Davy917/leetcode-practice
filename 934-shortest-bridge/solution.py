from collections import deque
from typing import List
class Solution:
    def __init__(self) -> None:
        self.directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        self.rows = 0
        self.cols = 0
    def shortestBridge(self, grid: List[List[int]]) -> int:
        #helper
        in_grid = lambda x, y: x >= 0 and y >= 0 and x < self.rows and y < self.cols
        def dfs(grid, i, j):
            if not in_grid(i, j) or grid[i][j] != 1:
                return
            grid[i][j] = 2
            dq.append([i, j]) #dq等一下bfs會用到
            for direction in self.directions:
                dfs(grid, i + direction[0], j + direction[1])
        def bfs(grid):
            curLen = 0
            while len(dq) > 0: #dq的成員是剛剛在 dfs 時加入的
                layer_size = len(dq)
                for _ in range(layer_size):
                    cur = dq.popleft()
                    cur_x = cur[0]
                    cur_y = cur[1]
                    for direction in self.directions:
                        new_x = cur_x + direction[0]
                        new_y = cur_y + direction[1]
                        if in_grid(new_x, new_y):
                            if grid[new_x][new_y] == 0:
                                dq.append([new_x, new_y])
                                grid[new_x][new_y] = 2
                            elif grid[new_x][new_y] == 1:
                                return curLen
                curLen += 1
            return 0 #理論上不會走到這裡
        self.rows = len(grid)
        self.cols = len(grid[0])
        dq = deque()
        for i in range(self.rows):
            for j in range(self.cols):
                if(grid[i][j] == 1):
                    dfs(grid, i, j) #dfs把兩座島區分出來
                    return bfs(grid) #bfs算最短路徑
        return 0 #理論上不會走到這裡
if __name__ == "__main__":
    grid = [[0,0,0,1],
            [1,0,0,0],
            [1,1,0,0],
            [1,1,1,0]]
    print("Ans = ", Solution().shortestBridge(grid))

"""
建議先看
1091-shortest-path-binary-matrix/solution.js

非自己想的, 建議再練習
[0,0,0,2]
[1,0,0,0]
[1,1,0,0]
[1,1,1,0]
我們把 grid 變成這樣, 就能清楚區分兩座島,
然後對上面這張圖做bfs, bfs天生適合求圖的最短路徑

被我忽略的小細節:
bfs在呼叫時不是把某一個 grid[i, j] == 2 的傳進bfs
而是用 dfs 時我們找到的所有 grid[i, j] == 2 的座標, 也就是dq
其含義就是所有值為 2 的座標都往前一步, 最先碰到 1 的就是最短路徑

bfs共會遇到三種情形
grid[i, j] == 0 直接把那格改成2並入隊, 代表等一下會往那一格走
grid[i, j] == 1 找到另一座島, 返回當前統計的步數
grid[i, j] == 2 不用理會
"""