from collections import deque
class Solution:
    def __init__(self):
        self.directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    def updateMatrix(self, mat: list[list[int]]) -> list[list[int]]:
        in_grid = lambda x, y: x >= 0 and y >= 0 and x < rows and y < cols
        def bfs():
            while len(dq) > 0:
                layer_size = len(dq)
                for _ in range(layer_size):
                    cur = dq.popleft()
                    cur_x = cur[0]
                    cur_y = cur[1]
                    for direction in self.directions:
                        new_x = cur_x + direction[0]
                        new_y = cur_y + direction[1]
                        if in_grid(new_x, new_y) and mat[new_x][new_y] == 1:
                            dq.append([new_x, new_y])
                            mat[new_x][new_y] = 0
                            result[new_x][new_y] = result[cur_x][cur_y] + 1
        rows = len(mat)
        cols = len(mat[0])
        result = [[0] * cols for i in range(rows)] # 列表生成式
        dq = deque()
        for i in range(rows):
            for j in range(cols):
                if mat[i][j] == 0:
                    dq.append([i, j])
        bfs()
        return result

if __name__ == "__main__":
    mat = [[0,0,0],[0,1,0],[1,1,1]]
    print("Ans = ", Solution().updateMatrix(mat))


"""
列表生成式:
LanguagePractice\PythonPractice\列表生成式.py
"""