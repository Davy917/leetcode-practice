import java.util.Arrays;
public class ShellSort {
    private static int[] shellSort(int[] nums) {
        int len = nums.length;
        for (int delta = len/2; delta > 0; delta /= 2) {
            System.out.println("delta = " + delta);
            for (int start = 0; start < delta; start++) {
                System.out.println("start = " + start);
                for (int i = delta + start; i < len; i += delta) {//注意i += delta
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
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }
    static void main(String[] args) {
        int[] nums = {5, 2, 8, 3, 7, 1, 6, 4};
        shellSort(nums);
    }
}
