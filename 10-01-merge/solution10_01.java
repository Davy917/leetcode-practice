import java.util.Arrays;
class Solution10_01 {
    public static void merge(int[] A, int m, int[] B, int n) {
        int index1 = 0;
        int index2 = 0;
        int[] result = new int[A.length];
        while (index1 < m && index2 < n){
            if (A[index1] <= B[index2]){
                result[index1 + index2] = A[index1];
                index1++;
            }
            else {
                result[index1 + index2] = B[index2];
                index2++;
            }
        }
        while (index1 < m){
            result[index1 + index2] = A[index1];
            index1++;
        }
        while (index2 < n){
            result[index1 + index2] = B[index2];
            index2++;
        }
        System.arraycopy(result, 0, A, 0, result.length);
        System.out.println(Arrays.toString(A));
    }
    static void main(String[] args) {
        int[] A = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] B = {2, 5, 6};
        int n = 3;
        merge(A, m, B, n);
    }
}