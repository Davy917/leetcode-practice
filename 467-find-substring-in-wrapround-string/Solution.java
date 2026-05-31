import java.util.Arrays;

//官方解答
class Solution467 {
    public int findSubstringInWraproundString(String s) {
        int[] dp = new int[26];
        int n = s.length();
        int l = 0, r = 0;
        while (r < n){
            if (r > 0 && (s.charAt(r) - s.charAt(r-1) + 26) % 26 != 1)
            {
                l = r;
            }
            int k = r-l+1;
            dp[s.charAt(r) - 'a'] = Math.max(dp[s.charAt(r) - 'a'], k);
            r++;
        }
        return Arrays.stream(dp).sum();
    }

    static void main(String[] args) {
        String s = "abcdabc";
        System.out.println("Ans = " + findSubstringInWraproundString(s));
    }
}
/*
建議先把713題看完, 再來看這題
這題說了要返回 s 中有多少 "不同非空子串"
舉例:
"cac" 的答案只會有"c", "a", 所以"c"並不能重複

思路:
右邊的字符一定比左邊的字符大1, z除外
不符合上述條件者, r直接停下來, 等 l = r 時, r才再度移動

如何表示字母连续 (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1
如何去掉可能重复的子串，保留最长的 Math.max(dp[p.charAt(i) - 'a'], k)
数组求合 Arrays.stream(dp).sum();

官方解答:
https://leetcode.cn/problems/unique-substrings-in-wraparound-string/

FAQ:
為什麼 467 題不能直接 ans += r - l + 1?
在 713 題中，如果 nums = [10, 5, 10, 5]，兩個 [10, 5] 都會被重複計算進 ans，這是題目要求的。
但在 467 題中，如果輸入是 p = "abcdabcd"，子字串 "abcd" 出現了兩次。
如果你直接用 ans += r - l + 1，你會把 "a", "ab", "abc", "abcd" 這些子字串重複算兩次，但題目要求唯一的，所以第二次出現時不應該再加。

dp[26] 的核心定義
dp[i] 代表：在字串 p 中，以第 i 個字母結尾的「最長連續子字串」的長度。

為什麼「最長長度」就等於「不重複的數量」?
假設p = "abcd"
以 'd' 結尾的連續子字串有幾個？
長度為 1: "d"
長度為 2: "cd"
長度為 3: "bcd"
長度為 4: "abcd"
總共 4 個。 剛好就是以 'd' 結尾的最長長度！
結論：如果以某個字母結尾的最長連續長度是 K，那麼以它結尾的合法子字串就有 K 個。
*/