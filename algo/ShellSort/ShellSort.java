import java.util.Arrays;

class ShellSort{
    static void shellSort(int[] nums){
        int len = nums.length;
        System.out.println("--- 開始希爾排序 (Shell Sort) ---");
        System.out.println("原始陣列: " + Arrays.toString(nums));
        // delta 決定每次分組的間隔
        for (int delta = len / 2; delta > 0 ; delta /= 2) {
            System.out.println("\n當前間隔 delta = " + delta);
            // start 決定從哪個位置開始進行插入排序 (每個子序列的起點)
            for (int start = 0; start < delta; start++) {
                System.out.println("  處理子序列 (起點 start = " + start + ")");
                // 子序列的元素索引為: start, start + delta, start + 2*delta, ...
                // 對每個子序列進行插入排序
                // i 是當前要插入的元素索引
                for (int i = start + delta; i < len; i += delta) {
                    int temp = nums[i]; // 儲存當前要插入的元素
                    int j; // 用於向前比較和移動元素的索引
                    System.out.println("    準備插入元素: nums[" + i + "] = " + temp);

                    // 在當前子序列中，將 temp 插入到正確的位置
                    // j - delta >= 0 確保不會越界
                    // nums[j - delta] > temp 判斷是否需要移動元素
                    for (j = i; j - delta >= 0 && nums[j - delta] > temp; j -= delta) {
                        System.out.println("      移動元素: nums[" + j + "] (" + nums[j] + ") = nums[" + (j - delta) + "] (" + nums[j - delta] + ")");
                        nums[j] = nums[j - delta]; // 元素後移
                    }
                    // 將 temp 插入到正確的位置
                    if (j != i) { // 如果發生了移動，才需要打印插入動作
                        System.out.println("      插入元素: nums[" + j + "] = " + temp);
                    } else {
                        System.out.println("      元素 " + temp + " 已在正確位置 (無需移動)");
                    }
                    nums[j] = temp;
                    System.out.println("    子序列處理後狀態: " + Arrays.toString(nums));
                }
            }
            System.out.println("間隔 delta = " + delta + " 處理完成，陣列狀態: " + Arrays.toString(nums));
        }
        System.out.println("\n--- 希爾排序完成 ---");
        System.out.println("最終排序結果: " + Arrays.toString(nums));
    }

//第二次練習時寫出, 先寫一遍insertSort, 再用insertSort的思想來寫shellSort
    static void shellSort_v2(int[] nums){
        int len = nums.length;
        int temp;
        for (int delta = len/2; delta >= 1; delta/=2){
            for (int start = 0; start < delta; start++){
                for (int cur = start + delta; cur < len; cur += delta) {
                    temp = nums[cur];
                    int pre = cur - delta;
                    while (pre >= start && nums[pre] > temp){
                        nums[pre + delta] = nums[pre];
                        pre -= delta;
                    }
                    nums[pre + delta] = temp;
                }
            }
        }
    }

    static void main(String[] args) {
        int[] nums = {5, 2, 8, 3, 7, 1, 6, 4};
        int[] nums2 = {7, 5, 1, 2, 9, 6, 4, 3};
        shellSort(nums);
        shellSort_v2(nums2);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums2));
    }
}
