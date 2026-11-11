import java.util.*;
class Solution658 {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length;
        while (l+1 < r){
            int m = (l+r)/2;
            if (arr[m] > x)
                r = m;
            else
                l = m;
        }
        int a = l, b = l+1;
        for (int i = 0; i < k; i++) {
            if (a > -1 && b < arr.length)
                if (Math.abs(arr[a]-x) < Math.abs(arr[b]-x) || ((Math.abs(arr[a]-x) == Math.abs(arr[b]-x)) && arr[a] < arr[b]))
                    a--;
                else
                    b++;
            else if (a > -1)
                a--;
            else if (b < arr.length)
                b++;
        }
        arr = Arrays.copyOfRange(arr, a+1, b);
        var res = new ArrayList<Integer>();
        for (int num: arr)
            res.add(num);
        return res;
    }

    static void main(String[] args) {
        int[] arr = {3};
        System.out.println(findClosestElements(arr, 1, 3));
    }
}
/*
自己寫的
思路:
1. 先找到k, 任何一個k都可以(這邊是找最後一個k)
2. 從此k的下標出發, 同時前後搜索, 搜索到出界就停下
3. 指針 [a+1, b) 停留的位置, 就是答案
4. 把原arr做成切片arr[a+1, b)
5. 把切片arr轉成list, 並return list

嘗試一次完成一件事, 並儘量簡化代碼

補充:
[a+1, b) 代表左閉右開, 左邊界包含a+1, 右邊界不包含b
a+1是因為 for (int i = 0; i < k; i++) 會把a多-1, 所以要 a+1 才是真正的起始位置。
*/