class Solution50 {
    double res = 1;
    public double myPow(double x, int n) {
        if (x == 1 || x == 0){
            System.out.println("x = 0 or 1, break");
            return x;
        }
        if (n == 0){
            System.out.println("n = 0, break");
            return res;
        }
        if (n < 0){
            res /= x;
            System.out.printf("res = %s ", res);
            System.out.println("n = " + n);
            return myPow(x, n+1);
        }
        else {
            res *= x;
            System.out.printf("res = %s ", res);
            System.out.println("n = " + n);
            return myPow(x, n-1);
        }
    }

    static void main(String[] args) {
        Solution50 sol = new Solution50();
        double x = 2.00000, ans = 0;
        int n = -200000000;
        ans = sol.myPow(x, n);
        System.out.println("ans = " + ans);
    }
}

/*
loop1:
res = 2, n=3

loop2:
res = 4, n=2

loop3:
res = 8, n=0
 */