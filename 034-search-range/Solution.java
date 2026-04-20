/*
代碼參考模板:
algo/BinarySearch/BinarySearch_ultra.java
 */
import java.util.Arrays;

class Solution34 {
    public static int[] P034_searchRange(int[] nums, int target) {
        int[] ans = {0, 0};
        int left = -1, right = nums.length;

        if (nums.length == 0)
            return new int[]{-1, -1};

        //先找開始位置
        while (left + 1 !=  right){
            int middle = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);
            if (nums[middle] < target)
                left = middle;

            else if (nums[middle] > target)
                right = middle;

            else
                right = middle; //為什麼不是middle + 1
        }

        if (right >= nums.length || nums[right] != target)
            return new int[]{-1, -1};

        ans[0] = right;
        left = ans[0];
        right = nums.length - 1;

        while (left < right){
            int middle = left + (right - left + 1) / 2; //注意上取整避免死迴圈
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);
            if (nums[middle] > target){
                right = middle - 1;
            }
            //只可能是 nums[middle] == target
            else {
                left = middle;
            }
        }
        ans[1] = left; //迴圈後 left 就是最後一個 target 的位置
        return ans;
    }
    static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 2, 2, 2, 2, 5};
        int[] nums2 = {1};
        int target = 2;
        int[] ans = P034_searchRange(nums, target);
        System.out.println("ans = " + Arrays.toString(ans));
    }
}
/*
FAQ:
什麼是非遞減數組??

也就是「往右看不會變小」，可以：
    變大 or 保持一樣（可重複）

例子（都是非遞減）：
[1, 2, 3, 4]（嚴格遞增）
[1, 1, 2, 2, 2, 5]（有重複）
[7, 7, 7]（全相等）

反例（不是非遞減）：
    [1, 3, 2, 4]（3 > 2，出現下降）


我對第一個迴圈的理解是,
如果middle值比target還小, 代表答案在右邊, 把left移到middle, 此時middle已被排除
如果middle值比target還大, 代表答案在左邊, 把right移到middle, 此時middle已被排除
如果middle值與target相等, 因為要找起始位置, 所以往左找, 移動right這沒問題
但right = middle 不就把middle本身也排除了嗎??
middle本身也可能是答案, 為什麼不是寫成right = middle + 1
 */