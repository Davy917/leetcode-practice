class Solution1438 {
    public static int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int maxCount = 0;
        for (int l = 0; l < n; l++) {
            int r = l, curCount = 0;
            while (r < n && Math.abs(nums[l] - nums[r]) <= limit){
                curCount++;
                r++;
            }
            maxCount = Math.max(maxCount, curCount);
        }
        return maxCount;
    }
    static void main(String[] args) {
        int[] nums = {1,5,6,7,8,10,6,5,6};
        int limit = 4;
        System.out.println("Ans = " + longestSubarray(nums, limit));
    }
}
/*
保證當前子數組中, 最大絕對差 <= limit

6,7,8,10,6
紀錄當前數組中的最大值, 一直拿這個值去扣新來的num
 */