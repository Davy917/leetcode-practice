//自己寫的
class Solution340 {
    static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] counting = new int[128];
        int distinct = 0; //代表現在窗口中有多少相異字元
        int l = 0, r = 0;
        int curLen = 0, maxLen = 0;
        while (r < s.length()){
            if (counting[s.charAt(r)] == 0)
                distinct++;
            counting[s.charAt(r)]++;
            curLen++;
            while (distinct == k+1){
                counting[s.charAt(l)]--;
                curLen--;
                if (counting[s.charAt(l)] == 0)
                    distinct--;
                l++;
            }
            maxLen = Math.max(maxLen, curLen);
            r++;
        }
        return maxLen;
    }
    public static void main(String[] args) {
        String s = "aa";
        int k = 1;
        System.out.println("Ans = " + lengthOfLongestSubstringKDistinct(s, k));
    }
}
/*
思路:
跟159題是一致的, 但這題用Array 來寫
159-length-of-longest-substring-two-distinct/Solution.java

r發現第 k+1 個字元的時候, 要移動l
l 移動到, 當前窗口只剩 k 個字元的時候, 接著移動 r
如此循環
 */