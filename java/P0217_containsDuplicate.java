import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution217 {
    public boolean P0217_containsDuplicate(int[] nums) {
        if (nums.length == 0){return false;}
        Map<Integer, Integer> map = new HashMap<>();
        //先把map塞值
        for (int i=0; i< nums.length; i++){
            map.put(nums[i], i);
        }

        //塞完後發現兩邊size不一致, 代表有重複的數
        return map.size() != nums.length;

        /*
        if (map.size() != nums.length){return true;}
            return false;
         */
    }
    static void main(String[] args) {
        int[] nums1 = {1,2,3,4};
        System.out.println(new Solution217().P0217_containsDuplicate(nums1));
    }
}

/*
给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。

示例 1：

输入：nums = [1,2,3,1]

输出：true

解释：

元素 1 在下标 0 和 3 出现。

示例 2：

输入：nums = [1,2,3,4]

输出：false

解释：

所有元素都不同。

示例 3：

输入：nums = [1,1,1,3,3,4,3,2,4,2]

输出：true
 */