import java.util.Arrays;
class RadixSort_basic {
    static void RadixSort_basic(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int val : arr)
            if (Math.abs(val) > max)
                max = Math.abs(val);

        int max_digit_length = 0;
        while (max != 0){
            max /= 10;
            max_digit_length ++;
        }

        int dev = 1;
        while (dev < Math.pow(10, max_digit_length)){
            int[] radixarr = new int[arr.length];
            int[] counting = new int[19];
            for (int index = 0; index < arr.length; index++){
                radixarr[index] = arr[index] / dev % 10 + 9;
                counting[radixarr[index]]++;
            }
            System.out.println("radixarr = " + Arrays.toString(radixarr));
            int preSum = 0;
            for (int i = 0; i < counting.length; i++){
                int temp = counting[i];
                counting[i] = preSum;
                preSum += temp;
            }
            System.out.println("counting = " + Arrays.toString(counting));
            int[] result = new int[radixarr.length];
            int index = 0;
            for (int val : radixarr){
                result[counting[val]] = arr[index];
                counting[val]++;
                index++;
            }
            System.out.println("arr = " + Arrays.toString(result));
            dev *= 10;
        }
    }
    public static void main(String[] args) {
        int[] arr = {520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168};
        int[] arr2 = {520, 211, 438, 888, 7, 111, 985, 666, 996, 233, 168};
        RadixSort_basic(arr);
    }
}
