from typing import List
class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        def dfs(graph, color, cur, expect) -> bool:
            if color[cur] != 0:
                return color[cur] == expect
            color[cur] = expect
            for x in graph[cur]:
                if not dfs(graph, color, x, 3-expect):
                    return False
            return True
        graph = []
        color = [0] * (n+1)
        for _ in range(n+1):
            graph.append([])
        for i in range(len(dislikes)):
            pre = dislikes[i][0]
            cur = dislikes[i][1]
            graph[pre].append(cur)
            graph[cur].append(pre) #轉鄰接表的部分, 邏輯有變
        for i in range(1, n+1):
            if color[i] == 0:
                if not dfs(graph, color, i, 1):
                    return False
        return True
    
if __name__ == "__main__":
    prerequisites = [[1,2],[1,3],[2,3]]
    print("Ans = ", Solution().possibleBipartition(3, prerequisites))
"""
相似題:
207, 210, 785, 802

轉鄰接表的部分, 邏輯有變
因為dislikes是雙向關係, 你可以理解成無向圖, 或是雙向圖
概念上：無向圖
    「不喜歡」是對稱關係，A 和 B 不能同組，沒有方向性
    建圖時只需一條無向邊連接 A 和 B

實現上：等價於雙向的有向邊
在鄰接表中，你會同時加入 A → B 和 B → A
這只是無向圖的一種常見程式碼寫法，本質不變
"""