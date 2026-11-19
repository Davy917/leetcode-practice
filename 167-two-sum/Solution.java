import java.util.Arrays;

class Solution167 {
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length-1;
        int[] n = numbers;
        while (l + 1 < r){
            if (n[l] + n[r] > target){
                r--;
            } else if (n[l] + n[r] < target){
                l++;
            } else {
                return new int[]{l+1, r+1};
            }
        }
        return new int[]{l+1, r+1};
    }

    static void main(String[] args) {
        int[] numbers = {2, 7, 11, 11, 15};
        System.out.println("Ans = " + Arrays.toString(twoSum(numbers, 9)));
    }
}
//自己寫的