import java.util.Arrays;

public class Solution1122 {
    public static int[] P1122_relativeSortArray(int[] arr1, int[] arr2){
        //找最大值
        int max = 0;
        for (int val : arr1)
            max = Math.max(max, val);

        //統計arr1中的元素出現過幾次
        int[] counting = new int[max + 1];
        for (int val : arr1){
            ++counting[val]; //這種情境下也可以寫成counting[val]++;
        }
        System.out.println(Arrays.toString(counting));

        int[] result = new int[arr1.length];
        int index = 0;
        for (int val : arr2){
            for (int i = 0; i < counting[val]; ++i){
                result[index] = val;
                index++;
            }
            counting[val] = 0;
        }
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(counting));

        for (int val = 0; val <= max; ++val)
            for (int i = 0; i < counting[val]; ++i)
                result[index++] = val;

        System.arraycopy(result, 0, arr1, 0, arr1.length);
        return result;
    }
    static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        P1122_relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(arr1));
    }
}
/*
差異只在「有沒有把運算結果再拿去使用」時才會出現：
++counting[val]：先加 1，再回傳新值。
counting[val]++：先回傳舊值，再加 1。
 */