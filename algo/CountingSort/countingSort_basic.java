import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CountingSort_basic {
    public static void countingSort(int[] arr) {
        int[] counting = new int[9];
        HashMap<Integer, Queue<Integer>> record = new HashMap<>();
        for (int value : arr){
            counting[value - 1]++;
            record.putIfAbsent(value - 1, new LinkedList<>());
            record.get(value - 1).add(value);
        }
        System.out.println("record = " + record);
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (counting[i] > 0){
                arr[index] = record.get(i).remove();
                System.out.println(record); //打印出來, 會清晰很多
                index++;
                counting[i]--;
            }
        }
        System.out.println("arr = " + Arrays.toString(arr));
    }
    public static void main(String[] args) {
        int[] arr = {5, 7, 3, 1, 6, 8, 9, 4, 7};
        countingSort(arr);
    }
}

/*
FAQ
record在宣告時是寫成
    HashMap<Integer, Queue<Integer>> record = new HashMap<>();
後面卻寫了
    record.putIfAbsent(value - 1, new LinkedList<>());
LinkedList跟 Queue不一樣但是為什麼不會報錯
 */
