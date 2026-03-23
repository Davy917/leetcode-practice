import java.util.Arrays;

class Solution912 {
//方法一，交换法插入排序，實際跑過會超時
    public int[] sortArray(int[] nums) {
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

    void swap(int[] nums, int i, int j){
        //j = i-1
        System.out.printf("change %s %s ", nums[j], nums[i]);
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        System.out.printf("to %s %s %n", nums[j], nums[i]);
    }
//方法二，移动法插入排序，優化過後ok
    int[] insertSort(int[] nums){
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
//方法三，希爾排序
    int[] shellSort(int[] nums) {
        int len = nums.length;
        for (int delta = len/2; delta > 0; delta /= 2) {
            System.out.println("delta = " + delta);
            for (int start = 0; start < delta; start++) {
                System.out.println("start = " + start);
                for (int i = delta + start; i < len; i += delta) {
                    System.out.println("i = " + i);
                    int temp = nums[i];
                    int j = i;
                    while (j-delta >= 0 && nums[j-delta]>temp){
                        nums[j] = nums[j-delta];
                        j-=delta;
                    }
                    nums[j] = temp;//為什麼寫在這裡??
                }
            }
            System.out.printf(Arrays.toString(nums));
        }
        return nums;
    }


    static void main(String[] args) {
        int[] nums = {5, 2, 8, 3, 7, 1, 6, 4};
        //int[] nums = {5, 1, 1, 2, 0, 0};
        Solution912 sol = new Solution912();
        //sol.sortArray(nums);
        //sol.insertSort(nums);
        sol.shellSort(nums);
    }
}
/*
[5, 2, 3, 1]
[2, 5, 3, 1]
[2, 3, 5, 1]
[1, 2, 3, 5]
 */