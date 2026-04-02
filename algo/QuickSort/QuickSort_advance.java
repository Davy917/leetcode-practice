import java.util.Arrays;

public class QuickSort_advance {
    static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }
    static void quickSort(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        int middle = partition(arr, start, end);
        quickSort(arr, start, middle - 1);
        quickSort(arr, middle + 1, end);
    }

    static int partition(int[] arr, int start, int end){
        int left = start;
        int right = end;
        while (left < right){
            //注意要先移右邊, 再移左邊, 有差別!!(請看註解, 有差的根因)
            while (left < right && arr[start] <= arr[right]){//注意大於小於符號不要寫反
                right--;
            }
            while (left < right && arr[start] >= arr[left]){//注意大於小於符號不要寫反
                left++;
            }
            swap(arr, left, right);
        }
        System.out.println("After swap" + Arrays.toString(arr));
        swap(arr, start, right);
        System.out.println("After partition" + Arrays.toString(arr));//debug
        return right;
    }
/*
有差的根因是：pivot 固定在 arr[start]，最後又做 swap(arr, start, right)。
這代表其實在用「right 當 pivot 最終落點」的分區策略，這類策略通常要先移 right 再移 left，
否則 left 可能先衝到交會點，讓 right 還沒找到正確位置。
 */
    static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 0, 3, 5};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
/*
代碼出處:
https://leetcode.cn/leetbook/read/illustration-of-algorithm/p57uhr/
 */