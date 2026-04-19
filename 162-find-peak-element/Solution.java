/*
參考代碼模板:
(basic + advance組合題)
algo/BinarySearch/BinarySearch_advance.java
algo/BinarySearch/BinarySearch_basic.java
 */
class Solution162 {
    public static int P0162_findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);
            if (nums[middle] < nums[middle + 1])
                left = middle + 1;
            else
                right = middle;
        }
        return left;
    }
    static void main(String[] args) {
        int[] nums = {1, 2, 1, 5, 3, 6, 4};
        int ans = P0162_findPeakElement(nums);
        System.out.println("ans = " + ans);
    }
}
/*
參考代碼:
從影片9:00開始
https://www.youtube.com/watch?v=6S9ETYGfk9k

很多人可能跟我一开始一样，有一个思维误区，就是一次走一半难道不怕错过了一些峰值吗，但是其实这不重要，题目只是要求求出一个峰值，
因此只要右边存在答案就可以把左边都抛弃掉，然后只要一直确保这个区间存在答案，当区间长度为1时，答案就出来了。

FAQ:
while (left < right) 這個並不是左右都閉的寫法, 少了一個等號, 為什麼這樣寫不會出錯??
在這題（findPeakElement）用的是左右都閉區間 [left, right]
沒錯，但 while (left < right) 仍然完全合理，而且是這種「找某種極值/邊界」二分的標準寫法。

核心原因：這份程式保證每一輪都會把區間縮小，並且把答案維持在區間內；
當 left == right 時，區間只剩一個元素，那個位置就是答案，應該直接停止，不需要再進迴圈。

為什麼 [left, right] 也常用 while (left < right)？
[left, right] 表示答案一定在這個閉區間裡。
當 left < right 時，區間至少有 2 個元素，我們可以安全地用 mid 與 mid+1 比較來決定往哪邊縮。
當 left == right 時，區間只剩 1 個索引：
    不需要再比較 nums[mid+1]
    直接 return left 即可
也就是說：
while (left < right) 是「做到剩 1 個候選就停」的寫法，特別適合這題。

把等號加上去（while (left <= right)）會不會有問題？
會有問題，至少有兩種常見風險：
風險 1：mid + 1 可能越界
    當 left == right 時：
        mid == left == right
        你仍然會做 nums[mid + 1]
        若此時 mid == nums.length - 1，就會讀到 nums[nums.length] → 越界
風險 2：即使你補了越界保護，也可能不收斂/邏輯變複雜
    如果硬要用 <=，你通常就必須：
        改比較方式（不能直接用 mid+1）
        或在 left==right 時特判 break/return 不然容易出現「多跑一輪但沒有任何必要」甚至「更新規則不小心造成不收斂」。

總結（你可以記成一句規則）
    找邊界/找峰值這類「最後收斂到單點」的二分：常用 while (left < right)（收斂到 left == right 就結束）
    找明確值、允許區間變空（像一般 binary search 回傳 -1）：常用 while (left <= right)
你這題的寫法本質是「收斂到唯一答案」，所以用 < 才是最乾淨、也避免 mid+1 越界的方式。
 */