class Solution287_v2 {
    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
            System.out.printf("slow = %d, fast = %d\n", slow, fast);
        } while (slow != fast);
        slow = 0;
        while (fast != slow){
            slow = nums[slow];
            fast = nums[fast];
            System.out.printf("slow = %d, fast = %d\n", slow, fast);
        }
        return slow;
    }
    static void main(String[] args) {
        int[] nums = {1, 4, 6, 6, 6, 2, 3};
        System.out.println("Ans = " + findDuplicate(nums));
    }
}
/*
do {
    // 迴圈主體
} while (條件);

先執行一次區塊，再判斷條件。
    條件為 true 就繼續，false 就結束。
    while (條件); 這行最後的分號 ; 不能漏。
    和 while 的差別
    while：先判斷，可能一次都不執行。
    do...while：先做再判斷，至少執行一次。

力扣視頻題解:
https://leetcode.cn/problems/find-the-duplicate-number/solutions/261119/xun-zhao-zhong-fu-shu-by-leetcode-solution/

其它詳細解說請看
287-find-duplicate/Floyd_conversation.md
 */