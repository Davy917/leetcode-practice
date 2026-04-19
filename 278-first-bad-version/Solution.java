/*
代碼參考模板:
algo/BinarySearch/BinarySearch_advance.java
 */
class VersionControl {
    private static final int firstBadVersion = 2147483647;
        boolean isBadVersion(int version){
            return (version >= firstBadVersion) ? true : false;
            // 可簡化成 return version >= badVersion;
      }
}
class Solution278 extends VersionControl {
    public int P0278_firstBadVersion(int n) {
        /*
        概念上寫成這樣才對:
        int left = 1, right = n + 1, ans = 0;
        實際在leetcode提交要改成下面, 原因見下方註解
         */
        int left = 0, right = n, ans = 0;
        while (left < right){
            int middle = left + (right - left) / 2;
            System.out.printf("left = %d, right = %d, middle = %d%n", left, right, middle);
            if (isBadVersion(middle)){
                if (!isBadVersion(middle - 1)){
                    ans = middle;
                    break;
                }
                right = middle;
            }
            else {
                if (isBadVersion(middle + 1)) {
                    ans = middle + 1;
                    break;
                }
                left = middle + 1;
            }
        }
        return ans;
    }
    static void main(String[] args) {
        int curVersion = 2147483647;
        Solution278 sol = new Solution278();
        int ans = sol.P0278_firstBadVersion(curVersion);
        System.out.println("ans = " + ans);
    }
}
/*
問題:
寫成 int left = 1, right = n + 1, ans = 0; 會被卡上限2147483647, 有點不合理??
會變成 -2147483648（overflow），所以後面 binary search 全亂掉。
這是 Java int 固定 32-bit 的規則，確實會發生，LeetCode 就是故意測這個邊界。

在 Java int 下要避開 overflow，你要把右界改成 long，例如概念：
    long right = (long)n + 1;
    計算 mid 用 long
    呼叫 isBadVersion((int)mid) 前確認 mid <= n

這題最穩定, 這題最穩定、最標準的是閉區間 [1, n], 代碼請看:
algo/BinarySearch/BinarySearch_basic.java
 */