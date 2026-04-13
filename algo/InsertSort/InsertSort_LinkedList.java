package InsertSort;

import java.util.Arrays;
import java.util.LinkedList;

public class InsertSort_LinkedList {
    public static void sort(LinkedList<Integer> arr){
        for (int index = 0; index < arr.size(); index++) {
            int visitor = index - 1;
            int cur_val = arr.get(index);
            while (visitor >= 0 && arr.get(visitor) > cur_val){
                arr.set(visitor + 1, arr.get(visitor));
                visitor -= 1;
            }
            arr.set(visitor + 1, cur_val);
        }
    }
    static void main(String[] args) {
        LinkedList<Integer> arr = new LinkedList<>(Arrays.asList(55, 80, 22, 60, 18, 90, 40, 5, 70, 30));
        sort(arr);
        System.out.println("Ans = " + arr);
    }
}