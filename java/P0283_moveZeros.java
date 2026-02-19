import java.util.*;
class Solution283 {
    public void P0283_moveZeroes(int[] nums) {

        int counter = 0;

        for (int i=nums.length-1; i>-1; i--){
            System.out.println("i = " + i);
            if(nums[i] == 0){
                counter++;
                System.out.println("counter = " + counter);
                for (int j = i; j< nums.length-counter; j++){
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    System.out.println(Arrays.toString(nums));
                }
            }
            else {
                continue;
            }
        }
        return;
    }
    static void main(String[] args) {
        int[] arr = {0,1,0,3,0,11,12,0};
        new Solution283().P0283_moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}
/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。
输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
int[] arr = new int[] {0,1,0,3,0,11,12};
new Solution999().moveZeroes(arr);
System.out.println(Arrays.toString(arr));
 */