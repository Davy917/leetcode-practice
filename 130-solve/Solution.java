import java.util.*;

class Solution130 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int rows;
    static int cols;
    public static void solve(char[][] board) {
        rows = board.length;
        cols = board[0].length;
        System.out.printf("rows = %d, cols = %d\n", rows, cols);
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < cols; i++) // 上邊
            if (board[0][i] == 'O' && visited[0][i] == false)
                bfs(board, visited, 0, i);
        for (int i = 0; i < cols; i++) //下邊
            if (board[rows - 1][i] == 'O' && visited[rows - 1][i] == false)
                bfs(board, visited, rows - 1, i);
        for (int i = 0; i < rows; i++) //左邊
            if (board[i][0] == 'O' && visited[i][0] == false)
                bfs(board, visited, i, 0);
        for (int i = 0; i < rows; i++) //右邊
            if (board[i][cols - 1] == 'O' && visited[i][cols - 1] == false)
                bfs(board, visited, i, cols - 1);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (visited[i][j] == false)
                    board[i][j] = 'X';
    }
    public static void bfs(char[][] board, boolean[][] visited, int x, int y){
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{x, y});
        visited[x][y] = true;
        while (deque.size() > 0){
            int layerSize = deque.size();
            for (int i = 0; i < layerSize; i++) {
                int[] cur = deque.pollFirst();
                int curX = cur[0];
                int curY = cur[1];
                for (int[] direction : DIRECTIONS){
                    int newX = curX + direction[0];
                    int newY = curY + direction[1];
                    if (inGrid(newX, newY) && board[newX][newY] == 'O' && visited[newX][newY] == false){
                        deque.addLast(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
    }
    public static boolean inGrid(int x, int y){ return x >= 0 && y >= 0 && x < rows && y < cols; }
    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},
                        {'X','O','O','X'},
                        {'X','X','O','X'},
                        {'X','O','X','X'}};

        char[][] board2 = {{'X','X','X','X','O'},
                        {'X','O','O','X','X'},
                        {'O','X','X','X','X'}};
        solve(board);
        System.out.println("Ans = " + Arrays.deepToString(board));
    }
}
/*
自己寫的
只要確定, 哪些區域一定沒有被包圍, 剩下的區域全部填'X'
一定沒有被包圍的區域代表它是跟邊相連的

        上邊
        x x x x
        x o o x  右
        x x o x  邊
        x o x x
        下邊

上邊: [0, 0], [0, 1], [0, 2], [0, 3]
下邊: [3, 0], [3, 1], [3, 2], [3, 3]
左邊: [0, 0], [1, 0], [2, 0], [3, 0]
右邊: [0, 3], [1, 3], [2, 3], [3, 3]

對四個邊所有符合條件的座標都做 bfs, 完成後
visited 中的true就是那些沒被包圍的區域
visited 中 false 的座標全部填入'X'
 */