class Solution1438 {
    public static int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int maxLen = 0;
        for (int l = 0; l < n; l++) {
            int maxNum = nums[l], minNum = nums[l];
            int curLen = 0;
            int r = l;
            while (r < n && Math.abs(maxNum - nums[r]) <= limit && Math.abs(minNum - nums[r]) <= limit){
                maxNum = Math.max(maxNum, nums[r]);
                minNum = Math.min(minNum, nums[r]);
                curLen++;
                r++;
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }
    static void main(String[] args) {
        int[] nums = {1,5,6,7,8,10,6,5,6};
        int limit = 4;
        System.out.println("Ans = " + longestSubarray(nums, limit));
    }
}
//暴力解超時, 試著優化這個解法