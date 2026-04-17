class GuessGame{
    public static final int pick = 7;
    int guess(int n){
        return (n > pick) ? -1 : (n < pick ? 1 : 0);
        //等價 return Integer.compare(pick, n);
    }
}
class Solution374 extends GuessGame{
    public int guessNumber(int n) {
        int left = 1, right = n, ans = 0;
        while (left <= right){ //可能要改成<
            int middle = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);
            int result = guess(middle);
            if (result > 0)
                left = middle + 1;
            else if (result < 0)
                right = middle - 1;
            else{
                ans = middle;
                break;
            }
        }
        return ans;
    }
    static void main(String[] args) {
        int range = 10;
        Solution374 sol = new Solution374();
        System.out.println(sol.guessNumber(10));
    }
}

/*
extends介紹:
https://leetcode.cn/leetbook/read/on-java-zhong-wen-ban-ji-chu-juan/lv2phv/

Neetcode講解, 迭代 BinarySearch
https://www.youtube.com/watch?v=xW4QsTtaCa4

中文版, 分而治之, 遞歸BinarySearch
https://www.youtube.com/watch?v=TWpumg75Kmo
 */

/*
1 2 3 4 5 6 7 8 9 10
 */