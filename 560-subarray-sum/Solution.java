//暴力解，hashmap優化解見python版本
class Solution560{
    public int P0560_subarraySum(int[] nums, int k){
        int result = 0;

        for (int i=0; i<nums.length; i++){
            int k1 = k;
            for (int j=i; j<nums.length; j++){
                k1-=nums[j];
                if( k1 == 0){result++;}
            }
        }
        return result;
    }

    static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        Solution560 sol = new Solution560();
        System.out.println(sol.P0560_subarraySum(nums, k));
    }
}

/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
输入：nums = [1,1,1], k = 2
输出：2

输入：nums = [1,2,3], k = 3
输出：2
 */