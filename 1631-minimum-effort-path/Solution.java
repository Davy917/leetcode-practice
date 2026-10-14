import java.util.PriorityQueue;

class Pair implements Comparable<Pair> {
    int x;
    int y;
    int dist;

    Pair(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(this.dist, other.dist);
    }
}

class Solution1631 {
    private int rows;
    private int cols;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean[][] visited;
    private int maxVal = -1;
    public int minimumEffortPath(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        visited = new boolean[rows][cols];
        return dijkstra(heights);
    }
    private int dijkstra(int[][] heights){
        var heap = new PriorityQueue<Pair>();
        heap.offer(new Pair(0, 0, 0));
        while (!heap.isEmpty()){
            Pair cur = heap.poll();
            if (visited[cur.x][cur.y])
                continue;
            visited[cur.x][cur.y] = true;
            maxVal = Math.max(maxVal, cur.dist);
            if (cur.x == rows - 1 && cur.y == cols - 1)
                return maxVal;
            for (int[] direction : DIRECTIONS){
                int newX = cur.x + direction[0];
                int newY = cur.y + direction[1];
                if (inGrid(newX, newY) && !visited[newX][newY]){
                    int newDist = Math.abs(heights[newX][newY] - heights[cur.x][cur.y]);
                    Pair newPair = new Pair(newX, newY, newDist);
                    heap.offer(newPair);
                }
            }
        }
        return maxVal; //理論上不會走到這
    }
    private boolean inGrid(int i, int j){
        return i >= 0 && j >= 0 && i < rows && j < cols;
    }
    static void main(String[] args) {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int[][] heights2 = {{10, 8}, {10, 8}, {1, 2}, {10, 3}, {1, 3}, {6, 3}, {5, 2}};
        Solution1631 sol = new Solution1631();
        System.out.println("Ans = " + sol.minimumEffortPath(heights2));
    }
}
/*
自己寫的
拿紙筆把 heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}} 畫出來
理解題意:
找到耗費體力最小的一條路徑--->不是找最短路徑喔!
返回左上到右下最小體力值--->繞路反而有可能獲得最小體力值
路徑耗費的體力值, 是路徑上相鄰格子, 高度差絕對值的最大值
--->想像成, 邊長 = ABS(終點 - 起點)
題目要找最短的邊長

int[][] heights是什麼?
有權有向圖

用什麼算法?
dijkstra

走過的路不再走, 底下二選一
走過的地方 heights[cur.x][cur.y] = 0;
或是多寫一個 boolean[][] visited

BFS, 檢查未被遍歷 ---> 入隊, 並標記成已遍歷
dijkstra, 出隊, 才標記為已遍歷

嘗試回答:
為什麼兩者會產生此差異?

visited 能被捨棄嗎?
可以, 把走過的地方改成 0 在這題是可行的

這還不是標準的dijkstra, 試試標準版dijkstra
 */