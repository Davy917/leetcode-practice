import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).parent.parent))
from typing import List
from datastructure import quick_union as union_find
class Solution:
    def findRedundantConnection(self, edges: List[List[int]]):
        n = len(edges)
        uf = union_find(n + 1) #題目是1base, + 1 才不會報錯, 0 會做為多餘的分量獨立存在, 但不會影響結果
        for i in range(n):
            if(uf.isConnected(edges[i][0], edges[i][1])):
                return edges[i]
            uf.union(edges[i][0], edges[i][1])

if __name__ == "__main__":
    edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
    print("Ans = ", Solution().findRedundantConnection(edges))