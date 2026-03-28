import java.util.Arrays;

public class QuickSort {
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
        int pivot = arr[start];
        int left = start + 1;
        int right = end;

        while (left < right){
            while (left < right && arr[left] <= pivot){//注意是 arr[left] <= pivot, 原本寫成pivot > arr[left]
                left++;
            }
            if (left != right){
                swap(arr, left, right);
                right--;
            }
        }
        if (left == right && arr[right] > pivot){
            right--;
        }
        if (right != start){//注意
            swap(arr, start, right);
        }
        return right;
    }
    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 6, 3, 5};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

/*
p  l              r
4, 2, 7, 1, 6, 3, 5

p     l           r
4, 2, 7, 1, 6, 3, 5

p     l        r
4, 2, 5, 1, 6, 3, 7

      l     r
4, 2, 3, 1, 6, 5, 7

         l  r
4, 2, 3, 1, 6, 5, 7

            l
            r
4, 2, 3, 1, 6, 5, 7

p  l  r
1, 2, 3, 4, 6, 5, 7

   l
p  r
1, 3, 2, 4, 6, 5, 7

start = 0, end = 0
 */