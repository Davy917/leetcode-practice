import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution349 {
    public int[] P0349_intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int n : nums1){
            nums1Set.add(n);
        }
        for (int n : nums2){
            if (nums1Set.contains(n)){
                intersect.add(n);
                nums1Set.remove(n);
            }
        }

        int[] result = new int[intersect.size()];//??
        int counter = 0;
        for (Integer i : intersect){
            result[counter++] = i;
        }
        return result;
    }
    static void main(String[] args) {
        Solution349 sol = new Solution349();
        int[] numbs1 = {4,9,5};
        int[] numbs2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(sol.P0349_intersection(numbs1, numbs2)));
    }
}