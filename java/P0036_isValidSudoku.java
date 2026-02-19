import java.util.*;

class Solution036 {
    //這三行是P0036_isValidSudokuWithMap方法的HashMap
    HashMap<String, Set<Character>> rowMap = new HashMap<>();
    HashMap<String, Set<Character>> columnMap = new HashMap<>();
    HashMap<String, Set<Character>> gridMap = new HashMap<>();

    public boolean P0036_isValidSudoku(char[][] board) {
        Set<Character> column = new HashSet<>();
        Set<Character> row = new HashSet<>();
        Set<Character> matrix = new HashSet<>();
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int r = boxRow * 3 + i;
                        int c = boxCol * 3 + j;
                        // 使用 board[r][c]
                        System.out.println("("+r+","+c+") -> " + board[r][c]);
                        if (matrix.contains(board[r][c]) && board[r][c] != '.'){return false;}
                        matrix.add(board[r][c]);
                        System.out.println("matrix = " + matrix);
                    }
                }
                matrix.clear();
            }
        }

        for (int i=0; i<9; i++){
            column.clear();
            row.clear();
            for (int j=0; j<9; j++){
                if (column.contains(board[i][j]) && board[i][j] != '.'){return false;}
                if (row.contains(board[j][i]) && board[j][i] != '.'){return false;}
                column.add(board[i][j]);
                row.add(board[j][i]);
                }
            }
        return true;
    }

    public boolean P0036_isValidSudokuWithMap(char[][] board){
        for (int row = 0; row<9; row++){
            for (int column = 0; column<9; column++){
                Character c = board[row][column];
                if (c == '.'){continue;}
                if (!checkRow(row, c)){return false;}
                if (!checkColumn(column, c)){return false;}
                if (!checkGrid(row, column, c)){return false;}
            }
        }
        return true;
    }

    boolean checkRow(int row, Character c){
        String key = String.valueOf(row);//設計鍵--->呼叫靜態方法把整數轉成字串（型別轉換／格式化）
        Set<Character> set = rowMap.getOrDefault(key, new HashSet<>());
        if (!set.add(c)){return false;}//如果集合已經包含 c（加入失敗），則執行大括號內的程式碼，也可以寫成set.add(c) == false
        rowMap.put(key, set);
        System.out.println("rowMap = " + rowMap);
        return true;
    }

    boolean checkColumn(int column, Character c){
        String key = String.valueOf(column);
        Set<Character> set = columnMap.getOrDefault(key, new HashSet<>());
        if (!set.add(c)){return false;}
        columnMap.put(key, set);
        System.out.println("columnMap = " + columnMap);
        return true;
    }

    boolean checkGrid(int row, int column, Character c){
        //System.out.println("gridMap = " + gridMap);
        String key = (row/3) + "-" + (column/3);
        Set<Character> set = gridMap.getOrDefault(key, new HashSet<>());
        if (!set.add(c)){return false;}
        gridMap.put(key, set);
        //System.out.println("gridMap = " + gridMap);
        return true;
    }

    static void main(String[] args) {
        char[][] sodoku = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        //System.out.println(new Solution036().P0036_isValidSudoku(sodoku));
        System.out.println(new Solution036().P0036_isValidSudokuWithMap(sodoku));
    }
}

/*
x y   x y   x y
0 0   0 3   0 6
0 1   0 4   0 7
0 2   0 5   0 8
1 0   1 3   1 6
1 1   1 4   1 7
1 2   1 5   1 8
2 0   2 3   2 6
2 1   2 4   2 7
2 2   2 5   2 8

3 0   3 3   3 6
3 1   3 4   3 7
3 2   3 5   3 8
4 0   4 3   4 6
4 1   4 4   4 7
4 2   4 5   4 8
5 0   5 3   5 6
5 1   5 4   5 7
5 2   5 5   5 8
 */