//看明白了, 但自己寫一遍還是寫不出來
import java.util.Arrays;

class Solution424 {
    public static int characterReplacement(String s, int k) {
        int l = 0, r = 0;
        int maxCount = 0, res = 0;
        int[] counting = new int[26];

        while (r < s.length())
        {
            counting[s.charAt(r) - 'A']++;
            maxCount = Math.max(maxCount, counting[s.charAt(r) - 'A']);
            r++;
            if (r-l > maxCount + k) //達成此條件代表, l要開始往右移了
            {
                counting[s.charAt(l) - 'A']--;
                l++;
            }
            res = Math.max(res, r-l);
        }
        return res;
    }
    static void main(String[] args) {
        String s = "A";
        int k = 1;
        System.out.println("Ans = " + characterReplacement(s, k));
    }
}
/*
官方解答:
https://leetcode.cn/leetbook/read/sliding-window-and-two-pointers/rlcgrd/
 */