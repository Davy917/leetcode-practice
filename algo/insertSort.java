import java.util.Arrays;
public class insertSort {
      static void insertSort(int[] nums){
        int temp;
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            temp = nums[i];
            while (j >= 0 && nums[j] > temp){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j+1] = temp;//注意不可以寫在w
        }
    }
    static void insertSort_v2(int[] nums){
        int temp;
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            temp = nums[i];
            while (j >= 0 && nums[j] > temp){
                nums[j + 1] = nums[j];
                nums[j] = temp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 8, 6, 1, 7, 4, 3};
        insertSort_v2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
