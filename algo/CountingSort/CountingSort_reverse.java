package CountingSort;

import java.util.Arrays;
public class CountingSort_reverse {
    void CountingSort(int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
            else if (arr[i] > max)
                max = arr[i];
        }

        final int true_range = max - min + 1;
        int[] counting = new int[true_range]; //注意, 要記得counting的大小是用true_range

        for (int element : arr) {
            counting[element - min] ++;
        }
        System.out.println(Arrays.toString(counting));

        for (int index = 1; index < true_range; index++)//注意是true_range, 而不是counting.length
            counting[index] += counting[index - 1];
        for (int index = 0; index < true_range; index++)
            counting[index]--;
        System.out.println(Arrays.toString(counting));

        //寫第二次還是寫不出來
        int[] result = new int[arr.length];
        for (int index = arr.length - 1; index >= 0; index--){
            int val = arr[index];
            result[counting[val - min]] = val; //val - min 是在 counting 中的偏移位置, 可以從上兩個迴圈推得
            counting[val - min]--;
        }
        System.arraycopy(result, 0, arr, 0, result.length);
    }
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 2, 6, 4};
        CountingSort_reverse Sort = new CountingSort_reverse();
        Sort.CountingSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
