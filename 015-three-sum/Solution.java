//跟AI討論後寫出來的版本, 比官解更好理解
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 1. 排序是前提
        int n = nums.length;
        for (int l = 0; l < n-2; l++){
            // 2. 避免重複的固定元素
            // 如果當前數字跟前一個一樣，跳過，因為以這個數字開頭的所有組合都已經找過了
            if (l > 0 && nums[l] == nums[l-1])
                continue;
            int t = - nums[l];
            int r = n - 1;
            int k = l + 1;
            while (k < r){
                //找到一組解
                int sum = nums[k] + nums[r];
                if (sum == t) {
                    result.add(Arrays.asList(nums[l], nums[k], nums[r]));
                    // 3. 關鍵：找到解後，跳過左右指針指向的重複數字
                    while (k < r && nums[k] == nums[k+1]) k++;
                    while (k < r && nums[r] == nums[r-1]) r--;
                    k++;
                    r--;
                }
                else if (sum > t) r--;
                else k++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {-4, -1, -1, 0, 1, 2};
        int[] nums2 = {-4, 0, 3, 4, 4, 4, 6};
        System.out.println("Ans = " + threeSum(nums));
    }
}
/*
一開始, 三個指針分別所在的位置
l   k              r
-4, 0, 3, 4, 4, 4, 6

l   k           r
-4, 0, 3, 4, 4, 4, 6

以此類推
 */