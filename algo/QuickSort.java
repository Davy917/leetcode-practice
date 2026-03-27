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
     */
    static void quickSort(int[] arr, int start, int end){

        //將數組分區，並取得中間值的下標
        int middle = partition(arr, start, end);

        //當左邊區域中至少有兩個數字時，對左邊區域快速排序
        if (start != middle && start != middle - 1){
            quickSort(arr, start, middle - 1);
        }

        //當右邊區域中至少有兩個數字時，對右邊區域快速排序
        if (start != middle && start != middle + 1){
            quickSort(arr, middle + 1, end);
        }
    }
    static int partition(int[] arr, int start, int end){
        //TODO
    }
}