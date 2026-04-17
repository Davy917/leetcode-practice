//TODO
package RadixSort;

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
        if (start == end || position == 0)
            return;

        int dev = (int)Math.pow(10, position - 1); // 注意要轉成(int)
        int[] counting = new int[19];
        for (int val : arr){
            int radix = val / dev + 9;
            counting[radix] ++;
        }

        counting[0]--;
        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];
        System.out.println("counting = " + Arrays.toString(counting));

        int[] countingCopy = new int[counting.length];
        System.arraycopy(counting, 0, countingCopy, 0, counting.length);

        int[] result = new int[end - start + 1];
        for (int index = 0; index < end - start + 1; index++){
            int radix = arr[index] / dev + 9;
            result[counting[radix]] = arr[index];
            counting[radix]--;
        }


        for (int i = 0; i < countingCopy.length; i++){
            int bucketLocalStart = (i == 0) ? 0 : countingCopy[i - 1] + 1;
            int bucketLocalEnd = countingCopy[i];
            int bucketStart = start + bucketLocalStart;
            int bucketEnd = start + bucketLocalEnd;
            if (bucketStart < bucketEnd)
                RadixSort_MSD(a, bucketStart, bucketEnd, position - 1);
        }
        System.out.println(Arrays.toString(result));
    }
    public static void main(String[] args) {
        int[] arr = {520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168};
        int[] arr2 = {27, 53, 35, 52, 51, 32, 36, 23, 58};
        RadixSort(arr2);
    }
}

/*
bucket_local_start = 0 if i == 0 else counting_copy[i-1] + 1
 */