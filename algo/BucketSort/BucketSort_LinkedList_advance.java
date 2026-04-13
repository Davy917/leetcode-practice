package BucketSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import InsertSort.*;

public class BucketSort_LinkedList_advance {
    private static final int bucketAmount = 10;
    static void BucketSort(int[] arr) {
        if (arr == null)
            return;
        int max = arr[0];
        int min = arr[0];
        for (int val : arr) {
            if (val > max)
                max = val;
            else if (val < min)
                min = val;
        }
        int trueRange = max - min;
        if (trueRange == 0)
            return;

        int gap = trueRange / (bucketAmount - 1);//注意 - 1
        HashMap<Integer, Queue<Integer>> buckets = new HashMap<>(); //為什麼是Queue, 見下方註解

        for (int val: arr){
            int Index = (val - min)/ gap;//注意 - min
            if (!buckets.containsKey(Index))
                buckets.put(Index, new LinkedList<>());
            buckets.get(Index).add(val);
        }

        int index = 0;
        for (int i = 0; i < bucketAmount; i++) {
            if (buckets.get(i) == null)
                continue;
            int[] arrInBucket = buckets.get(i).stream().mapToInt(Integer::intValue).toArray();
            InsertSort.InsertSort_basic(arrInBucket);
            System.arraycopy(arrInBucket, 0, arr, index, arrInBucket.length);
            index += arrInBucket.length;
        }
    }
    static void main(String[] args) {
        int[] arr = {55, 80, 22, 60, 18, 90, 40, 5, 70, 30};
        BucketSort(arr);
        System.out.println("Ans = " + Arrays.toString(arr));
    }
}
/*
HashMap<Integer, Queue<Integer>> buckets = new HashMap<>();
//為什麼是Queue??

Java 的「介面多型 + 泛型型別推斷」：
buckets 的值型別是 Queue<Integer>。
LinkedList 有實作 Queue 介面，所以 new LinkedList<>() 可以被當成 Queue<Integer> 使用。
put 的第二個參數目標型別已經是 Queue<Integer>，因此 <> 會自動推斷成 LinkedList<Integer>。


把Queue換成LinkedList其實也不影響整體的運作, 為什麼要寫成Queue ??

改成 LinkedList 也能正常跑，但寫成 Queue<Integer> 有幾個設計上的好處：
    1. 面向介面程式設計
        變數型別用 Queue，表示「我只需要佇列行為」，不綁死具體實作。
    2. 更容易替換實作
        之後可把 new LinkedList<>() 換成 new ArrayDeque<>() 等，不用改其他使用端程式碼。
    3. 限制可用 API，降低誤用
        若宣告成 LinkedList，會暴露很多非佇列方法（例如隨機插入等）；宣告成 Queue 可避免用到不該用的操作。
    4. 可讀性更好
        讀程式的人會直接知道這裡的資料結構意圖是「桶內先進先出容器」，而不是一般鏈結串列操作。
        所以這是可維護性與抽象層次的選擇，不是功能是否能跑的問題。

代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/phtz1j/
QUEUE的用法:
https://leetcode.cn/leetbook/read/on-java-zhong-wen-ban-ji-chu-juan/lw84he/
 */