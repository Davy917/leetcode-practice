/*
官方解答
 */
import java.util.Arrays;

class Solution80 {
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            System.out.printf("[front] slow = %d, fast = %d, nums = %s\n", slow, fast, Arrays.toString(nums));
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
            System.out.printf("[end] slow = %d, fast = %d, nums = %s\n", slow, fast, Arrays.toString(nums));
        }
        return slow;
    }
    static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3};
        System.out.println("Ans = " + removeDuplicates(nums));
    }
}

/*
官方題解:
https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/solutions/702644/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-yec2
 */
