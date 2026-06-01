import java.util.TreeSet;

//官方解答
class Solution220 {
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        var set = new TreeSet<Long>();
        for (int i=0; i<n; i++){
            Long ceiling = set.ceiling((long) nums[i] - (long) valueDiff);
            System.out.printf("nums[i] = %d, ceiling = %d\n", nums[i], ceiling);
            if (ceiling != null && ceiling <= (long)nums[i] + (long) valueDiff)
                return true;

            set.add((long)nums[i]);
            System.out.println("set = " + set);
            if (i >= indexDiff)
                set.remove((long)nums[i-indexDiff]);
        }
        return false;
    }
    static void main(String[] args) {
        int[] nums = {1,5,9,1,5,9};
        int indexDiff = 2;
        int valueDiff = 3;
        System.out.println("Ans = " + containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff));
    }
}
/*
第9行的用意,舉例說:
你每次處理 nums[i]，要找是否存在某個 y，滿足 |y - nums[i]| <= valueDiff
帶入數值 |y - 5| <= 3 , 此時 y 只可能在 2 ~ 8 這個區間

寫成公式 nums[i] - valueDiff <= y <= nums[i] + valueDiff

第16行, 舉例說:
set按照順序加入nums[i], 當我們加入一個新元素, 同時也可確定最舊的元素已經不可能是答案了, 因為題目要求 abs(i - j) <= indexDiff
所以加入新元素後移除舊元素, 可以用這種方式判斷當前最舊的是否需要被移除了

從滑動窗口的視角來說, i 就是 r, i-indexDiff 就是 l
官方解答:
https://leetcode.cn/problems/contains-duplicate-iii/solutions/726619/cun-zai-zhong-fu-yuan-su-iii-by-leetcode-bbkt/

語法:
ceiling(x) 的作用是：
在 TreeSet 中找出「大於或等於 x 的最小元素」 如果找不到，回傳 null。
舉例:
[2, 5, 8, 10]
    ceiling(1) -> 2
    ceiling(5) -> 5
    ceiling(6) -> 8
    ceiling(11) -> null
 */