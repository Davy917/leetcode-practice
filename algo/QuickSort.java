/**
 * pivot的選擇方式有三種:
 * 1. 選第一個元素
 * 2. 選最後一個元素
 * 3. 選區間內隨機一個元素
 */

import java.util.Arrays;

public class QuickSort {
    static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * quickSort中的等式是如何得出來的??
     * //將數組分區，並取得中間值的下標
     * int middle = partition(arr, start, end);
     *
     * //當左邊區域中至少有兩個數字時，對左邊區域快速排序
     * if (start != middle && start != middle - 1){
     *     quickSort(arr, start, middle - 1);
     * }
     *
     * //當右邊區域中至少有兩個數字時，對右邊區域快速排序
     * if (start != middle && start != middle + 1){
     *     quickSort(arr, middle + 1, end);
     * }
     *
     * 分析上面四種判斷條件:
     * start == middle 相當於 quickSort(arr, start, middle - 1)中的start == end + 1
     * start == middle - 1 相當於 quickSort(arr, start, middle - 1)中的start == end
     * middle == end 相當於 quickSort(arr, middle + 1, end)中的start == end + 1
     * middle = end - 1 相當於 quickSort(arr, middle + 1, emd)中的start == end
     *
     * 綜上, 我們可以將此邊界條件統一移動到 quickSort 函數之前:
     * public static void quickSort(int[] arr, int start, int end) {
     *     // 如果區域內的數字少於兩個,退出遞歸
     *     if (start == end || start == end + 1) return;
     *     // 將數組分區. 並獲得中間值下標
     *     int middle = partition(arr, start, end);
     *     // 對左邊區域進行快速排序
     *     quickSort(arr, start, middle - 1);
     *     // 對右邊區域進行快速排序
     *     quickSort(arr, middle + 1, end);
     * }
     *
     * 更進一步, 上文所說的middle >= start && middle <= end可以推出
     * 除了start == end || start == end + 1這兩個條件外, 其他情況下的start都小於end
     * 最後可以得出如下quickSort
     *
     * 我們需要知道,這裡的start >= end實際上只有兩種情況:
     * start == end:表明區域內只有一個數字
     * start ==end + 1:表明區域內一個數字也沒有
     */
    static void quickSort(int[] arr, int start, int end){
        System.out.println("in quickSort");
        //如果區域內的數字少於兩個, 退出遞歸
        if (start >= end){
            System.out.printf("start %s >= ", start);
            System.out.printf("end %s%n", end);
            return;
        }
        //將數組分區,並獲得中間值的下標
        int middle = partition(arr, start, end);
        //對左邊區域進行快速排序
        quickSort(arr, start, middle - 1);
        //對右邊區域進行快速排序
        quickSort(arr, middle + 1, end);
    }

    static int partition(int[] arr, int start, int end){
        System.out.println("in partition");
        int pivot = arr[start];
        int left = start + 1;
        int right = end;

        while (left < right){

            //debug
            System.out.println("pivot.val = " + arr[start] + " Index = " + start);
            System.out.println("left.val = " + arr[left] + " Index = " + left);
            System.out.println("right.val = " + arr[right] + " Index = " + right);

            //此迴圈的用意在於, 找到第一個大於pivot的數字
            while (left < right && arr[left] <= pivot){
                left++;
                System.out.println("pivot > left, " + "left++");
            }

            //拿這個數字跟right交換
            if (left != right){
                System.out.printf("find %s > pivot%n", arr[left]);
                exchange(arr, left, right);
                right--;
            }
        }

        if (left == right && arr[right] > pivot){
            System.out.println("left == right && right.val > pivot");
            right--;
        }
        if (right!= start){
            System.out.println("left == right");
            exchange(arr, start, right);
        }return right;

    }

    static void exchange(int[] arr, int i, int j){
        System.out.print("in exchange ");
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        System.out.println(Arrays.toString(arr));
    }

    static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        int[] arr2 = {5, 4, 7, 2, 1, 8, 6};
        quickSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}