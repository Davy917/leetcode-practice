class Solution209 {
    public int P0209_minSubArrayLen(int target, int[] nums) {
        //宣告變數
        int left = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right<nums.length; right++){
            sum += nums[right];
            System.out.println("sum = " + sum);

            while (sum >= target){
                if (right - left + 1 < minLen){
                    minLen = right - left + 1;
                    System.out.println("find shorter len" + minLen);
                }
                //簡潔寫法
                //minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                System.out.println("sum - nums[left] = " + sum);
                left++;
            }
        }

        if (minLen == Integer.MAX_VALUE){
            return 0;
        }
        else {
            return minLen;
        }
        //return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
    public static void main(String[] args) {
        Solution209 sol = new Solution209();
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(sol.P0209_minSubArrayLen(target, nums));
    }
}

/*
可以把 Integer.MAX_VALUE 視為「接近無窮大」的 int 型別哨兵，實際值為 2^31 - 1（2147483647）。它是 Integer 類別的靜態常數（static final），不是方法。

注意事項：


它不是真正的無限大，超過後會溢位。
避免對它做 +1 等會導致溢位的算術；更新最小值時可用 Math.min(...)。
若需更大範圍，改用 long 與 Long.MAX_VALUE。
 */