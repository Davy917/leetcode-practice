import java.util.*;

class Solution27 {
    public int P0027_removeElement(int[] nums, int val) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != val){
                nums[ans] = nums[i];
                ans++;
                System.out.println(Arrays.toString(nums));
            }
        }
        return ans;
    }
    static void main() {
        Solution27 sol = new Solution27();
        int[] nums = {3,2,2,3};
        int val = 3;
        System.out.println(sol.P0027_removeElement(nums, val));
    }
}

/*
```java
import java.util.*;
import java.util.stream.*;

class Solution777 {
    // 方法 A: 使用 ArrayList\<Integer\>（動態大小），最後轉回 int[]
    public int removeElementUsingList(int[] nums, int val) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            if (n != val) {
                list.add(n);
                System.out.println(list); // 印出動態集合狀態
            }
        }
        // 如果需要 primitive int[]，可以轉回：
        int[] result = list.stream().mapToInt(Integer::intValue).toArray();
        // 選擇性：把結果拷回原陣列前段
        System.arraycopy(result, 0, nums, 0, result.length);
        return result.length;
    }

    // 方法 B: 就地覆寫（雙指標 ans），不需額外集合，效能最佳
    public int removeElement(int[] nums, int val) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[ans++] = nums[i];
                System.out.println(Arrays.toString(Arrays.copyOf(nums, ans))); // 顯示已保留的部分
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution777 sol = new Solution777();
        int[] nums1 = {3, 2, 2, 3};
        int val = 3;
        System.out.println("Using List -> kept: " + sol.removeElementUsingList(nums1, val));

        int[] nums2 = {3, 2, 2, 3};
        System.out.println("In-place -> kept: " + sol.removeElement(nums2, val));
    }
}
 */