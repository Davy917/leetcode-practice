from typing import List
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def dfs(graph, color, cur, expect) -> bool:
            if color[cur] != 0:
                if color[cur] == expect:
                    return True
                else:
                    return False
            color[cur] = expect
            for next in graph[cur]:
                if not dfs(graph, color, next, 3 - expect):
                    return False
            return True
        n = len(graph)
        color = [0] * n
        for i in range(n):
            if(color[i] == 0):
                if not dfs(graph, color, i, 1):
                    return False
        return True
if __name__ == "__main__":
    graph = [[1,3],[0,2],[1,3],[0,2]]
    print("Ans = ", Solution().isBipartite(graph))
    
"""
210, 785, 802容易搞混
"""