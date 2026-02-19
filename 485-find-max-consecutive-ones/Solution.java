class Solution485 {
    public int P0485_findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 0){
            return nums.length;
        }
        int maxLen = 0;
        int tempLen = 0;

        for (int i = 0; i< nums.length; i++){
            if (nums[i] == 1){
                tempLen++;
                System.out.println("1");
            }
            else {
                tempLen = 0;
            }
            if (tempLen > maxLen) {
                maxLen = tempLen;
                System.out.println("find new maxLen" + maxLen);
            }
        }
        return maxLen;
    }
    static void main() {
        Solution485 sol = new Solution485();
        int[] nums = {1,1,0,1,1,1};
        System.out.println(sol.P0485_findMaxConsecutiveOnes(nums));
    }
}