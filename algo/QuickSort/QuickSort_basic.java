package QuickSort; /**
 * pivot的選擇方式有三種:
 * 1. 選第一個元素
 * 2. 選最後一個元素
 * 3. 選區間內隨機一個元素
 */

import java.util.Arrays;

public class QuickSort_basic {
    /**
     * quickSort中的等式是如何得出來的??
     * //將數組分區，並取得中間值的下標
     * int middle = partition(arr, start, end);
     *
     * //當左邊區域中至少有兩個數字時，對左邊區域快速排序
     * if (start != middle && start != middle - 1){
     *     quickSort(arr, start, middle - 1);
     * }
     *
     * //當右邊區域中至少有兩個數字時，對右邊區域快速排序
     * if (start != middle && start != middle + 1){
     *     quickSort(arr, middle + 1, end);
     * }
     *
     * 分析上面四種判斷條件:
     * start == middle 相當於 quickSort(arr, start, middle - 1)中的start == end + 1
     * start == middle - 1 相當於 quickSort(arr, start, middle - 1)中的start == end
     * middle == end 相當於 quickSort(arr, middle + 1, end)中的start == end + 1
     * middle = end - 1 相當於 quickSort(arr, middle + 1, emd)中的start == end
     *
     * 綜上, 我們可以將此邊界條件統一移動到 quickSort 函數之前:
     * public static void quickSort(int[] arr, int start, int end) {
     *     // 如果區域內的數字少於兩個,退出遞歸
     *     if (start == end || start == end + 1) return;
     *     // 將數組分區. 並獲得中間值下標
     *     int middle = partition(arr, start, end);
     *     // 對左邊區域進行快速排序
     *     quickSort(arr, start, middle - 1);
     *     // 對右邊區域進行快速排序
     *     quickSort(arr, middle + 1, end);
     * }
     *
     * 更進一步, 上文所說的middle >= start && middle <= end可以推出
     * 除了start == end || start == end + 1這兩個條件外, 其他情況下的start都小於end
     * 最後可以得出如下quickSort
     *
     * 我們需要知道,這裡的start >= end實際上只有兩種情況:
     * start == end:表明區域內只有一個數字
     * start ==end + 1:表明區域內一個數字也沒有
     */
    void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }
    void quickSort(int[] arr, int start, int end){
        System.out.println("in quickSort");
        System.out.printf("quickSort(start=%d, end=%d)%n", start, end);
        //如果區域內的數字少於兩個, 退出遞歸
        if (start >= end){
            System.out.printf("  return because start>=end (start=%d, end=%d)%n", start, end);
            return;
        }
        //middle左邊的數都比它小，右邊的數都比它大
        System.out.println("start to find middle");
        int middle = partition(arr, start, end);
        System.out.println("find middle = " + arr[middle]);
        //接著對左邊區域進行快速排序
        System.out.println("進入左半邊");
        quickSort(arr, start, middle - 1);
        //接著對右邊區域進行快速排序
        System.out.println("進入右半邊");
        quickSort(arr, middle + 1, end);
    }
    /*
    layer0：quickSort(arr, 0, 6)
    partition → middle=3
    呼叫左邊 → layer1：quickSort(arr, 0, 2)

    layer1：quickSort(arr, 0, 2)
    partition → middle=0
    呼叫左邊 → layer2：quickSort(arr, 0, -1)

    layer2：quickSort(arr, 0, -1)
    start>=end → return
    回到layer1 → quickSort(arr, 0, 2)

    layer1：quickSort(arr, 0, 2)
    呼叫右邊 → layer2：quickSort(arr, 1, 2)

    layer2：quickSort(arr, 1, 2)
    partition → middle=2
    呼叫左邊 → layer3：quickSort(arr, 1, 1)

    layer3：quickSort(arr, 1, 1)
    start>=end → return
    回到layer2 → quickSort(arr, 1, 2)

    layer2：quickSort(arr, 1, 2)
    呼叫右邊 → layer3：quickSort(arr, 3, 2)

    layer3：quickSort(arr, 3, 2)
    start>=end → return
    回到layer2 → quickSort(arr, 1, 2)

    layer2：quickSort(arr, 1, 2)
    return（左右都處理完）
    回到layer1 → quickSort(arr, 0, 2)

    layer1：quickSort(arr, 0, 2)
    return（左右都處理完）
    回到layer0 → quickSort(arr, 0, 6)

    layer0：quickSort(arr, 0, 6)
    呼叫右邊 → layer1：quickSort(arr, 4, 6)

    layer1：quickSort(arr, 4, 6)
    partition → middle=5
    呼叫左邊 → layer2：quickSort(arr, 4, 4)

    layer2：quickSort(arr, 4, 4)
    start>=end → return
    回到layer1 → quickSort(arr, 4, 6)

    layer1：quickSort(arr, 4, 6)
    呼叫右邊 → layer2：quickSort(arr, 6, 6)

    layer2：quickSort(arr, 6, 6)
    start>=end → return
    回到layer1 → quickSort(arr, 4, 6)

    layer1：quickSort(arr, 4, 6)
    return（左右都處理完）
    回到layer0 → quickSort(arr, 0, 6)

    layer0：quickSort(arr, 0, 6)
    return（整體排序完成）
    */


    int partition(int[] arr, int start, int end){
        System.out.println("in partition" + Arrays.toString(arr));
        int pivot = arr[start];
        int left = start + 1;
        int right = end;

        while (left < right){

            //debug
            System.out.println("pivot.val = " + arr[start] + " Index = " + start);
            System.out.println("left.val = " + arr[left] + " Index = " + left);
            System.out.println("right.val = " + arr[right] + " Index = " + right);

            //此迴圈的用意在於, 找到第一個大於pivot的數字
            //arr[left] <= pivot, 如果 left 值比 pivot小, 則繼續比較下一個left
            while (left < right && arr[left] <= pivot){//注意是 arr[left] <= pivot, 原本寫成pivot > arr[left]
                left++;
                System.out.println("pivot > left, " + "left++");
            }

            //拿這個數字跟right交換
            //一旦 left == right，代表指標已經相遇（區間收斂），此時再交換會造成兩個問題：做無效動作，甚至破壞後面收尾邏輯對 right 的假設。
            if (left != right){
                System.out.printf("find %s > pivot, right-- %n", arr[left]);
                swap(arr, left, right);
                right--;
            }
        }

        /*
        上面的迴圈結束後若 left == right，代表已經相遇；
        如果相遇點 arr[right] > pivot，表示這個位置屬於右邊（大於 pivot）那一區，
        pivot 不該放在這裡，所以先 right--，再在最後用 swap(arr, start, right) 把 pivot 放回正確位置。
         */
        if (left == right && arr[right] > pivot){
            right--;
            System.out.println("left, right相遇 且 right.val > pivot, right--");
        }
        if (right!= start){//注意, 這裡主要是在避免 swap(arr, start, start) 這種「自己跟自己交換」的無意義動作
            System.out.println("left, right相遇");
            swap(arr, start, right);
        }return right;
    }

//雙指針版本
    void quickSort_v2(int[] arr){
        quickSort_v2(arr, 0, arr.length-1);
    }
    void quickSort_v2(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        int middle = partition_v2(arr, start, end);
        quickSort_v2(arr, start, middle - 1);
        quickSort_v2(arr, middle + 1, end);
    }
    int partition_v2(int[] arr, int start, int end){
        int pivot = arr[start];
        int left = start + 1;
        int right = end;
        while (left < right){
            while (left < right && arr[left] <= pivot){
                left++;
            }
            while (left < right && arr[right] >= pivot){
                right--;
            }
            if (left < right){
                //為什麼 swap 後要 left++ / right--？ 答案見下方partition_v2
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        if (left == right && arr[right] > pivot){
            right--;
        }
        //注意這邊沒加 right!= start 這個判定條件, 也不會影響結果
        swap(arr, start, right);
        return right;
    }

    static void swap(int[] arr, int i, int j){
        System.out.println("in swap ");
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        System.out.println(Arrays.toString(arr));
    }

    static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 6, 3, 5};
        int[] arr2 = {2, 2, 1, 1, 1, 2, 2};
        QuickSort_basic quickSort = new QuickSort_basic();
        quickSort.quickSort(arr);
        //quickSort.quickSort_v2(arr);
        System.out.println(Arrays.toString(arr));
    }
}

/*
partition:
觀察arr變化

從left開始, 遇到比pivot大的數, 就交換到數組最後, 並將right減一
直到left和right相遇, 再將pivot 和中間數交換(right)此時數組就被分成左右兩區
接下來會對左邊區域進行快速排序,

4, 2, 7, 1, 6, 3, 5
4, 2, 5, 1, 6, 3, 7
4, 2, 3, 1, 6, 5, 7 <---left 和 right 相遇
1, 2, 3, 4, 6, 5, 7

代碼出處:
最简单的分区算法
https://leetcode.cn/leetbook/read/sort-algorithms/eul7hm/
 */

/*
partition_v2
p  l              r
4, 2, 7, 1, 6, 3, 5
p     l        r
4, 2, 7, 1, 6, 3, 5

p     l        r
4, 2, 3, 1, 6, 7, 5 <----swap後

看第 1 輪 swap 後：
arr[2] 變成 3（已經確定在正確的左邊）
arr[5] 變成 7（已經確定在正確的右邊）
所以 left++/right-- 是把「剛剛已經放對的兩格」直接排除，
縮小待處理區間，避免下一輪又從同一格重新檢查。

p        l  r
4, 2, 3, 1, 6, 7, 5

第二輪外層迴圈
            l
p           r
4, 2, 3, 1, 6, 7, 5

            l
p        r
4, 2, 3, 1, 6, 7, 5

            l
p        r
1, 2, 3, 4, 6, 7, 5
 */