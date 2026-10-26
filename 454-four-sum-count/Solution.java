/*
完整思路看js
*/
import java.util.HashMap;
import java.util.Map;

class Solution454 {
    public int P0454_fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: nums1){
            for (int j: nums2){
                //設計哈希鍵
                int headSum = i + j;
                int count = map.getOrDefault(headSum, 0);
                map.put(headSum, count + 1);
            }
        }
        for (int i: nums3){
            for (int j: nums4){
                int tailSum = i + j;
                result += map.getOrDefault(-tailSum, 0);
            }
        }
        return result;
    }

    static void main(String[] args) {
        int[] nums1= {1, 2};
        int[] nums2= {-2, -1};
        int[] nums3= {-1, 2};
        int[] nums4= {0, 1};
        System.out.println(new Solution454().P0454_fourSumCount(nums1, nums2, nums3, nums4));
    }
}