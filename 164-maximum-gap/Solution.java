import java.util.Arrays;

class Solution164 {
    public static int maximumGap(int[] nums) {
        if (nums.length < 2)
            return 0;
        int maxNum = nums[0];
        for (int val : nums)
             maxNum = Math.max(val, maxNum);
        int maxDigitLen = 0;
        while (maxNum > 0){
             maxNum /= 10;
             maxDigitLen++;
        }
        int dev = 1;
        int[] counting = new int[10];
        for (int Digit = 0; Digit < maxDigitLen; Digit++) {
            Arrays.fill(counting, 0);
            for (int val : nums)
                counting[val / dev % 10]++;

            int preSum = 0;
            for (int index = 0; index < counting.length; index++) {
                int temp = counting[index];
                counting[index] = preSum;
                preSum += temp;
            }


            int[] result = new int[nums.length];
            for (int val : nums){
                int radix = val / dev % 10;
                result[counting[radix]] = val;
                counting[radix]++;
            }
            System.arraycopy(result, 0, nums, 0, nums.length);
            dev *= 10;
        }
        int maxGap = 0;
        for (int index = 1; index < nums.length; index++) {
            maxGap = Math.max(maxGap, (nums[index] - nums[index - 1]));
        }
         return maxGap;
    }
    static void main(String[] args) {
        int[] nums = {1,10000000};
        System.out.println("Ans = " + maximumGap(nums));
    }
}