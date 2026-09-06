from collections import deque
from typing import List
class Solution:
    def __init__(self) -> None:
        self.directions = [[-1,0], [1,0], [0,-1],[0,1], [-1,-1], [-1,1], [1,-1], [1,1]]
        self.rows = 0
        self.cols = 0
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        in_grid = lambda i, j : i >= 0 and j >= 0 and i < self.rows and j < self.cols
        def bfs(board: List[List[str]], visited: List[List[bool]], i, j):
            if board[i][j] == 'E':
                dq = deque([[i, j]]) #初始化, 為等下bfs做準備
            elif board[i][j] == 'M':
                board[i][j] = 'X'
                return
            else: #踩到數字 或 'B'
                return
            while len(dq) > 0:
                layer_size = len(dq)
                for i in range(layer_size):
                    mines_count = 0 #有偵測到炸彈就累加
                    temp = [] #未確定安全之前, 只能先裝在temp
                    cur = dq.popleft()
                    cur_x = cur[0]
                    cur_y = cur[1]
                    for direction in self.directions: #此for迴圈僅用來檢查鄰近是否有炸彈
                        new_x = cur_x + direction[0]
                        new_y = cur_y + direction[1]
                        if in_grid(new_x, new_y) and board[new_x][new_y] == 'E' and not visited[new_x][new_y]:
                            temp.append([new_x, new_y])
                        if in_grid(new_x, new_y) and board[new_x][new_y] == 'M':
                            mines_count += 1
                    if mines_count == 0: #安全, 鄰近座標無炸彈
                        board[cur_x][cur_y] = 'B'
                        for toAdd in temp:
                            dq.append(toAdd)
                            visited[toAdd[0]][toAdd[1]] = True
                    else:                 #不安全
                        board[cur_x][cur_y] = str(mines_count)
            return
        self.rows = len(board)
        self.cols = len(board[0])
        visited = []
        for _ in range(self.rows):
            visited.append([False] * self.cols)
        bfs(board, visited, click[0], click[1])
        return board
if __name__ == '__main__':
    click = [3,0]
    board = [['E','E','E','E','E'],
            ['E','E','M','E','E'],
            ['E','E','E','E','E'],
            ['E','E','E','E','E']]
    Solution().updateBoard(board, click)
    print("Ans = ", board)

"""
自己寫出來的
先把遊戲規則定義清楚再碰代碼, 如果不清楚要怎麼下手
先把範圍縮小到一個格子, 想著這格要做什麼動作? 會遇到什麼事情? 接下來的每一格都一樣

踩地雷設計了很多規則
簡化後, 可以發現其實只有踩到 'E' 或 'M' 需要動作, 其它都無須理會

被忽略的細節:
(1)如果發現當前這格的鄰居有炸彈
    除了標記數值外, 也應該停止往前, 也就是停止入隊
(2)如果確定當前節點是安全的
    除了入隊之外, 一定要標記為已訪問
bfs需要一直重複做哪些事?
先檢查當前所有鄰居:
    沒有炸彈, 才能標記為'B', 並把未被訪問的入隊, 入隊之後要馬上標記為已訪問
    有炸彈, 必須標記為數字, 且鄰居都不能入隊
"""