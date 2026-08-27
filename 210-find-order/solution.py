from typing import List
from collections import deque
class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        def dfs(graph, color, cur):
            if color[cur] != 0:
                return color[cur] == 2
            color[cur] = 1
            for x in graph[cur]:
                if not dfs(graph, color, x):
                    return False
            color[cur] = 2
            temp.appendleft(cur)
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
        temp = deque()
        for i in range (numCourses):
            if color[i] == 0:
                if not dfs(graph, color, i):
                    return []

        result = [0] * numCourses
        for i in range(len(temp)):
            result[i] = temp[i]
        return result

if __name__ == "__main__":
    # prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
    prerequisites = []
    print("Ans = ", Solution().findOrder(2, prerequisites))

"""
寫第二遍, 自己寫出來, 做了代碼優化

相似題:
207, 210, 785, 802
"""