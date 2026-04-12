package BucketSort;
import java.util.Arrays;
import InsertSort.InsertSort;

public class BucketSort_basic {
    private static final int bucketAmount = 10;
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
        int trueRange = max - min;
        if (trueRange == 0) //arr裡面都是一樣的數字, 不用排
            return;

        int gap = trueRange / (bucketAmount - 1);//注意 - 1
        int[][] buckets = new int[bucketAmount][arr.length];
        int[] bucketLength = new int[bucketAmount];

        //裝入桶中
        for (int val: arr){
            int index = (val - min) / gap; //注意, - min
            buckets[index][bucketLength[index]] = val;
            bucketLength[index]++;
            System.out.printf("bucket %d = %s%n",index, Arrays.toString(buckets[index]));
        }
        int index = 0;
        for (int i = 0; i < bucketAmount; i++) {
            if (bucketLength[i] == 0)
                continue;
            int[] arrayInBucket = Arrays.copyOf(buckets[i], bucketLength[i]);
            InsertSort.InsertSort_basic(arrayInBucket);
            System.out.println("arrayInBucket = " + Arrays.toString(arrayInBucket));
            System.arraycopy(arrayInBucket, 0, arr, index, bucketLength[i]); //也可以使用copyOfRange, 見下方二擇一
            index += bucketLength[i];
        }
    }
    static void main(String[] args) {
        int[] arr = {55, 80, 22, 60, 18, 90, 40, 5, 70, 30};
        BucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
    /*
        二擇一
        InsertSort.InsertSort_basic(Arrays.copyOfRange(buckets[i], 0, bucketLength[i]));
        InsertSort.InsertSort_basic(Arrays.copyOf(buckets[i], bucketLength[i]));
     */