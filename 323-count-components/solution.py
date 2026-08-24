from typing import List
from datastructure.UnionFind.QuickUnion import quick_union as union_find
class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        uf = union_find(n)
        for i in range(len(edges)):
            uf.union(edges[i][0], edges[i][1])
        return uf.count