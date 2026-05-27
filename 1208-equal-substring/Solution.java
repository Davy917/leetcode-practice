/*
自己寫的
 */
class Solution1208 {
    public static int equalSubstring(String s, String t, int maxCost) {
        int maxLen = 0;
        int l = 0, r = 0;
        while (r < s.length()){
            if (maxCost >= 0)
                maxCost -= Math.abs(s.charAt(r) - t.charAt(r));
            while (maxCost < 0){
                maxCost += Math.abs(s.charAt(l) - t.charAt(l));
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
            r++;
        }
        return maxLen;
    }
    static void main(String[] args) {
        String s = "abcd";
        String t = "bcdf";
        System.out.println("Ans = " + equalSubstring(s, t, 3));
    }
}
/*
如果 maxCost < 0, 移動 l
 */