package BucketSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import InsertSort.*;

public class BucketSort_LinkedList {
    private static final int bucketAmount = 10;
    static void BucketSort(int[] arr){
        if (arr == null)
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
        if (trueRange == 0)
            return;
        System.out.println(trueRange);

        int gap = trueRange / (bucketAmount - 1);//注意 - 1
        HashMap<Integer, LinkedList<Integer>> buckets = new HashMap<>();

        //裝桶
        for (int val: arr){
            int index = (val - min)/ gap;
            if (!buckets.containsKey(index))
                buckets.put(index, new LinkedList<>());
            buckets.get(index).add(val);
        }

        System.out.println(buckets);
        int index = 0;
        for (int i = 0; i < bucketAmount; i++){
            if (buckets.get(i) == null)
                continue;
            InsertSort_LinkedList.sort(buckets.get(i));

            for (int val : buckets.get(i))
                arr[index++] = val;
        }
    }

    static void main(String[] args) {
        int[] arr = {55, 80, 22, 60, 18, 90, 40, 5, 70, 30};
        BucketSort(arr);
        System.out.println("Ans = " + Arrays.toString(arr));
    }
}
/*
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/phtz1j/
 */