import java.util.Arrays;

class Solution643 {
    static double findMaxAverage(int[] nums, int k) {
        int left = 0;
        int windowSum = 0;
        for(int i = 0; i < k; i++){
            windowSum += nums[i];
        }
        int maxVal = windowSum;
        for (int index = k-1; index < nums.length; index++) {
            if (index > k-1)
                windowSum += nums[index];
            maxVal = Math.max(maxVal, windowSum);
            windowSum -= nums[left];
            left++;
        }
        return (double)maxVal/k;
    }
    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println("Ans = " + findMaxAverage(nums, k));
    }
}
