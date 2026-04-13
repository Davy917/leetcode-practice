/*
拿去跑leetcode會超時
 */
import java.util.Arrays;

class Solution912 {
    public static int[] sortArray(int[] nums) {
        for (int i = 1; i < nums.length ; i++){
            System.out.println("layer1");
            int j = i;
            while (j >= 1 && nums[j] < nums[j-1]){
                System.out.println("nums[i] = " + nums[i]);
                System.out.println("nums[j-1] = " + nums[j-1]);
                swap(nums, j, j-1);
                System.out.printf("nums = %s%n", Arrays.toString(nums));
                j--;
            }
        }
    return nums;
    }

   static void swap(int[] nums, int i, int j){
        System.out.printf("change %s %s ", nums[j], nums[i]);
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        System.out.printf("to %s %s %n", nums[j], nums[i]);
    }

    static void main(String[] args) {
        int[] nums = {5, 2, 8, 3, 7, 1, 6, 4};
        //int[] nums = {5, 1, 1, 2, 0, 0};
        sortArray(nums);
    }
}
/*
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/ev4tee/
 */