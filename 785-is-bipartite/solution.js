/**
 * @param {number[][]} graph
 * @return {boolean}
 */
var isBipartite = function(graph) {
    const dfs = (graph, color, cur, expect) => {
        if(color[cur] !== 0)
            return color[cur] === expect

        color[cur] = expect
        for(let next of graph[cur])
            if(!dfs(graph, color, next, 3 - expect))
                return false
        return true
    }
    const n = graph.length
    let color = new Array(n).fill(0)
    for(let i = 0; i < n; i++) //加一個迴圈，確保每個未染色的節點都啟動一次 DFS
        if(color[i] === 0)
            if(!dfs(graph, color, i, 1))
                return false
    return true
}
if (require.main === module){
    const graph = [[1,3],[0,2],[1,3],[0,2]]
    console.log("Ans = ", isBipartite(graph))
}
/*
二分圖定義請直接看影片:
https://www.youtube.com/watch?v=670Gn4e89B8

染色法建議先看
802-eventual-safe-nodes/solution.js

當前測資 [[1,3],[0,2],[1,3],[0,2]]
[遞迴樹]
dfs(0, 1)                                    color = [0,0,0,0]
│ color[0] = 1                               color = [1,0,0,0]
│
├─→ dfs(1, 2)                                ← 節點 0 的鄰居 1
│   │ color[1] = 2                           color = [1,2,0,0]
│   │
│   ├─→ dfs(0, 1)                            ← 節點 1 的鄰居 0
│   │   └─ color[0]=1 === expect(1) → true ✅  (已染色，直接比對)
│   │
│   └─→ dfs(2, 1)                            ← 節點 1 的鄰居 2
│       │ color[2] = 1                       color = [1,2,1,0]
│       │
│       ├─→ dfs(1, 2)                        ← 節點 2 的鄰居 1
│       │   └─ color[1]=2 === expect(2) → true ✅
│       │
│       └─→ dfs(3, 2)                        ← 節點 2 的鄰居 3
│           │ color[3] = 2                   color = [1,2,1,2]
│           │
│           ├─→ dfs(0, 1)                    ← 節點 3 的鄰居 0
│           │   └─ color[0]=1 === expect(1) → true ✅
│           │
│           └─→ dfs(2, 1)                    ← 節點 3 的鄰居 2
│               └─ color[2]=1 === expect(1) → true ✅
│           │
│           └─ return true ✅
│       │
│       └─ return true ✅
│   │
│   └─ return true ✅
│
├─→ dfs(3, 2)                                ← 節點 0 的鄰居 3
│   └─ color[3]=2 === expect(2) → true ✅     (已在 dfs(2,1) 中染色)
│
└─ return true ✅
*/