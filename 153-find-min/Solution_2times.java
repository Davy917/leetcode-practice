class Solution_2times {
    public static int P0153_findMin(int[] nums) {
        int left = 0, right = nums.length - 1, min = nums[0];
        while (left < right){ //注意, 不加等號, 等號代表只剩一個數字時還要進迴圈, 這題不需要
            int middle = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);

            //答案只會出現在沒排好的那邊
            //右邊沒排好, 往右邊找
            if (nums[middle] > nums[right]){
                left = middle + 1;
                min = nums[right];
            }
            //左邊沒排好, 往左邊找
            else{
                right = middle;
                min = nums[left];
            }
        }
        return min;
    }
    static void main(String[] args) {
        int[] nums = {7, 8, 9, 0, 1, 2, 4, 5, 6};
        int[] nums2 = {1, 2, 3, 4, 5, 6};
        int ans = P0153_findMin(nums2);
        System.out.println("ans = " + ans);
    }
}