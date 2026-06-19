//嘗試優化python版本失敗, 但寫出了另一個版本
import java.util.Arrays;

class Solution16 {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int diff = 0;
        int ans = 0;
        for (int l = 0; l < n - 2; l++) {
            int k = l + 1;
            int r = n - 1;
            while (k + 1 !=  r){
                if (nums[l] + nums[k] + nums[r] == target)
                    return target;
                while (k + 1 != r && nums[l] + nums[k] + nums[r] < target){
                    diff = Math.abs(nums[l] + nums[k] + nums[r] - target);
                    if (minDiff > diff){
                        minDiff = diff;
                        ans = nums[l] + nums[k] + nums[r];
                    }
                    k++;
                }
                while (k + 1 !=  r && nums[l] + nums[k] + nums[r] > target){
                    diff = Math.abs(nums[l] + nums[k] + nums[r] - target);
                    if (minDiff > diff){
                        minDiff = diff;
                        ans = nums[l] + nums[k] + nums[r];
                    }
                    r--;
                }
            }
            diff = Math.abs(nums[l] + nums[k] + nums[r] - target);
            if (minDiff > diff){
                minDiff = diff;
                ans = nums[l] + nums[k] + nums[r];
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        //int[] nums = {4,0,5,-5,3,3,0,-4,-5};
        int[] nums2 = {1,1,1,1};
        System.out.println("Ans = " + threeSumClosest(nums2, 3));
    }
}
/*
建議先看過第15題
015-three-sum/Solution.java

思路:
只要我們在每次 l ,k, r 要移動時都做下面這個判定, 就能確保最接近 target 的合, 被 minDiff 追蹤著
diff = Math.abs(nums[l] + nums[k] + nums[r] - target);
if (minDiff > diff){
    minDiff = diff;
    ans = nums[l] + nums[k] + nums[r];
}

k 跟 r 是不允許重合在一起的, 否則 minDiff 會記錄到錯誤的值
故每個迴圈都要判斷 k + 1 !=  r
*/