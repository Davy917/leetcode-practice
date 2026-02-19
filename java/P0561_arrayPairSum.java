import java.util.*;
class Solution561 {
    public int P0561_arrayPairSum(int[] nums) {

        Arrays.sort(nums);
        int result = 0;
        int j = nums.length - 1;
        int i =0;

        while (j >= 0){
            i = j - 1;
            System.out.println(nums[j] +" " + nums[i]);
            result += Math.min(nums[j], nums[i]);
            j-=2;
        }
        return result;
    }

    static void main() {
        Solution561 sol = new Solution561();
        int[] nums = {1,4,3,2};
        System.out.println(sol.P0561_arrayPairSum(nums));
    }
}