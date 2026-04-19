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
                min = nums[right]; //不夠嚴謹
            }
            //左邊沒排好, 往左邊找
            else{
                right = middle; //注意, 不能 - 1, test case[3, 1, 2]會有問題
                min = nums[left]; //不夠嚴謹
            }
        }
        return min;
    }
    static void main(String[] args) {
        int[] nums = {7, 8, 9, 0, 1, 2, 4, 5, 6};
        int[] nums2 = {1, 2, 3, 4, 5, 6};
        int[] nums3 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums4 = {3, 1, 2};
        int ans = P0153_findMin(nums4);
        System.out.println("ans = " + ans);
    }
}
/*
正確嚴謹的思路是:
比較 nums[mid] 和 nums[right],
若 nums[mid] > nums[right]
最小值在右半邊 (mid, right],
否則最小值在左半邊 [left, mid]

當nums剛好是照順序排序時
會一直走第二種情況（nums[mid] <= nums[right]），最後收斂到 0
它可以視為是同一條規則的特例

min = nums[left];
min = nums[right];
min的更新邏輯不夠嚴謹，這部分可以直接拿掉，最後回傳 nums[left] 才是標準不變式寫法。

總結:
每輪用 nums[mid] 與 nums[right] 判斷哪一半一定包含最小值，保留該半區間直到 left == right。
 */