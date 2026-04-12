package BucketSort;
import java.util.Arrays;

public class BuckerSort_basic {
    static void BucketSort(int[] arr){
        if (arr.length == 0 || arr.length == 1)
            return;
        int max = arr[0];
        int min = arr[0];
        for (int val: arr){
            if (val > max)
                max = val;
            else if (val < min)
                min = val;
        }
    }
    static void main(String[] args) {
        int[] arr = {55, 80, 22, 60, 18, 90, 40, 5, 70, 30};
        BucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
