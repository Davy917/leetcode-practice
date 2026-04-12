package SelectionSort;

import java.util.Arrays;

class SelectionSort {
    int[] selectionSort(int[] arr) {
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.printf("As is %s%n", Arrays.toString(arr));
            minIndex = i;
            System.out.printf("min = %s%n", arr[minIndex]);
            for (int j = i + 1; j < arr.length; j++) {
                System.out.printf("min %s compare with", arr[minIndex]);
                System.out.printf("cur %s%n", arr[j]);
                if (arr[minIndex] > arr[j]) {
                    // 记录最小值的下标
                    System.out.println("find smaller val " + arr[j]);
                    minIndex = j;
                }
            }
            // 将最小元素交换至首位
            int temp = arr[i];
            arr[i] = arr[minIndex];//選擇排序法是不穩定排序法, 原因就是它是把值, 直接插入首端或尾端
            arr[minIndex] = temp;
            System.out.printf("To be %s%n", Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        SelectionSort t = new SelectionSort();
        int[] ans = t.selectionSort(new int[]{5, 8, 6, 3, 9, 1});
        System.out.println(Arrays.toString(ans));
    }
}