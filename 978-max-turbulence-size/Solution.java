/*
自己寫的, 非官方解法
 */
class Solution978 {
    public static int maxTurbulenceSize(int[] arr) {
        int maxSize = 1;
        int biggerFlag = 0, smallerFlag = 0;
        int l = 0, r = 1;
        while (r < arr.length){
            if (arr[r-1] > arr[r]){
                biggerFlag++;
                smallerFlag = 0;
            }
            else if (arr[r-1] < arr[r]){
                smallerFlag++;
                biggerFlag = 0;
            }
            else {
                biggerFlag = 0;
                smallerFlag = 0;
                l = r;
            }

            if (smallerFlag > 1){
                l = r-1;
                smallerFlag--;
            } else if (biggerFlag > 1) {
                l = r-1;
                biggerFlag--;
            }
            maxSize = Math.max(maxSize, r-l+1);
            r++;
        }
        return maxSize;
    }
    static void main(String[] args) {
        int[] arr = {2,0,2,4,2,5,0,1,2,3};
        System.out.println("Ans = " + maxTurbulenceSize(arr));
    }
}
/*
思路:
r發現連續兩個 > , 或連續兩個 <, 或出現一個 = 時要停下來移動 l
l 一直移動到 r-1, 一直重複這樣的操作就能找到答案

biggerFlag 左邊 > 右邊
smallerFlag 左邊 < 右邊
這兩個 Flag 看起來很抽象, 但其實這已經是最暴力最好理解的版本

另外這邊也附上官解:
https://leetcode.cn/problems/longest-turbulent-subarray/solutions/596355/zui-chang-tuan-liu-zi-shu-zu-by-leetcode-t4d8/
官解的核心思想是, right的左右兩邊都比它大, 或都比它小, 那麼這個right就可以繼續前進
否則right沒有資格繼續前進, 那就要移動left
 */