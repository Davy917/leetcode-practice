"""
標準版dijkstra看python版本
自己寫的dijkstra看java版本
"""
from dataclasses import dataclass
from typing import List
import heapq
@dataclass
class Pair:
    x: int
    y: int
    dist: int #從起點到該節點的路徑成本
    def __lt__(self, other):
        return self.dist < other.dist
class Solution:
    def __init__(self):
        self.rows = 0
        self.cols = 0
        self.dist = []
        self.heights = []
        self.directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        self.rows = len(heights)
        self.cols = len(heights[0])
        self.heights = heights
        self.dist = [[float('inf')] * self.cols for i in range(self.rows)]
        return self.dijkstra()
    def dijkstra(self) -> int:
        heap = [Pair(0, 0, 0)]
        self.dist[0][0] = 0
        while len(heap) > 0:
            cur = heapq.heappop(heap)
            print("cur = ", cur)
            if cur.dist > self.dist[cur.x][cur.y]:
                continue
            if cur.x == self.rows - 1 and cur.y == self.cols - 1:
                print(self.dist)
                return self.dist[self.rows - 1][self.cols - 1]
            for direction in self.directions:
                new_x = cur.x + direction[0]
                new_y = cur.y + direction[1]
                if self.in_grid(new_x, new_y):
                    new_dist = max(cur.dist, abs(self.heights[cur.x][cur.y] - self.heights[new_x][new_y]))
                    if new_dist < self.dist[new_x][new_y]:
                        self.dist[new_x][new_y] = new_dist
                        heapq.heappush(heap, Pair(new_x, new_y, new_dist))
                    print(heap)
    def in_grid(self, x, y) -> bool:
        return x >= 0 and y >= 0 and x < self.rows and y < self.cols
if __name__ == "__main__":
    heights = [[1, 2, 3], [3, 8, 4], [5, 3, 5]]
    heights2 = [[10, 8], [10, 8], [1, 2], [10, 3], [1, 3], [6, 3], [5, 2]]
    print("Ans = ", Solution().minimumEffortPath(heights2))


"""
標準版目前自己還寫不出來
dijkstra結構:

while heap還有元素
    cur = heap彈出最小元素
    if cur.dist > self.dist[cur.x][cur.y]:
        continue
    if cur.x == self.rows - 1 and cur.y == self.cols - 1:
        return self.dist[self.rows - 1][self.cols - 1]

    for 檢查cur的上下左右鄰居
        沒出界的才繼續檢查
            edge = abs(當前值 - 鄰居值) #就是所謂的邊長
            new_dist = 取大的(當前最小邊長, edge)
            new_dist比原來路徑更好的話
                dist[new_x][new_y] = new_dist
                建立new_pair並放入堆中
"""