import java.util.Arrays;
public class CountingSort {
    public static int[] sort(int[] nums){
        if (nums == null)
            return null;
        int max = nums[0];
        int min = nums[0];
        for (int val :nums){
            if (val > max)
                max = val;
            else if (val < min)
                min = val;
        }
        int trueRange = max - min + 1;
        if (trueRange == 0)
            return null;

        int[] counting = new int[trueRange];

        for (int val : nums) {
            counting[val - min] += 1;
        }

        int preSum = 0;
        for (int index = 0; index < counting.length; index++){
            int temp = counting[index];
            counting[index] = preSum;
            preSum += temp;
        }
        System.out.println(Arrays.toString(counting));

        int[] result = new int[nums.length];
        for (int index = 0; index < nums.length; index++){
            result[counting[nums[index] - min]] = nums[index];
            counting[nums[index] - min]++;
        }
        System.arraycopy(result, 0, nums, 0, result.length);
        return nums;
    }
    static void main(String[] args) {
        int[] nums = {2, 4, 5, 2, 6, 4};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
/*
[2, 0, 2, 1, 1]
[0, 2, 2, 4, 5]
 */