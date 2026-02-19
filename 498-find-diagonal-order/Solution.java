public static int[] P0498_findDiagonalOrder(int[][] mat) {
    int m = mat.length;//行
    int n = mat[0].length;//列
    int[] DiagonalMat = new int[m * n];
    int x = 0 ,y = 0, j = 0;//宣告變數

    for (int i =0; i<m+n-1; i++){
        if (i%2 == 0){ //偶數,下--->上
            if(i < m){//為什麼是 i < m, 因為Pivot point發生在i = 3時
                x = i;
                y = 0;
            }
            else { //Pivot point發生時,走進else
                x = m-1;
                y = i-m+1;
            }

            while (y<n && x>=0){
                System.out.println(x + " "+ y);
                DiagonalMat[j] = mat[x][y];
                x--;
                y++;
                j++;
            }
        }
        else {//奇數,上--->下
            if (i < n){//為什麼是 i < n, 因為Pivot point發生在i = 4時
                x = 0;
                y = i;
            }

            else{//Pivot point發生時,走進else
                x = i-n+1;
                y = n-1;
            }
            while (x < m && y >= 0){
                System.out.println(x + " " + y);
                DiagonalMat[j] = mat[x][y];
                x++;
                y--;
                j++;
            }
        }
    }
    return DiagonalMat;
}

public static void main() {
    int[][] mat1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    System.out.println(Arrays.toString(P0498_findDiagonalOrder(mat1)));
}