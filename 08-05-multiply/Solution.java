class Solution08_05 {
    public int multiply(int A, int B) {
        System.out.println("getin");
        if (B == 1){
            System.out.println("B = 0, break");
            return A;
        }
        int res = A;
        res = res + multiply(A, B-1);
        System.out.printf("A = %s ", res);
        System.out.println("B = " + B);
        return res;
    }

    static void main(String[] args) {
        int A = 1;
        int B = 2;
        Solution08_05 sol = new Solution08_05();
        sol.multiply(A, B);
    }
}
/*
1, 3
 */