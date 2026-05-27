//自己寫的
class Solution1004 {
    public static int longestOnes(int[] nums, int k) {
        int maxLen = 0;
        int l = 0, r = 0;
        while (r < nums.length){
            if (nums[r] == 0)
                k--;
            while (k == -1){
                if (nums[l] == 0)
                    k++;
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
            r++;
        }
        return maxLen;
    }
    static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int[] nums2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        System.out.println("Ans = " + longestOnes(nums2, 3));
    }
}
/*
思路是窗格內最多允許 k 個 0
出現第 k+1 個 0 的時候要移動 l
l 要移動到下一個 0
 */