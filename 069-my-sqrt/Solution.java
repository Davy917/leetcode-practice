/*
第一次寫的版本python
主思路看js
變化看java
*/
class Solution69 {
    public static int mySqrt(int x) {
        int l = 0, r = 46340, m = 0;
        while (l+1 < r){
            m = (l + r) / 2;
            if (m * m > x)
                r = m - 1;
            else
                l = m;
        }
        return r * r > x ? r : l;
    }
    static void main(String[] args) {
        System.out.println(mySqrt(10));
    }
}
/*
l = 0       l本身或它右邊某數值是答案
r = 46340   r本身或它左邊某數值是答案

迴圈結束後:
l, r必相鄰, 且都可能是答案, 需要二選一

return的時候再判定:
return r * r > x ? r : l;
 */