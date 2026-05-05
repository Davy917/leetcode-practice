import java.util.Arrays;

public class RadixSort_advance {
    static int[] RadixSort_advance(int[] arr){
        int maxNum = Math.abs(arr[0]);
        for (int value : arr)
            maxNum = Math.max(Math.abs(maxNum), Math.abs(value));
        int maxDigitLen = 0;
        while (maxNum > 0){
            maxNum /= 10;
            maxDigitLen++;
        }
        int dev = 1;
        int[] result = new int[arr.length];
        while (dev < Math.pow(10, maxDigitLen)){
            int[] counting = new int[19];
            for (int value : arr)
                counting[value / dev % 10 + 9]++;
            System.out.println("counting before prefix" + Arrays.toString(counting));
            int preSum = 0;
            for (int index = 0; index < counting.length; index++){
                int temp = counting[index];
                counting[index] = preSum;
                preSum += temp;
            }
            System.out.println("counting after prefix" + Arrays.toString(counting));
            for (int value : arr){
                int radix = value / dev % 10 + 9;
                result[counting[radix]] = value;
                counting[radix]++;
            }
            arr = Arrays.copyOf(result, result.length);
            dev *= 10;
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = {520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168};
        int[] arr2 = {520, 211, 438, 888, 7, 111, 985, 666, 996, 233, 168};
        System.out.println("Ans = " + Arrays.toString(RadixSort_advance(arr)));
    }
}
