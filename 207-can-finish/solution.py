from typing import List
class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        def dfs(graph, color, cur) -> bool:
            if color[cur] != 0:
                return color[cur] == 2
            color[cur] = 1
            for x in graph[cur]:
                if not dfs(graph, color, x):
                    return False
            color[cur] = 2
            return True

        graph = []
        n = len(prerequisites)
        for _ in range(numCourses):
            graph.append([])
        for i in range(n):
            pre = prerequisites[i][1]
            cur = prerequisites[i][0]
            graph[pre].append(cur)

        color = [0] * numCourses
        for i in range (numCourses):
            if color[i] == 0:
                if not dfs(graph, color, i):
                    return False
        return True
if __name__ == "__main__":
    prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
    print("Ans = ", Solution().canFinish(4, prerequisites))

"""
相似題:
207, 210, 785, 802
"""