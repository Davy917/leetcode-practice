import java.util.*;
class Solution167 {
    public int[] P0167_twoSum(int[] numbers, int target) {

        int[] result = {0, 0};
        int left = 0;
        int right = numbers.length - 1;

        while (left < right){
            if(numbers[left] + numbers[right] == target){
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            }
            else if (numbers[left] + numbers[right] > target){
                right--;
            }
            else{
                left++;
            }
        }

        return null;
    }
    public static void main(String[] args) {
        Solution167 sol = new Solution167();

        int[] nums = {2, 3, 5, 7, 11, 15};
        int target = 10;
        System.out.println(Arrays.toString(sol.P0167_twoSum(nums, target)));
        //System.out.println(sol.twoSum(nums, target));
    }
}

/*
由於陣列是有序的, 如果當前數比target還要大, 後面的數都不用看了
*/