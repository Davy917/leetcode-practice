import java.util.*;
class Solution202 {
    public boolean P0202_isHappy(int n) {
        Set<Integer> hashSet = new HashSet<>();
        while (hashSet.add(n)){
            int next = 0;
            while (n > 0){
                next += (n % 10) * (n % 10);
                n /= 10;
                System.out.println("n = " + n);
            }
            System.out.println("next = " + next);
            if (next == 1){return true;}
            n = next;
        }
        return false;
    }

    static void main(String[] args) {
        System.out.println(new Solution202().P0202_isHappy(19));
    }
}
/*
121
如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
输入：n = 19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */