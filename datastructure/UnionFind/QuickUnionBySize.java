package UnionFind;
public class QuickUnionBySize {
    int[] parent;
    int[] rank;
    QuickUnionBySize(int size){
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    int find(int x){
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
    void union(int x, int y){
        int parentX = find(x);
        int parentY = find(y);
        if (parentX != parentY){
            if (rank[parentX] > rank[parentY])
                parent[parentY] = parentX; // 為什麼不是 parent[y] = parentX
            else if (rank[parentY] > rank[parentX])
                parent[parentX] = parentY;
            else {
                parent[parentY] = parentX;
                rank[parentX]++;
            }
        }
    }
    boolean connected(int x, int y){
        return find(x) == find(y);
    }
}
/*

什麼是路徑壓縮??
find 路徑未壓縮, 每個人的老大都是自己的前一位
parent = [0, 0, 1, 2], count = 1

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

find 路徑壓縮優化, 每個人的老大都是最前面那一位
parent = [0, 0, 0, 0], count = 1
    1 (根節點)
  / | \
 2  3  4  <-- 所有節點都直接指向根節點了！

count 代表連通分量總數, 路徑壓縮不會影響連通分量總數, 但會影響當前圖的畫法, 也就是parent

第22行為什麼不是 parent[y] = parentX ??
核心概念：我們合併的是「集合（樹）」，而不是「單個節點」
    Union-Find 的底層是一棵棵的樹。
    1. x 的根節點是 parentX（代表 x 所在的整個幫派的老大）。
    2. y 的根節點是 parentY（代表 y 所在的整個幫派的老大）。

當我們執行 union(x, y) 時，我們的目標是把這兩個幫派合併成一個幫派。
最有效率且正確的方法是：讓其中一個幫派的老大，認另一個幫派的老大當老大。

也就是說：
1. 正確寫法：parent[parentY] = parentX; （y 的老大 parentY 指向 x 的老大 parentX）
2. 這樣一來，原本歸 parentY 管轄的所有小弟，在呼叫 find() 向上追溯時，都會順著 parentY -> parentX 找到新的總老大 parentX。整個幫派成功合併。
*/