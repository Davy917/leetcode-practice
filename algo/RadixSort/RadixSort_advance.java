package RadixSort;

import java.util.Arrays;

public class RadixSort_advance {

    static void RadixSort(int[] arr){
        int max_num = arr[0];
        for (int val: arr){
            if (Math.abs(val) > Math.abs(max_num)){
                max_num = Math.abs(val);
            }
        }
        int maxDigitLength = 0;
        while (max_num > 0){
            max_num /= 10;
            maxDigitLength += 1;
        }
        System.out.println(maxDigitLength);

        int dev = 1;
        for (int i = 0; i < maxDigitLength; i++) {
            int[] counting = new int[19];
            for (int val: arr){
                int radix = val / dev % 10 + 9;
                counting[radix] += 1;
            }
            System.out.println("counting = " + Arrays.toString(counting));
            //TODO

            dev *= 10;
        }
    }

    static void main(String[] args) {
        int[] arr = {520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168};
        RadixSort(arr);
    }
}
