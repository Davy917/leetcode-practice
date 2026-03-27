import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Solution215 {
    private int quickSelect(List<Integer> nums, int k){
        Random rand = new Random();
        int pivot = nums.get(rand.nextInt(nums.size()));//pivot是一個哨兵，比它小的放smaller, 比它大的放equal
        List<Integer> bigger = new ArrayList<>();
        List<Integer> smaller = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        for (Integer i : nums){
            if (i < pivot)
                smaller.add(i);
            else if (i > pivot)
                bigger.add(i);
            else
                equal.add(i);
        }
        //debug
        System.out.printf("pivot = %s ", pivot);
        System.out.printf("smaller = %s ", smaller);
        System.out.printf("equal = %s ", equal);
        System.out.printf("bigger = %s%n", bigger);

        //如果整個數組是排序過的, 那麼k就是從後往前，第幾個數字

        //k在bigger中
        if (k <= nums.size())
            return quickSelect(bigger, k);
        //k在smaller中
        else if (k < nums.size() - smaller.size())
            return quickSelect(smaller, k - nums.size() + smaller.size());//why??
        //k在equal中
        return pivot;
    }
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (Integer i : nums){
            list.add(i);
        }
        return quickSelect(list, k);
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public int findKthLargest_v2(int[] nums, int k) {
        for (int i = 0; i < k; i++){
            int maxIndex = i;
            for (int j = i + 1; j < nums.length; j++){
                if (nums[maxIndex] < nums[j]){
                    System.out.println("maxIndex = " + maxIndex);
                    maxIndex = j;
                }
            }
            swap(nums, i, maxIndex);
        }
        return nums[k-1];
    }

    static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Solution215 sol = new Solution215();

        //快速排序測試
        //sol.findKthLargest(nums, k);
        //sol.findKthLargest_v2(nums, k);

        //堆排序測試
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(nums, nums.length);
        System.out.println(Arrays.toString(nums));
    }
}