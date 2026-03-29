//尾遞歸 + 隨機基準數
import java.util.Arrays;
import java.util.Random;

public class QuickSort_ultra {
    static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }
    static void quickSort(int[] arr, int start, int end){
        while (start < end){
            int pivot = partition(arr, start, end);
            System.out.printf("After partition start = %d ", start);
            System.out.printf("end = %d, pivot = %d%n", end, pivot);
            if (pivot - start < end - pivot){//pivot右邊的數較多
                quickSort(arr, start, pivot - 1);//就排序左邊
                start = pivot + 1;
            }else {//pivot左邊的數較多, 或兩邊相等
                quickSort(arr, pivot + 1, end);//就排序右邊
                end = pivot - 1;
            }
        }
    }
    /*
    quickSort Demo
    初始:
    4, 2, 7, 1, 6, 3, 5
    
    start = 0 end = 6, pivot = 2
    1, 2, 3, 4, 6, 7, 5

    start = 0 end = 1, pivot = 1
    2, 1, 3, 4, 6, 7, 5
    
    走進else
    end 變 0
    最外層while (start < end)不成立,
    整個排序就停在2, 1, 3, 4, 6, 7, 5
     */


    static int partition(int[] arr, int start, int end){
        int pivot = (int)(start + Math.random() * (end - start + 1));
        //等價於 int pivot = ThreadLocalRandom.current().nextInt(start, end + 1); <---copilot推薦寫法
        swap(arr, start, pivot);
        int left = start;
        int right = end;
        while (left < right){
            while (left < right && arr[right] >= arr[start]){
                right--;
            }
            while (left < right && arr[left] <= arr[start]){
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, start, right);
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
        System.out.println(Math.random());
    }
}

/*
先說明 Math.random() 會回傳一個包含 0，但不包含 1 的double, 實際打印就可以看到
用具體例子說明:
int pivot = (int)(start + Math.random() * (end - start + 1));

當start = 3, end = 7
end - start + 1 = 5
Math.random() * 5 是 0.0 ~ 4.9999
start + Math.random() * 5 區間變成 3.0 ~ 7.9999
(int)後只可能是3, 4, 5, 6, 7
 */

/*
代碼出處:
https://leetcode.cn/leetbook/read/illustration-of-algorithm/p57uhr/
 */
