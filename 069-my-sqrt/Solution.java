/*
使用 BinarySearch_basic 實現
 */
class Solution69 {
    public static int mySqrt(int x) {
        int left = 1, right = x, ans = 0;

        while (left <= right){
            int middle = left + (right - left) / 2;
            if ((long)middle * middle <= x){ //要轉成(long)
                ans = middle;
                left = middle + 1;
            }
            else
                right = middle - 1;
        }
        return ans;
    }
    static void main(String[] args) {
        int x = 8;
        System.out.println(mySqrt(x));
    }
}

/*
代碼參考
algo/BinarySearch/BinarySearch_basic.java
 */