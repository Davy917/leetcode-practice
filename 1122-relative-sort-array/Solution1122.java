import java.util.Arrays;

public class Solution1122 {
    public static int[] P1122_relativeSortArray(int[] arr1, int[] arr2){
        if (arr1 == null || arr2 == null)
            return null;
        int max = arr2[0];
        int min = arr2[0];
        for (int val: arr2){
            if (val > max)
                max = val;
            else if (val < min)
                min = val;
        }
        int trueRange = max - min + 1;
        if (trueRange <= 1)
            return null;
        int[] counting = new int[trueRange];

        //TODO
        int[] result = new int[arr1.length];
        int index = 0;
        for (int val: arr1){
            result[]
            index++;
        }
        return arr1;
    }
    static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        P1122_relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(arr1));
    }
}
/*

 */