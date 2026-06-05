import java.util.Arrays;

class Solution167_v2 {
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int l = i + 1, r = numbers.length-1;
            int t = target - numbers[i];
            while (l <= r){
                int m = l + (r-l) / 2;
                if (t < numbers[m]){
                    r = m-1;
                }
                else if (t > numbers[m]){
                    l = m+1;
                }
                else {
                    return new int[]{i + 1, m + 1};
                }
            }
        }
        //理論不會走到這
        return null;
    }
    public static void main(String[] args) {
        int[] nums = {-1, 0};
        System.out.println("Ans = " + Arrays.toString(twoSum(nums, -1)));
    }
}
//golang那個版本寫完之後過一個月, 再練習一次還是只能寫出時間複雜度 nlogn 的版本
