/*
二分查找
 */
class Solution287 {
    public static int findDuplicate(int[] nums) {
        //這份解法是在對答案的值做二分（value binary search），不是對陣列 index 做二分。
        int left = 1, right = nums.length - 1, ans = -1;
        while (left <= right){

            int middle = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, middle = %d\n", left, right, middle);
            int count = 0;

            for (int num : nums)
                if (num <= middle)
                    count++;

            System.out.println("count = " + count);

            if (count <= middle)
                left = middle + 1;
            else{
                right = middle - 1;
                ans = middle;
            }
        }
        return ans;
    }
    static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println("Ans = " + findDuplicate(nums));
    }
}
/*
圖解 1, 3, 4, 2, 2
nums 1 2 3 4
cunt 1 3 4 4 // cunt是 <= nums[i] 的元素個數

重複元素滿足 cunt[i] > i
找到第一個 cunt[i] > i 的位置就是答案

因為cunt一定是非遞減數組, 所以可以用二分查找

力扣視頻題解:
https://leetcode.cn/problems/find-the-duplicate-number/solutions/261119/xun-zhao-zhong-fu-shu-by-leetcode-solution/
 */