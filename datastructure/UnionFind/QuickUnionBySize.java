package UnionFind;
public class QuickUnionBySize {
    int[] root;
    int[] rank;
    QuickUnionBySize(int size){
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }
    int find(int x){
        if (root[x] == x)
            return x;
        return root[x] = find(root[x]);
    }
    void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY){
            if (rank[rootX] > rank[rootY])
                root[rootY] = rootX; // 為什麼不是 root[y] = rootX
            else if (rank[rootY] > rank[rootX])
                root[rootX] = rootY;
            else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
    boolean connected(int x, int y){
        return find(x) == find(y);
    }
}
/*
find 路徑壓縮優化
1 (根節點)
 ^
 |
2
 ^
 |
3
 ^
 |
4


    1 (根節點)
  / | \
 2  3  4  <-- 所有節點都直接指向根節點了！

第22行為什麼不是 root[y] = rootX ??
核心概念：我們合併的是「集合（樹）」，而不是「單個節點」
    Union-Find 的底層是一棵棵的樹。
    1. x 的根節點是 rootX（代表 x 所在的整個幫派的老大）。
    2. y 的根節點是 rootY（代表 y 所在的整個幫派的老大）。

當我們執行 union(x, y) 時，我們的目標是把這兩個幫派合併成一個幫派。
最有效率且正確的方法是：讓其中一個幫派的老大，認另一個幫派的老大當老大。

也就是說：
1. 正確寫法：root[rootY] = rootX; （y 的老大 rootY 指向 x 的老大 rootX）
2. 這樣一來，原本歸 rootY 管轄的所有小弟，在呼叫 find() 向上追溯時，都會順著 rootY -> rootX 找到新的總老大 rootX。整個幫派成功合併。
*/