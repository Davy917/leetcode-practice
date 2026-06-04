//想複習java deque型別可以看, python版本也有一樣的實作
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution1438 {
    //暴力解超時, 試著優化這個解法
    public static int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int maxLen = 0;
        for (int l = 0; l < n; l++) {
            int maxNum = nums[l], minNum = nums[l];
            int curLen = 0;
            int r = l;
            while (r < n && Math.abs(maxNum - nums[r]) <= limit && Math.abs(minNum - nums[r]) <= limit){
                maxNum = Math.max(maxNum, nums[r]);
                minNum = Math.min(minNum, nums[r]);
                curLen++;
                r++;
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }
    //優化後, 改編自python版本
    public static int longestSubarray_v2(int[] nums, int limit){
        int n = nums.length;
        int maxNum = 0, minNum = 0;
        var dqMin = new ArrayDeque<Integer>();
        var dqMax = new ArrayDeque<Integer>();
        int curLen = 0, maxLen = 0;
        for (int r = 0, l = 0; r < n; r++) {
            while (!dqMax.isEmpty() && nums[dqMax.getLast()] < nums[r])
                dqMax.pollLast();
            dqMax.addLast(r);
            while (!dqMin.isEmpty() && nums[dqMin.getLast()] > nums[r])
                dqMin.pollLast();
            dqMin.addLast(r);
            System.out.printf("dqMin = %s, dqMax = %s\n", dqMin, dqMax);
            while (nums[dqMax.getFirst()] - nums[dqMin.getFirst()] > limit){
                l++;
                if (dqMax.size() < n && dqMax.getFirst() < l)
                    dqMax.pollFirst();
                if (dqMin.size() < n && dqMin.getFirst() < l)
                    dqMin.pollFirst();
                System.out.printf("l = %d, r = %d\n", l, r);
            }
            curLen = r-l+1;
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }

    static void main(String[] args) {
        int[] nums = {1,5,6,7,8,10,6,5,6};
        int limit = 4;
        System.out.println("Ans = " + longestSubarray_v2(nums, limit));
    }
}
    /*
    思考軌跡:
    1438-longest-subarray\leetcode1438-footprint.md
    
    優化後的代碼更像是, leetcode官解 滑动窗口 + 单调队列
    https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/612688/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
    */