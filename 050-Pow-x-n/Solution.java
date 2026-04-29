//myPow_original 是沒有看解答寫的, n>=200000000時會爆掉
//myPow遞規寫法是看解答寫的
//myPow迭代寫法請見python
class Solution50 {
    double res = 1;
    /*
    public double myPow_original(double x, int n) {
        long N = n;
        if (x == 1 || x == 0) {
            System.out.println("x = 0 or 1, break");
            return x;
        }
        if (N == 0) {
            System.out.println("n = 0, break");
            return res;
        }
        if (N < 0) {
            res /= x;
            System.out.printf("res = %s ", res);
            System.out.println("n = " + n);
            return myPow_original(x, (int) (N + 1));
        } else {
            res *= x;
            System.out.printf("res = %s ", res);
            System.out.println("n = " + N);
            return myPow_original(x, (int) (N - 1));
        }
    }
    */
        double myPow(double x, int n) {
            long N = n;//注意
            if (N < 0){
                x = 1.0 / x;
                N = -N;
            }
            return pow(x, N);
        }
        double pow(double x, long n){
            if (n == 0){
                return 1.0;
            }
            double half = pow(x, n/2);
            double res = half * half;
            if ((n & 1) == 1) res *= x; // n 是奇數

            System.out.printf("half = %s, ", half);
            System.out.println("res = " + res);
            return res;
    }
    static void main(String[] args) {
        Solution50 sol = new Solution50();
        double x = 2.00000, ans = 0;
        int n = 10;
        //ans = sol.myPow_original(x, n);
        ans = sol.myPow(x, n);
        System.out.println("ans = " + ans);
    }
}

/*
問題一,
為什麼用long?
long 是 Java 的整數型別（primitive type），
用來表示「沒有小數點的整數」，而且它的可表示範圍比 int 大很多。

1) long 的大小與範圍
    int：32 位元（4 bytes）
    範圍：-2,147,483,648 到 2,147,483,647
    long：64 位元（8 bytes）
    範圍：-9,223,372,036,854,775,808 到 9,223,372,036,854,775,807

問題二,
n & 1 在做什麼?
1 的二進位是 ...0001，所以：

    n & 1 只會保留 n 的最低位元（Least Significant Bit）
    最低位元若是 1 → 代表 n 是 奇數
    最低位元若是 0 → 代表 n 是 偶數
因此：

    (n & 1) == 1 等同於「n 是奇數」
    (n & 1) == 0 等同於「n 是偶數」

直觀例子（用二進位看）

    n = 6 → 110
    110 & 001 = 000 → 0（偶數）
    n = 7 → 111
    111 & 001 = 001 → 1（奇數）

問題三,
half 是怎麼一步步算出來的？（以 x=2, n=9 為例）
你呼叫 pow(2, 9) 時：

1. half = pow(2, 9/2) → pow(2, 4)
2. 進到 pow(2, 4)：
    half = pow(2, 4/2) → pow(2, 2)
3. 進到 pow(2, 2)：
    half = pow(2, 2/2) → pow(2, 1)
4. 進到 pow(2, 1)：
    half = pow(2, 1/2) → pow(2, 0)
5. 進到 pow(2, 0)：這是遞迴的「最底部」
    回傳 1.0
接著開始往回算（回溯）：
回到 pow(2,1)：
    half = 1
    res = half*half = 1
    n 是奇數 → res *= x → 2
    回傳 2
回到 pow(2,2)：
    half = 2
    res = 2*2 = 4
    n 偶數 → 不乘 x
    回傳 4
回到 pow(2,4)：
    half = 4
    res = 4*4 = 16
    回傳 16
回到 pow(2,9)：
    half = 16
    res = 16*16 = 256
    n 奇數 → res *= 2 → 512
    回傳 512
所以 half 每一層其實都代表「先算比較小的次方（n/2），再平方回去」，
這就是快速冪能把複雜度從 O(n) 降到 O(log n) 的關鍵。
 */