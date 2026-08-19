/**
 * @param {number[][]} graph
 * @return {number[]}
 */
var eventualSafeNodes = function(graph) {
    const dfs = (graph, color, x) => {
        if (color[x] > 0)
            return color[x] === 2

        color[x] = 1
        for (const y of graph[x])
            if (!dfs(graph, color, y))
                return false

        color[x] = 2
        return true
    }

    const n = graph.length
    const color = new Array(n).fill(0)
    const ans = []
    for (let i = 0; i < n; i++)
        if (dfs(graph, color, i))
            ans.push(i)
    return ans
};

if (require.main === module){
    const graph = [[1,2],[2,3],[5],[0],[5],[],[]]
    // const graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
    console.log("Ans = ", eventualSafeNodes(graph))
}
/*
有向圖, 單節點可以自成一環
終端節點一定是安全節點，它們是安全節點的「基礎情況」。

白色:0 節點尚未被檢查
灰色:1 節點正在被檢查中
黑色:2 節點是一個安全節點

dfs 回傳當前節點是不是一個安全節點 true 或 false
在 eventualSafeNodes 調用 dfs 檢查每一個節點, 把安全節點放進 ans
最後 return ans

dfs在執行 graph = [[1,2],[2,3],[5],[0],[5],[],[]] 當中的[1,2]
[遞迴樹]
dfs(0)                          color[0]=1 (訪問中)
├─ for y=1: dfs(1)              color[1]=1 (訪問中)
│  ├─ for y=2: dfs(2)           color[2]=1 (訪問中)
│  │  └─ for y=5: dfs(5)        color[5]=1 (訪問中)
│  │     └─ graph[5]=[] 無出邊
│  │        → color[5]=2 (安全)  return true ✅
│  │     → color[2]=2 (安全)     return true ✅
│  ├─ for y=3: dfs(3)           color[3]=1 (訪問中)
│  │  └─ for y=0: dfs(0)
│  │     └─ color[0]==1 正在訪問中！
│  │        → return false ❌    ← 發現環！
│  │     → color[3]=1 (不安全)    return false ❌
│  └─ return false ❌
│     → color[1]=1 (不安全)       return false ❌
└─ return false ❌               ← 第一個鄰居就失敗，不再檢查 y=2
   → color[0]=1 (不安全)

此刻 color = [1,1,2,1,0,2,0]
*/