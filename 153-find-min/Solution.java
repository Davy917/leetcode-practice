class Solution153 {
    public int P0153_findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int min = Integer.MAX_VALUE;

        while (left <= right){
            int mid = left + (right - left)/2;
            System.out.println("mid = " + mid);
            if (min > nums[mid]){
                min = nums[mid];
            }
            if (nums[mid] > nums[nums.length-1]){
                left = mid + 1;
                System.out.println("left = " + left);
            }
            else {
                right = mid - 1;
                System.out.println("right = " + right);
            }
        }
        return min;
    }

    static void main(String[] args) {
        System.out.println(new Solution153().P0153_findMin(new int[]{3, 1, 2}));
    }
}
/*
输入：nums = [4,5,6,8,9,10,11,0,1,2]
输出：0
解释：原数组为 [8,7,0,1,2,4,5,6] ，旋转 4 次得到输入数组。

输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 */