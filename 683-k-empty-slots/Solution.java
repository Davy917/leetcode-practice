import java.util.Arrays;
import java.util.TreeSet;

//官方解答
class Solution683 {
    static int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        TreeSet<Integer> active = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int x = bulbs[i];
            Integer x_pre = active.floor(x);
            if (x_pre != null && Math.abs(x_pre-x)-1 == k)
                return i+1;
            Integer x_next = active.ceiling(x);
            if (x_next != null && Math.abs(x_next-x)-1 == k)
                return i+1;
            System.out.printf("x = %d, x_pre = %d, x_next = %d\n", x, x_pre, x_next);
            active.add(x);
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] bulbs = {1,5,2,4,3};
        int k =1;
        System.out.println("Ans = " + kEmptySlots(bulbs, k));
    }
}

/*
官解:
https://leetcode.cn/problems/k-empty-slots/solutions/2478087/k-ge-guan-bi-de-deng-pao-by-leetcode-sol-2sqw/
補充TreeSet常用方法
lower(x) 返回小於 x 的最大元素
floor(x) 返回小於或等於 x 的最大元素
ceiling(x) 返回大於或等於 x 的最小元素
higher(x) 返回大於 x 的最小元素
 */