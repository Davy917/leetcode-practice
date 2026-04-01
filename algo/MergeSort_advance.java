import java.util.Arrays;
//空間複雜度優化版
public class MergeSort_advance {
    public static void mergeSort(int[] arr){
        if (arr.length == 0) return;
        int[] result = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, result);
    }
    private static void mergeSort(int[] arr, int start, int end, int[] result){
        if (start == end) return;
        int middle = (start + end) / 2;

        System.out.println("拆分左邊");
        mergeSort(arr, start, middle, result);

        System.out.println("拆分右邊");
        mergeSort(arr, middle + 1, end, result);

        //決定要用一般版本還是simplift版本
        //merge_simplify(arr, start, end, result);
        merge(arr, start, end, result);
    }
    /*
    在这份代码中，我们统一使用 result 数组作为递归过程中的临时数组，所以merge 函数接收的参数不再是两个数组，而是 result 数组中需要合并的两个数组的首尾下标。
    根据首尾下标可以分别计算出两个有序数组的首尾下标 start1、end1、start2、end2，之后的过程就和之前合并两个有序数组的代码类似了。
     */
    public static void merge(int[] arr, int start, int end, int[] result){
        int middle = (start + end)/2;
        //數組1的首尾位置
        int start1 = start;
        int end1 = middle;
        //數組2的首尾位置
        int start2 = middle+1;
        int end2 = end;
        //用來遍歷數組的指針
        int index1 = start1;
        int index2 = start2;
        //結果數組的指針
        int resultIndex = start1;
        while (index1 <= end1 && index2 <= end2){
            if (arr[index1] <= arr[index2]){
                result[resultIndex] = arr[index1];
                resultIndex++;
                index1++;
            }
            else {
                result[resultIndex] = arr[index2];
                resultIndex++;
                index2++;
            }
        }
        while (index1 <= end1){
            result[resultIndex] = arr[index1];
            resultIndex++;
            index1++;
        }
        while (index2 <= end2){
            result[resultIndex] = arr[index2];
            resultIndex++;
            index2++;
        }
        if (end + 1 >= 0) System.arraycopy(result, 0, arr, 0, end + 1);
    }
    //merge方法精簡版
    public static void merge_simplify(int[] arr, int start, int end, int[] result){
        /*
        我们可以去掉一些不会改变的临时变量。
        比如 start1 始终等于 start，end2 始终等于 end，end1 始终等于 middle。
        并且分析可知，resultIndex 的值始终等于 start 加上 index1 和 index2 移动的距离。
         */
        int middle = (start + end)/2;
        int start2 = middle + 1;
        int index1 = start;
        int index2 = start2;
        while (index1 <= middle && index2 <= end){
            if (arr[index1] <= arr[index2]){
                result[index1 + index2 - start2] = arr[index1];//為什麼不是result[index1 + index2]??
                index1++;
            }
            else {
                result[index1 + index2 - start2] = arr[index2];
                index2++;
            }
        }
        while (index1 <= middle){
            result[index1 + index2 - start2] = arr[index1];
            index1++;
        }
        while (index2 <= end){
            result[index1 + index2 - start2] = arr[index1];
            index2++;
        }
        while (start <= end){
            arr[start] = result[start];
            start++;
        }
    }

    static void main(String[] args) {
        int[] arr = {2, 6, 1, 3, 5, 4};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}