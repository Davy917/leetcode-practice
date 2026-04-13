import java.util.Arrays;

public class InsertSort {
    private static int[] insertSort(int[] nums){
        for (int i = 1; i < nums.length; i++){
            int temp = nums[i]; //暫存nums[i]
            int j = i-1;
            while (j >= 0 && nums[j] > temp){
                System.out.printf("j = %s > ", nums[j]);
                System.out.println("temp = " + temp);
                nums[j+1] = nums[j];
                System.out.println("nums = " + Arrays.toString(nums));
                j--;
            }
            nums[j + 1] = temp;
            System.out.println("nums = " + Arrays.toString(nums));
        }
        return nums;
    }

    static void main(String[] args) {
        int[] nums = {5, 2, 8, 3, 7, 1, 6, 4};
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
