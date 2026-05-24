import java.util.Arrays;

class Solution424 {
    public static int characterReplacement(String s, int k) {
        int l = 0, r = 0;
        int res = 0;
        int maxCount = 0;
        int[] counting = new int[26];

        while (r < s.length()){
            counting[s.charAt(r) - 'A']++;
            maxCount = Math.max(maxCount, counting[s.charAt(r) - 'A']);
            if (k == 0 && r-l+1){

            }
            r++;
        }
        return 0;
    }
    static void main(String[] args) {
        String s = "AABCABBB";
        int k =2;
        System.out.println("Ans = " + characterReplacement(s, k));
    }
}
