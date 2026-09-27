/*
dfs看java
bfs看go
 */
import java.util.Arrays;

class Solution733 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int rows;
    private static int cols;
    private static int oldColor;
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        rows = image.length;
        cols = image[0].length;
        oldColor = image[sr][sc];
        if (oldColor == color)
            return image;
        dfs(image, sr, sc, color);
        return image;
    }
    private static void dfs(int[][] image, int sr, int sc, int color){
        image[sr][sc] = color;
        for (int[] directions : DIRECTIONS){
            int SR = sr + directions[0];
            int SC = sc + directions[1];
            if (inGrid(SR, SC) && image[SR][SC] == oldColor)
                dfs(image, SR, SC, color);
        }
    }
    private static boolean inGrid(int sr, int sc){
        return sr >= 0 && sc >= 0 && sr < rows && sc < cols;
    }
    static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("Ans = " + Arrays.deepToString(floodFill(image, 1, 1, 2)));
    }
}
