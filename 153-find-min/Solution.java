/*
第一次練習看python
完整思路看java
再優化看go
*/
class Solution153 {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        if (nums[l] <= nums[r]) { return nums[l]; }
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[0] <= nums[m]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }   
    static void main(String[] args) {
        int[] nums = {5,6,0,1,2,3,4};
        System.out.println("Ans = " + findMin(nums));
    }
}
/*
自己寫的
思路
找最小值 == 找原數組的nums[0]
時間複雜度O(logn) -> 二分查找

分兩種情況:
第一種情況:
[0,1,2,3,4,5,6] 轉 0 , n次, 相當於沒旋轉
直接 return nums[0]

第二種情況:
[5,6,0,1,2,3,4], 轉 1 ~ n-1次

特徵:
nums[0] 一定不是原數組的nums[0]
nums[0] 必定 >= nums[r]

根據特徵, 比大小 nums[m], nums[0]
循環 l, r是否重疊
    m = (l+r)/2
        0 ~ m 已排好, 答案一定在 m 右邊

        0 ~ m 未排好, 答案可能在 m 左邊(含m本身)

定義:
l = 0, 		          l可能是答案
r = nums.length - 1,  r可能是答案
不斷收斂, 直到 l == r, return nums[l]

第一種情況, 第二種情況分開處理, 思路較順
寫的時候一律都要在紙上畫狀態圖, 搭配推演
*/