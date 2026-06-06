import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int l = 0, r = nums.length;
        while (l < r){
            if (nums[l] + nums[r] > result){

            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println("Ans = " + threeSum(nums));
    }
}