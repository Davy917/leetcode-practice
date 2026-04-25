class Solution169 {
    public int majorityElement(int[] nums) {
        QuickSort.quickSort(nums);
        int count = 1, cur = 1, prev = 0, len = nums.length;

        while (count <= len / 2){
            if (cur > nums.length - 1){
                return nums[prev];
            }
            if (nums[cur] != nums[prev]){
                count = 0;
            }
            cur++;
            prev++;
            count++;
            System.out.println("prev = " + prev + " cur = " + cur + " count = " + count);
        }
        return nums[prev];

        /*
        给定一个大小为 n 的数组nums ，返回其中的多数元素。
        多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
        你可以假设数组是非空的，并且给定的数组总是存在多数元素。
         */
    }
    static void main(String[] args) {
        int[] nums = {3, 2, 3};
        Solution169 sol = new Solution169();
        int result = sol.majorityElement(nums);
        System.out.println("result = " + result);
    }
}
/*
1,1,1,2,2,2,2
 */