from collections import deque
from typing import List
class Solution:
    def __init__(self):
        self.directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        self.rows = 0
        self.cols = 0
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        self.rows = len(heights)
        self.cols = len(heights[0])

        can_pass_pacific = self.bfs(heights, 0, 0) #找出能流向 Pacific 者
        can_pass_atlantic = self.bfs(heights, self.rows - 1, self.cols - 1) #找出能流向 Atlantic 者
        can_pass_both = []
        for i in can_pass_pacific:
            if i in can_pass_atlantic:
                can_pass_both.append(i)
        return can_pass_both

    def bfs(self, heights, i, j) -> List[List[int]]:
        #先實現兩個helper
        def is_near_ocean(x, y) -> bool:
            if i == 0 and j == 0:
                return x == 0 or y == 0
            return x == self.rows - 1 or y == self.cols - 1 #用來判斷當前是否臨海

        def in_grid(x, y) -> bool:
            return x >= 0 and y >= 0 and x < self.rows and y < self.cols #用來判斷是否超界
        #這行以下才是主邏輯
        visited = []
        for _ in range(self.rows):
            row = [False] * self.cols
            visited.append(row) # visited用來判斷是否遍歷過

        result = [[i, j]] #水能流入大海者, 加入result
        dq = deque([[i, j]])
        visited[i][j] = True
        while len(dq) > 0:
            layer_size = len(dq)
            for _ in range(layer_size):
                cur = dq.popleft()
                cur_x = cur[0]
                cur_y = cur[1]
                for direction in self.directions:
                    new_x = cur_x + direction[0]
                    new_y = cur_y + direction[1]
                    if in_grid(new_x, new_y) and not visited[new_x][new_y]:
                        if is_near_ocean(new_x, new_y) or heights[cur_x][cur_y] <= heights[new_x][new_y]:
                            dq.append([new_x, new_y]) #入隊
                            visited[new_x][new_y] = True #更新visited
                            result.append([new_x, new_y]) #加入result
        return result
if __name__ == "__main__":
    heights = [[1, 2, 2, 3, 5], [3, 2, 3, 4, 4], [2, 4, 5, 3, 1], [6, 7, 1, 4, 5], [5, 1, 1, 2, 4]]
    print("Ans = ", Solution().pacificAtlantic(heights))

    """
    自己寫的
    介紹is_near_ocean:
    Pacific 的角度出發, 以下都算臨海
    [0, 0], [0, 1], [0, 2], [0, 3], [0, 4]
    [0, 0], [1, 0], [2, 0], [3, 0], [4, 0]
    可知
    x, y其中一個為0, 則該土地一定臨海
    
    Atlantic 的角度出發, 以下都算臨海
    [0, 4], [1, 4], [2, 4], [3, 4], [4, 4]
    [4, 0], [4, 1], [4, 2], [4, 3], [4, 4]
    可知
    x等於rows - 1, 或 y等於cols - 1, 則該座標一定臨海
    總結: 如果is_near_ocean 為　True 則該座標須入隊
    
    解釋 bfs 入隊邏輯
    if in_grid(new_x, new_y) and not visited[new_x][new_y]:
        if is_near_ocean(new_x, new_y) or heights[cur_x][cur_y] <= heights[new_x][new_y]:
    含意:
        如該座標在指定範圍內 -> 且沒被訪問過 -> 是否臨海? 是否大於前一座標? (達成一項就該入隊)
        注意上下兩個if 的順序不能顛倒, 第一個if的優先權大於第二個if
        
    主程式 pacificAtlantic:
    邏輯是分別找出兩者, 再取交集, 此交集就是答案
    can_pass_pacific 我們從 [0, 0]出發, 由前往後
    can_pass_atlantic 我們從[m-1, n-1]出發, 由後往前
    """