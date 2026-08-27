from typing import List
class Solution:
    def minimumSemesters(self, n: int, relations: List[List[int]]) -> int:
        def dfs(graph, color, cur) -> bool:
            if color[cur] != 0:
                return color[cur] == 2
            color[cur] = 1
            depth[cur] = 1  # 初始化：葉節點為 1
            for x in graph[cur]:
                if not dfs(graph, color, x):
                    return False
                depth[cur] = max(depth[cur], 1 + depth[x])  # 每個子節點都拿來比較
            color[cur] = 2
            return True

        graph = []
        for _ in range(n + 1):
            graph.append([])
        for i in range(0, len(relations)):
            pre = relations[i][0]
            x = relations[i][1]
            graph[pre].append(x)

        color = [0] * (n+1)
        depth = [0] * (n+1)
        for i in range(1, n + 1):
            if color[i] == 0:
                if not dfs(graph, color, i):
                    return -1
        result = max(depth)
        return result

if __name__ == "__main__":
    relations = [[1,2],[3,4],[4,5],[5,2]]
    print("Ans = ", Solution().minimumSemesters(5, relations))
"""
n = 5
relations = [[1,2],[3,4],[4,5],[5,2]]
graph = [[], [2], [], [4], [5], [2]]
最終:
color = [0, 2, 2, 2, 2, 2]
depth = [0, 2, 1, 4, 3, 2]

depth的定義:
depth[i] 代表「從節點 i 出發的最長路徑長度」：
                  4  depth = 4
                ↙   
              3      depth = 3
            ↙
   1      5          depth = 2
    ↘   ↙
      2              depth = 1

心得:
這題透過畫圖找規律, 很快就發現, 學完所有課程的最少學期數, 其實就是整張圖的最大深度
但是找最大深度卡了非常久, 問了Leet才知道要寫一個depth[]把每一層的深度都存進去
但是還是寫不出來, 最後是直接抄 Leet 給的作法才寫出來, 關於depth的定義如上圖所示, 詳細解題過程直接看與Leet的對話
"""