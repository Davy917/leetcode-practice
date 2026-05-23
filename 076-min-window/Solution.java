/*
非自己寫的
 */
import java.util.Arrays;

class Solution76 {
    public static String minWindow(String s, String t) {
        int minLen = s.length()+1;
        int start = 0;

        int needCount = t.length();
        int[] need = new int[128];
        for (char c:t.toCharArray())
            need[c]++; //用該字元的 ASCII 值當索引，將 `need` 對應位置加 `1`。

        System.out.println(Arrays.toString(need));
        int left = 0, right = 0;
        while (right < s.length()){
            char find = s.charAt(right);
            if (need[find] > 0){
                needCount--;
            }
            need[find]--; //這行一定要寫在這個位置
            while (needCount == 0) //說明此時滑動窗口實現全覆蓋, 可以開始右移left指針, 縮小窗口
            {
                System.out.printf("left = %d, right = %d\n", left, right);
                System.out.println(Arrays.toString(need));
                //更新最小滑動窗口信息
                if (right - left < minLen){
                    minLen = right - left;
                    start = left;
                }
                char tofind = s.charAt(left);
                need[tofind]++;
                if (need[tofind] > 0){
                    needCount++;
                }
                left++;
                System.out.println(Arrays.toString(need));
            }
            right++;
        }
        return minLen == s.length()+1 ? "" : s.substring(start, start + minLen+1);
    }
    static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println("Ans = " + minWindow(s, t));
    }
}

/*
補充:
如何確認'A'的ASCII值是多少??
char c = 'A';
System.out.println((int) c); // 65


這段演算法需要「無論字元是否仍被需要」都先做一次 `need[find]--`，用負值來表示「多抓到的字元」。
如果把 `need[find]--` 移到 `if \(need[find] > 0\)` 裡面，就只會在「目前還缺這個字元」時才遞減，會導致：
1. 多餘字元不會被記錄成負值。
2. 之後左指針縮窗時，`need[tofind]++` 的判斷基準失真。
3. `needCount` 可能過早增加，讓視窗被錯誤地判定為不滿足，錯過正確最短答案。

參考代碼:
評論區第一則 Quirky NorthcuttAB1 的代碼
https://leetcode.cn/problems/minimum-window-substring/solutions/257359/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/

觀察打印出來的nums,第65~67個數字的變化
 */