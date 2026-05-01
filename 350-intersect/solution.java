import java.util.Arrays;
import java.util.HashMap;
class Solution350 {
    public static int[] P350_intersect(int[] nums1, int[] nums2) {
        var map = new HashMap<Integer, Integer>();
        for (int val : nums1)
            map.put(val, map.getOrDefault(val, 0) + 1);

        int index = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        for (int val: nums2){
            if (map.containsKey(val) && map.get(val) > 0){
                result[index++] = val;
                map.put(val, map.get(val) - 1);
            }
        }
        return Arrays.copyOf(result, index);
    }
    void main(String[] args) {
        int[] nums1 = {1, 1, 2, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("Ans = " + Arrays.toString(P350_intersect(nums1, nums2)));
    }
}