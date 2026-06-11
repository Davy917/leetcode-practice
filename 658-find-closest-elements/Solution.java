/*
參考模板:
algo/BinarySearch/BinarySearch_advance.java

相同寫法也可以看go版本
658-find-closest-elements/solution.go

非官方解答
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution658 {
    public static List<Integer> P0658_findClosestElements(int[] arr, int k, int x) {
        List<Integer> mainList = new ArrayList<>();
        if (k == arr.length)
            return Arrays.stream(arr).boxed().collect(java.util.stream.Collectors.toList());

        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, mid = %d%n", left, right, mid);
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int target;
        if (left == 0) {
            target = 0;
        } else if (left == arr.length) {
            target = arr.length - 1;
        } else {
            target = Math.abs(arr[left] - x) < Math.abs(arr[left - 1] - x) ? left : left - 1;
        }

        mainList.add(arr[target]);
        left = target - 1;
        right = target + 1;

        while (mainList.size() < k){
            System.out.printf("left = %d, right = %d, mainList = %s%n", left, right, mainList);
            if (left < 0){
                mainList.add(arr[right]);
                right++;
                continue;
            }
            else if (right >= arr.length) {
                mainList.add(arr[left]);
                left--;
                continue;
            }
            if (Math.abs(arr[left] - x) < Math.abs(arr[right] - x)){ // 注意是 -x 不是 -arr[target]
                mainList.add(arr[left]);
                left--;
            }
            else if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)){
                mainList.add(arr[right]);
                right++;
            }
            else {
                if (arr[left] < arr[right]){
                    mainList.add(arr[left]);
                    left--;
                }
                else{
                    mainList.add(arr[right]);
                    right++;
                }
            }
        }
        mainList.sort(Integer::compareTo);
        System.out.println("sortedMainList = " + mainList);
        return mainList;
    }

    static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 4, 5};
        //P0658_findClosestElements(arr, 4, -1);
        int[] arr2 = {1, 2, 3, 4, 5};
        //P0658_findClosestElements(arr2, 4, 3);
        int[] arr3 = {0, 0, 0, 1, 3, 5, 6, 7, 8, 8};
        //P0658_findClosestElements(arr3, 2, 2);
    }
}

/*
原始版, 跑leetcode會出錯, 錯誤原因請看最後面

參考模板:
algo/BinarySearch/BinarySearch_basic.java

class Solution658_original {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> mainList = new ArrayList<>();
        if (k == arr.length)
            return Arrays.stream(arr).boxed().collect(java.util.stream.Collectors.toList());

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] < x)
                left = middle + 1;
            else
                right = middle - 1;
        }
        int target;
        if (left == 0)
            target = 0;
        else if (left == arr.length)
            target = arr.length - 1;
        else
            target = Math.abs(arr[left] - x) < Math.abs(arr[left - 1] - x) ? left : left - 1;

        mainList.add(arr[target]);
        left = target - 1;
        right = target + 1;

        for (int count = 1; count < k && (left >= 0 || right <= k); count++) {
            System.out.printf("left = %d, right = %d, mainList = %s%n", left, right, mainList);
            if (left < 0){
                mainList.add(arr[right]);
                right++;
                continue;
            }
            else if (right > k) { //因為一直盯著 1, 1, 2, 3, 4, 5 寫, 所以這行才寫錯
                System.out.println("hit");
                mainList.add(arr[left]);
                left--;
                continue;
            }
            if (Math.abs(arr[left] - arr[target]) < Math.abs(arr[right] - arr[target])){
                mainList.add(arr[left]);
                left--;
            }
            else if (Math.abs(arr[left] - arr[target]) > Math.abs(arr[right] - arr[target])){
                mainList.add(arr[right]);
                right++;
            }
            else {
                if (arr[left] < arr[right]){
                    mainList.add(arr[left]);
                    left--;
                }
                else{
                    mainList.add(arr[right]);
                    right++;
                }
            }
        }
        mainList.sort(Integer::compareTo);
        System.out.println("sortedMainList = " + mainList);
        return mainList;
    }
        static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 3, 5, 6, 7, 8, 8};
            findClosestElements(arr, 2, 2);
    }
}

    修改這3個地方, 變成現在的版本
    比較時改成 Math.abs(arr[left] - x) vs Math.abs(arr[right] - x) (不該犯錯)
    邊界改成 right < arr.length 與 right >= arr.length (不該犯錯)
    迴圈條件改成「直到取滿 k 個」：while (mainList.size() < k)
*/