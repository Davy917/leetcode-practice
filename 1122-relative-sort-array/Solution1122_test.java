import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap; // 使用 TreeMap 來自動排序不在 arr2 中的元素

public class Solution1122_test {
    public static int[] P1122_relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null)
            return null;

        // 1. 統計 arr1 中所有元素的頻率
        // 使用 HashMap 即可，因為我們不需要元素本身有序
        Map<Integer, Integer> counts = new HashMap<>();
        for (int val : arr1) {
            counts.put(val, counts.getOrDefault(val, 0) + 1);
        }
        System.out.println("Counts Map: " + counts); // Debugging

        int[] result = new int[arr1.length];
        int Index = 0; // 用於追蹤 result 陣列的當前寫入位置

        // 2. 按照 arr2 的順序將元素放入結果陣列
        for (int val : arr2) {
            // 如果 arr1 中包含 arr2 中的這個元素
            if (counts.containsKey(val)) {
                int count = counts.get(val);
                for (int i = 0; i < count; i++) {
                    result[Index++] = val;
                }
                // 從 counts 中移除這個元素，表示已經處理完畢
                counts.remove(val);
            }
        }
        System.out.println("Result after arr2 elements: " + Arrays.toString(result)); // Debugging
        System.out.println("Remaining Counts Map: " + counts); // Debugging

        // 3. 處理 arr2 中沒有出現的元素
        // 這些元素需要按照升序排列並放到結果陣列的末尾
        // 使用 TreeMap 可以自動將鍵 (數字) 排序
        TreeMap<Integer, Integer> remainingElements = new TreeMap<>(counts);
        for (Map.Entry<Integer, Integer> entry : remainingElements.entrySet()) {
            int val = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                result[Index++] = val;
            }
        }
        System.out.println("Final Result: " + Arrays.toString(result)); // Debugging

        return result;
    }
    public static void main(String[] args) {
        int[] arr1_test1 = {2, 3, 1, 3, 2, 4, 6, 9, 2, 19, 7};
        int[] arr2_test1 = {2, 1, 4, 3, 9, 6};
        int[] result1 = P1122_relativeSortArray(arr1_test1, arr2_test1);
        System.out.println("Test 1 Result: " + Arrays.toString(result1));
        // Expected: [2, 2, 2, 1, 4, 3, 3, 9, 6]
    }
}
