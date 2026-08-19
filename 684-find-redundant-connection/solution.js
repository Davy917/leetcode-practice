const UnionFind= require('../datastructure/UnionFind/QuickUnion');

/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var findRedundantConnection = function(edges) {
    let n = edges.length;
    let uf = new UnionFind(n + 1) //為什麼要+1
    for (let i = 0; i < n; i++) {
        if (uf.isConnected(edges[i][0], edges[i][1]) === false)
            uf.union(edges[i][0], edges[i][1])
        else
            return edges[i]
    }
};
if(require.main === module){
    // const edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
    const edges = [[1,2], [2,3], [3,4], [4,5], [1,5]]
    console.log("Ans = ", findRedundantConnection(edges))
}
/*
AI提示
想像一開始圖中沒有任何邊，然後我們按照 edges 的順序，把邊一條一條加進去。如果在加入某條邊 [u, v] 時，發現 u 和 v 已經連通了，這代表什麼呢？

看了上面提示才寫出來的
思路:
寫一個並查集, 這裡直接調用 datastructure/UnionFind/QuickUnion.js
檢查兩點是否相連:
    沒有相連就連上
    已經相連了就代表, 當前edges[i]再連上去一定會形成環--->不能連, 要返回 edges[i]

[FAQ]
第9行如果不+1 在某些情境下會有邏輯上的錯誤, 例如:[[1,2], [2,3], [3,4], [4,5], [1,5]]
走到[4,5]的時候, parent[0,1,1,1,1] 會出界, 因為parent[5]不存在, 但實際不會拋錯誤, 為什麼?

逐步追蹤 datastructure/UnionFind/QuickUnion.js

parent[5] === undefined
undefined === 5 → false
→ 呼叫 find(undefined)
    parent[undefined] === undefined
    undefined === undefined → true！
    → return undefined
→ parent[5] = undefined

所以 union(4, 5) 時：
parentX = find(4) = 1
parentY = find(5) = undefined
parent[undefined] = 1   ← 這裡是關鍵！

JS 陣列本質是物件，所以 parent[undefined] 等同於 parent["undefined"]，會在陣列上設一個 string key 的屬性。

接著走到 [1,5] 時 find(5) 再次執行：
parent[5] === undefined
undefined === 5 → false
→ 呼叫 find(undefined)
    parent["undefined"] === 1  ← 上一步存進去的！
    1 === undefined → false
    → 呼叫 find(1) → return 1
    → parent["undefined"] = 1, return 1
→ parent[5] = 1, return 1

find(1) = 1
find(5) = 1  → 相連！return [1,5] ✓

結論：不是邏輯正確，而是 JS 的陣列是物件，parent[undefined] 意外當成了節點 5 的中繼站，
讓整個 find chain 兜了一圈還是找到正確的 root。這是純粹的 JS 特性造成的「意外正確」。

parent在這過程中實際上長這樣
parent = [0, 1, 1, 1, 1]
parent["undefined"] = 1   // 額外掛在物件上

出界是真實發生的，只是 JS 沒有拋錯，而是透過 parent["undefined"] 這個意外行為歪打正著。

所以這份邏輯如果用 Java 或 Python 寫，
同樣的 testcase [[1,2],[2,3],[3,4],[4,5],[1,5]] 走到 find(5) 就會直接爆錯，根本跑不完。

JS 的陣列越界不拋錯這個特性，反而把這個 bug 掩蓋掉了。

#看不懂就直接從頭手推一遍就會明白了
*/