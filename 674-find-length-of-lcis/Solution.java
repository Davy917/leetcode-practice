/*
非官方解答, 自己想的
 */
class Solution674 {
    public static int findLengthOfLCIS(int[] nums) {
        int LCIS = 1, temp = 1, index = 1;

        while (index < nums.length){
            if (nums[index - 1] < nums[index])
                temp++;
            else {
                if (temp > LCIS)
                    LCIS = temp;
                temp = 1;
            }
            index++;
        }
        if (index == nums.length && temp > LCIS)
            LCIS = temp;
        return LCIS;
    }
    static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 4, 7, 8, 9};
        int[] nums2 = {1, 2};
        System.out.println("Ans = " + findLengthOfLCIS(nums2));
    }
}