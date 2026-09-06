from collections import deque
from typing import List
class Solution:
    def __init__(self) -> None:
        self.directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        self.rows = 0
        self.cols = 0
    def numEnclaves(self, grid: List[List[int]]) -> int:
        self.rows = len(grid)
        self.cols = len(grid[0])
        #helper
        is_edge = lambda i, j: i == 0 or j == 0 or i == self.rows - 1 or j == self.cols - 1
        in_grid = lambda i, j: i >= 0 and j >= 0 and i < self.rows and j < self.cols
        def bfs(grid, i, j):
            nonlocal count
            grid[i][j] = 0
            dq = deque([[i, j]])
            near_ocean = False
            temp = 1
            while len(dq) > 0:
                layer_size = len(dq)
                for i in range(layer_size):
                    cur = dq.popleft()
                    cur_x = cur[0]
                    cur_y = cur[1]
                    for direction in self.directions:
                        new_x = cur_x + direction[0]
                        new_y = cur_y + direction[1]
                        if in_grid(new_x, new_y) and grid[new_x][new_y] == 1 :
                            near_ocean = is_edge(new_x, new_y) # 如果該座標在邊上, near_ocean就會是True
                            dq.append([new_x, new_y])
                            grid[new_x][new_y] = 0 #走過的地方改0, 等下就不會再走到
                            temp += 1
            if not near_ocean:
                count += temp # 確定是飛地了才能把數量加上去
        count = 0
        for i in range(self.rows):
            for j in range(self.cols):
                if in_grid(i, j) and grid[i][j] == 1 and not is_edge(i, j):
                    bfs(grid, i, j)
        return count
if __name__ == "__main__":
    grid = [[0,0,0,1,1,1,0,1,0,0],[1,1,0,0,0,1,0,1,1,1],[0,0,0,1,1,1,0,1,0,0],[0,1,1,0,0,0,1,0,1,0],[0,1,1,1,1,1,0,0,1,0],[0,0,1,0,1,1,1,1,0,1],[0,1,1,0,0,0,1,1,1,1],[0,0,1,0,0,1,0,1,0,1],[1,0,1,0,1,1,0,0,0,0],[0,0,0,0,1,1,0,0,0,1]]
    print("Ans = ", Solution().numEnclaves(grid))
    """
    自己寫的
    什麼情境要做bfs?
    grid[i][j]是不在邊上的土地 -> 才值得做bfs

    進入bfs中:
    走過的土地, 一定不需要再走一次, 所以走過的地方就把值改為 0
    如果已經發現一塊地它不是飛地, 我們是不能提早返回的, 需要把那塊土地的所有值都改成 0, 避免等下重複走到

    題目希望我們回傳飛地單元格的數量
    因為我們對每個值為 1 的座標都做了is_edge判斷
    所以根據 near_ocean 就可以知道當前土地是不是飛地
    """