package BinarySearch;
/*
baisc 版本用的是「左右都閉」區間：[left, right]
 */
class BinarySearch_basic {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length - 1;
        while (left <= right){
            int middle = left + (right - left) / 2; //為什麼不是(left + right)/2, 見下方說明
            if (nums[middle] < target)
                left = middle + 1; //寫成 left = ++middle; 在這裡是等值的, 但實際意義上並不一樣
            else if (nums[middle] > target)
                right = middle - 1;
            else
                return middle;
        }
        return -1;
    }
    static void main(String[] args) {
        int[] arr = {-1, 0, 3, 4, 5, 9, 12};
        int target = 5;
        int ans = search(arr, target);
        System.out.println("ans = " + ans);
    }
}

/*
代碼出處:
https://leetcode.cn/leetbook/read/binary-search/xe5fpe/

問題一
比較:
left = ++middle
left = middle + 1

left = ++middle
會把middle的值先+1再把值給到 left, 所以middle的值會被動到

left = middle + 1
把middle + 1值給到left, 但middle 實際值不會被動到

問題二
這個版本是寫成left + (right - left) / 2 而不是 (left + right)/2 寫成後者據說在某些情況下會出錯, 能否指出是哪種情況下寫成後者會出錯??
會出錯的情況是：`left` 和 `right` 都很大時，`(left + right)` 可能發生**整數溢位**。

在 Java 中 `int` 上限是 `2147483647`。
例如：

\- `left = 2000000000`
\- `right = 2100000000`

此時：

\- `left + right = 4100000000`（超過 `int` 上限）
\- 會溢位成負數
\- `middle` 變成錯誤值（可能是負數或很小），導致二分搜尋邏輯錯誤，甚至索引越界

所以用：

`left + (right - left) / 2`

可以避免先做大數相加，通常更安全。
`right - left` 在合法區間內不會超過範圍，整體較不易溢位。
 */