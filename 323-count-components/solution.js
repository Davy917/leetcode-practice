const UnionFind= require('../datastructure/UnionFind/QuickUnion');
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var countComponents = function(n, edges) {
    const uf = new UnionFind(n)
    for (let i = 0; i < edges.length; i++)
        uf.union(edges[i][0], edges[i][1])

    return uf.count
};

if (require.main === module){
    const edges = [[0, 1], [1, 2], [3, 4]]
    console.log("Ans = ", countComponents(5, edges))
}
/*
UnionFind還是無法自己寫出來, 但已經能想到這題要怎麼解
無向圖, 每個edge中的元素, [0,1], [1, 2], [3, 4]
都可以看成 union(0,1),union(1,2), union(3,4)
*/