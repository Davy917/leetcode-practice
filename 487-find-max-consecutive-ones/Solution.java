//自己寫的, 非官方解法
class Solution487 {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        boolean isFlipped = false;
        int flipIndex = 0;
        int l=0, r=0;
        while (r < nums.length){
            if (nums[r] == 0 && isFlipped){
                l = flipIndex+1;
                flipIndex = r; //沒寫的話底下test case會錯
            }

            else if (nums[r] == 0){
                isFlipped = true;
                flipIndex = r;
            }
            System.out.printf("left = %d, right = %d, flipIndex = %d\n", l, r, flipIndex);
            maxCount = Math.max(maxCount, r-l+1);
            r++;
        }
        return maxCount;
    }
    static void main(String[] args) {
        int[] nums = {0, 1, 0, 0};
        System.out.println("Ans = " + findMaxConsecutiveOnes(nums));
    }
}
/*
寫完之後一直卡在 0,1,0,0 這個test case, Copilot給出最小修正, 就是加入當前第11行

當前思路是用isFilpped, flipIndex 來輔助 l 前進, r 就是一直無腦往前走
只要確保 l 每次都能夠在正確的位置上, 就能用19行的比大小來求出最後答案

copilot給出建議, 這題比較穩的做法應該是, 窗口內最多 1 個 0, 不需要額外維護 isFilpped, flipIndex
 */