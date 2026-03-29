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

//v2的寫法在邏輯上不嚴謹, 不屬於正規的插入排序法, 但暫時也無法證明它的結果是錯的
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
