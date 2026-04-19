package BinarySearch;

public class BinarySearch_advance {
    public static int search(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;
        while (left < right){ //注意沒有等號
            int middle = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);
            if (nums[middle] < target)
                left = middle + 1;
            else if (nums[middle] > target)
                right = middle; //注意不用 + 1
            else
                return middle;
        }
        if(left != nums.length && nums[left] == target)//why?? 什麼情況下會用到這裡
            return left;
        return -1;
    }

    static void main(String[] args) {
        int[] nums = {-1, 0, 3, 4, 5, 9, 12};
        int target = 3;
        int ans = search(nums, target);
        System.out.println("ans = " + ans);
    }
}

/*
代碼出處:
https://leetcode.cn/leetbook/read/binary-search/xerqxt/
 */