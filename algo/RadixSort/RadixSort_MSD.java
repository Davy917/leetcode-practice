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

        int[] counting = new int[19];
        int dev = (int)Math.pow(10, position - 1); // 注意要轉成(int)

        for (int index = start; index <= end; index++){ //注意不要寫成 for (int val : arr) 這種迴圈
            int radix = arr[index] / dev % 10 + 9;
            counting[radix]++;
        }

        counting[0]--;
        for (int i = 1; i < counting.length; i++)
            counting[i] += counting[i - 1];
        System.out.println("counting after prefix = " + Arrays.toString(counting));

        int[] countingCopy = new int[counting.length];
        System.arraycopy(counting, 0, countingCopy, 0, counting.length);


        int[] result = new int[end - start + 1];
        for (int index = end; index >= start; index--) { //注意, 迴圈 "由後往前" 遍歷, 不要寫反
            int radix = arr[index] / dev % 10 + 9;
            result[counting[radix]] = arr[index];
            counting[radix]--;
        }
        System.out.println("result = " + Arrays.toString(result));

        //記得result 要寫回 arr
        //result 是「局部陣列」，長度是 end - start + 1，它的合法起點必須是 0，不是 start。
        System.arraycopy(result, 0, arr, start, end - start + 1);

        for (int i = 0; i < countingCopy.length; i++){
            int bucketLocalStart = (i == 0) ? 0 : countingCopy[i - 1] + 1;
            int bucketLocalEnd = countingCopy[i];

            int bucketStart = start + bucketLocalStart;
            int bucketEnd = start + bucketLocalEnd;

            if (bucketStart < bucketEnd)
                RadixSort_MSD(arr, bucketStart, bucketEnd, position - 1);
        }
    }
    static void main(String[] args) {
        int[] arr = {520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168};
        int[] arr2 = {27, 53, 35, 52, 51, 32, 36, 23, 58};
        RadixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

/*
bucket_local_start = 0 if i == 0 else counting_copy[i-1] + 1
 */