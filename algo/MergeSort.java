import java.util.Arrays;

/*
只要开辟一个长度等同于两个数组长度之和的新数组，
并使用两个指针来遍历原有的两个数组，
不断将较小的数字添加到新数组中，
并移动对应的指针即可。

首先开辟了一个新数组 result，长度等同于 arr1 和 arr2 的长度之和
，然后使用 index1 记录 arr1 数组的下标，index2 记录 arr2 数组的下标。
再将两个数组中较小的值不断添加到 result 中。其中，result 的当前下标等同于 index1 和 index2 之和。
//result 的当前下标等同于 index1 和 index2 之和
 */
public class MergeSort {
    static int[] merge(int[] arr1, int[] arr2){
        //在 Java 中，要建立固定長度的陣列物件，本質上都需要配置新物件，所以離不開 new（直接或隱含）。
        int[] result = new int[arr1.length + arr2.length];
        int index1 = 0, index2 = 0;
        while (index1 < arr1.length && index2 < arr2.length){
            if (arr1[index1] <= arr2[index2]){
                //result[index1 + index2] = arr1[index1++];等價
                result[index1 + index2] = arr1[index1];
                index1++;
            }
            else {
                //result[index1 + index2] = arr2[index2++];等價
                result[index1 + index2] = arr2[index2];
                index2++;
            }
        }

        while (index1 < arr1.length){
            result[index1 + index2] = arr1[index1];
            index1++;
        }
        while (index2 < arr2.length){
            result[index1 + index2] = arr2[index2];
            index2++;
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void mergeSort(int[] arr){
        //先寫一個預防邊界case
        if (arr.length == 0)
            return;

        int[] result = mergeSort(arr, 0, arr.length - 1);
        System.out.println("test" + Arrays.toString(result));

        for (int i = 0; i < result.length; i++)
            arr[i] = result[i];
        //System.arraycopy(result, 0, arr, 0, result.length);
    }
    //2, 3, 5, 4, 6, 1 To understand
    private static int[] mergeSort(int[] arr, int start, int end){
        if (start == end) {
            /*
            代表建立一個長度為 1 的陣列，裡面唯一的元素是 arr[start]。
            例如如果 arr[start] 是 6，實際回傳的是 int[]，內容為 [6]。
            在你的遞迴中，這是「子問題只剩一個元素」時的基底回傳值。
             */
            System.out.println("arr[start] = " + arr[start]);
            return new int[]{arr[start]};
        }
        int middle = (start + end)/2;
        //拆分左邊
        int[] left = mergeSort(arr, start, middle);//不是middle - 1?
        //拆分右邊
        int[] right = mergeSort(arr, middle + 1, end);
        //合併兩邊
        return merge(left, right);//尾遞歸??
    }

    static void main(String[] args) {
        int[] arr = {2, 6, 1, 3, 5, 4};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
