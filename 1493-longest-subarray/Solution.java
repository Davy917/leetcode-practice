class Solution1493 {
    public static int longestSubarray(int[] nums) {
        int zeroCount = 0;
        int maxLen = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0)
                zeroCount++;
            while (zeroCount == 2){
                if (nums[l] == 0)
                    zeroCount--;
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
        }
        return maxLen-1;
    }
    static void main(String[] args) {
        int[] nums = {1,1,0,1};
        System.out.println("Ans = " + longestSubarray(nums));
    }
}
/*
思路和go版本是一樣的, 不過java這版的代碼可讀性會更好, 也比較好維護
1493-longest-subarray/solution.go
 */