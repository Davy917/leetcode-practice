//官解
class QuickUnion{
    int[] root;
    private int count; //紀錄省份的數量
    QuickUnion(int size){
        root = new int[size];
        for (int i = 0; i < size; i++)
            root[i] = i;
        this.count = size; //初始count, 每個城市本身都是一個省
    }
    int find(int x){
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }
    void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY){
            root[rootY] = rootX;
            count--;
        }
    }
    boolean isConnected(int x, int y){
        return find(x) == find(y);
    }
    int getCount(){
        return count;
    }
}
class Solution547 {
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        QuickUnion qu = new QuickUnion(n);
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                if (isConnected[i][j] == 1)
                    qu.union(i, j);
        return qu.getCount();
    }
    public static void main(String[] args) {
        int[][] arr = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println("Ans = " + findCircleNum(arr));
    }
}
/*
城市  j=0   j=1   j=2
i=0 [ A     B     C ]
i=1 [ D     E     F ]
i=2 [ G     H     I ]

對角線不需要看, 元素（A, E, I）代表「城市自己與自己的連通性」。
左下三角（D, G, H）與右上三角（B, C, F）是完全鏡像對稱的，只需要看一邊。

像這樣的安排可以讓我們只遍歷右上三角
for (int i = 0; i < n; i++)
    for (int j = i+1; j < n; j++)

用並查集解這題的思路
初始化：有 N 個城市，一開始每個城市都是一個獨立的省份（集合數量 = N）。
遍歷矩陣：如果發現 isConnected[i][j] == 1（且 i != j）：
檢查 i 和 j 是否已經在同一個省份中（使用 find）。
如果不在，就將它們合併 union(i, j)。
關鍵點：每成功合併兩個原本不相連的集合，總省份數量就要減 1。
返回結果：最後剩下的集合數量，就是省份的總數。

Example:
初始root [0, 1, 2, 3]
Union   [0, 0, 2, 1]
城市      1, 2, 3, 4
索引      0, 1, 2, 3
*/