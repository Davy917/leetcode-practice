//官方解答
import java.util.Arrays;
class Solution719 {
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n-1]-nums[0];
        while (left <= right) //二分查找
        {
            int mid = left + (right - left)/2;
            int count = 0;
            System.out.printf("left = %d, right = %d, mid = %d\n", left, right, mid);
            for (int i =0, j = 0; j < n; j++) //雙指針
            {
                while (nums[j] - nums[i] > mid)
                    i++;
                count += j-i;
                System.out.printf("i = %d, j = %d, count = %d\n", i, j, count);
            }
            if (count >= k)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }
    static void main(String[] args) {
        int[] nums = {1,6,1,2,9,2}; //sorted nums = 1,1,2,2,6,9
        int k = 7;
        System.out.println("Ans = " + smallestDistancePair(nums, k));
    }
}

/*
[參數含意]
left = 0：最小可能距離（有重複數時可以是 0）
right = max(nums) - min(nums)：最大可能距離
所以二分的是「答案距離 d」，不是在二分 nums 的位置。

i/j 才是陣列索引雙指針，用來計算「距離 <= mid 的 pair 數量」。
雙指針等效上會掃過整個 nums 一次

count 的意義是：
在目前 mid 下，距離 <= mid 的 pair 數量。
換言之第15行的意思就是, 如果我們找到一組配對是值大於 mid 的, 那我們將會忽略該配對


第一輪打印出來是這樣的
left = 0, right = 8, mid = 4
i = 0, j = 0, count = 0
i = 0, j = 1, count = 1
i = 0, j = 2, count = 3
i = 0, j = 3, count = 6
i = 2, j = 4, count = 8
i = 4, j = 5, count = 9

在當前 mid = 4 下，有 9 組配對距離 <= 4。
也就是：
距離 0、1、2、3、4 的配對加起來，共 9 對
距離 5、6、7、8 的配對不算進來

這為什麼對二分有幫助
因為你要找「第 k=7 小」的距離，而你知道：
mid=4 時有 9 對距離 <= 4
9 >= 7，代表答案距離 <= 4
所以能縮小搜尋範圍到 [0..3]，逐步逼近真正答案。

官方解答:
https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
 */