//官方解答
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

class Solution239 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int r = 0; r < k; r++) //預處理第一個視窗的單調遞減隊列
        {
            while (!deque.isEmpty() && nums[r] >= nums[deque.peekLast()]) //deque不為空 && 比大小
            {
                deque.pollLast(); //刪除
            }
            deque.offerLast(r); //添加到隊尾
            System.out.println(deque);
        }

        int n = nums.length;
        int[] ans = new int[n-k+1];
        ans[0] = nums[deque.getFirst()];
        for (int r = k; r < n; r++) {
            while (!deque.isEmpty() && nums[r] >= nums[deque.peekLast()])
            {
                deque.pollLast();
            }
            deque.offerLast(r);
            while (deque.getFirst() <= r-k)
            {
                deque.pollFirst();
            }
            System.out.println(deque);
            ans[r-k+1] = nums[deque.getFirst()];
        }
        return ans;
    }
    static void main(String[] args) {
        int[] nums = {7, -9, 6, 2, -7, 6};
        int k = 3;
        System.out.println("Ans = " + Arrays.toString(maxSlidingWindow(nums, k)));
    }
}

/*
隊首想像成deque的左邊
隊尾想像成deque的右邊

offer vs add:
offerLast(e)：加入失敗時回傳 false
addLast(e)：加入失敗時丟出例外（IllegalStateException）
對 ArrayDeque 來說通常沒有固定容量限制，所以實務上兩者效果相同（除非記憶體不足等極端情況）。

peek vs get:
peek, peekFirst, peekLast
只看不取。若容器為空，回傳 null（不拋例外）。
getFirst, getLast
若容器為空，會拋 NoSuchElementException。
所以在這段 Deque 單調佇列邏輯中，用 peekLast 是較安全、常見的寫法。

deque方法速講:
https://www.youtube.com/watch?v=sbAv2U9isfM

官方解答:
https://leetcode.cn/problems/sliding-window-maximum/solutions/543426/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/

補充:
為什麼官解沒有明確 l ??
因為在單調 deque 方案中 r 一直往右走 視窗左界 l = r - k + 1
既然 l 可以直接算出，就不需要單獨維護變數。
 */