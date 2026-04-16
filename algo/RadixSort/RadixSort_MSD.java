import java.util.Arrays;

public class RadixSort_MSD {
    static void RadixSort(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int val : arr)
            if (Math.abs(val) > max)
                max = Math.abs(val);
        int max_digit_length = 0;
        while (max != 0){
            max /= 10;
            max_digit_length ++;
        }
        RadixSort_MSD(arr, 0, arr.length - 1, max_digit_length);
    }
    static void RadixSort_MSD(int[] arr, int start, int end, int position){
        int dev = (int)Math.pow(10, position - 1); // 注意要轉成(int)
        int[] counting = new int[19];
        for (int val : arr){
            int radix = val / dev + 9;
            counting[radix] += 1;
        }
        System.out.println(Arrays.toString(counting));

        counting[0]--;
        for (int i = 1; i < counting.length; i++){
            counting[i] += counting[i - 1];
        }
        System.out.println(Arrays.toString(counting));

        int[] result = new int[end - start];
        for (int index = end - start; index >= 0; index--){
            int radix = arr[index] / dev + 9;
        }
    }
    public static void main(String[] args) {
        int[] arr = {520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168};
        int[] arr2 = {520, 211, 438, 888, 7, 111, 985, 666, 996, 233, 168};
        RadixSort(arr);
    }
}
