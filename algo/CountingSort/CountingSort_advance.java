import java.util.Arrays;
public class CountingSort_advance {
    void CountingSort(int[] arr){
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            else if ((arr[i] < min))
                min = arr[i];
        }
        int total_range = max - min + 1;
        int[] counting = new int[total_range];
        for (int element: arr){
            counting[element - min] ++;
        }
        System.out.println(Arrays.toString(counting));
        int preSum = 0;
        for (int i = 0; i < total_range; i++) {
            int temp = counting[i];
            counting[i] = preSum;
            preSum += temp;
        }
        System.out.println(Arrays.toString(counting));
        int[] result = new int[arr.length];
        for (int element: arr){
            int position = counting[element - min];//element - min 才能映射出正確位置, 可以從上個迴圈推得
            result[position] = element;
            counting[element - min]++;
        }
        System.arraycopy(result, 0,  arr, 0,  result.length);
    }
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 2, 6, 4};
        CountingSort_advance Sort = new CountingSort_advance();
        Sort.CountingSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
