package BinarySearch;

public class BinarySearch_ultra {
    public static int search(int[] nums, int target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 != right){
            int middle = (left + right) / 2;
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);
            if (target > nums[middle])
                left = middle; // 為什麼不能寫成 left = middle + 1
            else if (target < nums[middle])
                right = middle;
            else
                return middle;
        }
        return -1;
    }
    static void main(String[] args) {
        int[] arr = {1};
        int target = 2;
        int ans = search(arr, target);
        System.out.println("ans = " + ans);
    }
}
/*
    val = -1, 0, 3, 4, 5, 9, 12
index = -1, 0, 1, 2, 3, 4, 5, 6, 7
/*
代碼說明:
https://www.youtube.com/watch?v=JuDAqNyTG4g

也可見leetbook模板3:
https://leetcode.cn/leetbook/read/binary-search/xe22ch/

left = middle; // 為什麼不能寫成 left = middle + 1
例如：
`nums = [1]`，`target = 2`

流程（把第 11 行改成 `left = middle + 1`）：

1. 初始 `left = -1`，`right = 1`
2. `middle = 0`，且 `target > nums[0]`
3. 設成 `left = middle + 1 = 1`
4. 此時 `left = 1`，`right = 1`，但迴圈條件是 `left + 1 != right`，即 `2 != 1`，仍然成立，會繼續跑
5. 下一輪 `middle = (1 + 1) / 2 = 1`，存取 `nums[1]` 直接越界

所以在這個「開區間邊界」寫法中，`left = middle` 才能維持不變量；改成 `middle + 1` 會破壞邊界語義。
 */