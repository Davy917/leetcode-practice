//尾遞歸 + 隨機基準數
import java.util.Arrays;
import java.util.Random;

public class QuickSort_ultra {
    static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }
    static void quickSort(int[] arr, int start, int end){
        while (start < end){
            System.out.printf("Before partition start = %d end = %d%n", start, end);
            int pivot = partition(arr, start, end);
            //debug
            System.out.printf("After partition start = %d ", start);
            System.out.printf("end = %d, pivot = %d%n", end, pivot);
            System.out.println(Arrays.toString(arr));
            if (pivot - start < end - pivot){//pivot右邊的數較多
                System.out.println("if");//debug
                System.out.printf("quickSort %d ~ %d%n", start, pivot - 1);
                quickSort(arr, start, pivot - 1);//就排序左邊
                System.out.printf("[返回] quickSort %d ~ %d, start 更新: %d → %d%n", start, pivot - 1, start, pivot + 1);
                start = pivot + 1;
            }else {//pivot左邊的數較多, 或兩邊相等
                System.out.println("else");//debug
                System.out.printf("quickSort %d ~ %d%n", pivot + 1, end);
                quickSort(arr, pivot + 1, end);//就排序右邊
                System.out.printf("[返回] quickSort %d ~ %d, end 更新: %d → %d%n", pivot + 1, end, end, pivot - 1);
                end = pivot - 1;
            }
        }
    }
    /*
    quickSort Demo
    初始:
    4, 2, 7, 1, 6, 3, 5
    
    Before partition start = 0 end = 6
    After partition start = 0 end = 6, pivot = 3
    [1, 2, 3, 4, 6, 7, 5]
    else
    quickSort 4 ~ 6
    Before partition start = 4 end = 6
    After partition start = 4 end = 6, pivot = 5
    [1, 2, 3, 4, 5, 6, 7]
    else
    quickSort 6 ~ 6
    [返回] quickSort 6 ~ 6, end 更新: 6 → 4   ← level 1 (4~6) 的遞迴結束, while(4<4) 不成立, level 1 return
    [返回] quickSort 4 ~ 6, end 更新: 6 → 2   ← level 0 (0~6) 的遞迴結束, end 更新為 2, 繼續 while(0<2)
    Before partition start = 0 end = 2
    After partition start = 0 end = 2, pivot = 0
    [1, 2, 3, 4, 5, 6, 7]
    if
    quickSort 0 ~ -1
    [返回] quickSort 0 ~ -1, start 更新: 0 → 1
    Before partition start = 1 end = 2
    After partition start = 1 end = 2, pivot = 2
    [1, 2, 3, 4, 5, 6, 7]
    else
    quickSort 3 ~ 2
    [返回] quickSort 3 ~ 2, end 更新: 2 → 1
    [1, 2, 3, 4, 5, 6, 7]
    0.7519769519802502
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
