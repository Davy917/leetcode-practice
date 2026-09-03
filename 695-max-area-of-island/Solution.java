/*
bfs 看 Go (使用二維座標)
dfs 看 java
 */
class Solution695 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int rows;
    private static int cols;
    static int count = 0;
    public static int maxAreaOfIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[][] color = new int [rows][cols];

        int maxArea = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (grid[i][j] == 1 && color[i][j] == 0){
                    count = 0;
                    int curArea = dfs(grid, color, i, j);
                    maxArea = Math.max(maxArea, curArea);
                }
        return maxArea;
    }
    public static int dfs(int[][] grid, int[][] color, int i, int j){
        count++;
        color[i][j] = 1;
        for(int[] direction: DIRECTIONS){
            int newX = i + direction[0];
            int newY = j + direction[1];
            if (inGrid(newX, newY) && grid[newX][newY] == 1 && color[newX][newY] == 0)
                dfs(grid, color, newX, newY);
        }
        return count;
    }
    public static boolean inGrid(int i, int j){
        return i >= 0 && j >= 0 && i < rows && j < cols;
    }
    static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println("Ans = " + maxAreaOfIsland(grid));
    }
}
/*
自己寫出來的
思路:
1. 走到陸地, 走進dfs
2. 將color[i][j]標為1
3. 該陸地左右上下有未被遍歷過的土地, 就往該土地走

另一個版本, count 做為參數傳遞到 dfs, 應該這樣設計:

public static int dfs(int[][] grid, int[][] color, int i, int j, int count){
    count++;
    color[i][j] = 1;
    for(int[] direction: DIRECTIONS){
        int newX = i + direction[0];
        int newY = j + direction[1];
        if (inGrid(newX, newY) && grid[newX][newY] == 1 && color[newX][newY] == 0)
            count = dfs(grid, color, newX, newY, count);  // ✅ 接收返回值
    }
    return count;
}
*/