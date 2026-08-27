from typing import List
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        result = []
        n = len(graph)
        color = [0] * n
        for i in range(n):
            if color[i] == 0:
                self.dfs(graph, color, i, result)
        return sorted(result)
    
    def dfs(self, graph, color, cur, result) -> bool:
        if color[cur] != 0:
            return color[cur] == 2
        color[cur] = 1
        for next in graph[cur]:
            if not self.dfs(graph, color, next, result):
                return False
        color[cur] = 2
        result.append(cur)
        return True
if __name__ == "__main__":
    graph = [[1,2],[2,3],[5],[0],[5],[],[]]
    print("Ans = ", Solution().eventualSafeNodes(graph))
    
"""
相似題:
207, 210, 785, 802
"""