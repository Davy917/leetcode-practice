/*
方法一
暴力解: 遍歷數組, 返回pivot, 如果找不到真正的pivot返回nums[0]
方法二:
二分查找, 見Python
 */
class Solution154 {
    public int P154_findMinWithTry(int[] nums) {
        if (nums.length == 0)
            return -1;
        int pivot = 1;
        try {
            while (nums[pivot] >= nums[pivot - 1])
                pivot++;
            return nums[pivot];
        } catch (Exception ArrayIndexOutOfBoundsException){
            return nums[0];
        }
    }
    public int P154_findMin(int[] nums) {
        int pivot = 1;
        while (pivot < nums.length && nums[pivot] >= nums[pivot - 1]) {
            pivot++;
        }
        if(pivot ==nums.length)return nums[0];
        return nums[pivot];
    }

    static void main(String[] args) {
        int[] nums = {4, 4, 5, 6, 7};
        int[] nums2 = {4, 4, 4, 4, 5, 6, 7, 4};
        Solution154 sol = new Solution154();
        int ans = sol.P154_findMin(nums);
        int ans2 = sol.P154_findMinWithTry(nums);
        System.out.println("ans = " + ans);
    }
}
/*
Copilot建議:
try 是用來捕捉可能發生的例外（Exception），避免程式直接崩潰，並提供替代處理流程
能用邏輯判斷就先用邏輯判斷，不要把例外當一般流程控制。
 */