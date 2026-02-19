//偏暴力解, 滑動窗口及優化解法可看Python寫法
import java.util.HashSet;
import java.util.Set;

class Solution3 {
    public int P0003_lengthOfLongestSubstring(String s) {
        int maxStrLen = 0;
        char[] c = s.toCharArray();

        for (int i=0; i<s.length(); i++){
            int curStrLen = 0;
            Set<Character> set = new HashSet<>();
            for (int j=i; j<s.length(); j++){
                if (!set.add(c[j])){break;}
                curStrLen += 1;
                if (curStrLen > maxStrLen){ maxStrLen = curStrLen; }
            }
        }
        return maxStrLen;
    }
    static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(new Solution3().P0003_lengthOfLongestSubstring(s));
    }
}
/*
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
 */

