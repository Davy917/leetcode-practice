//官方解答
import java.util.Deque;

class Solution {
    public static boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        stack.push(1);
        while (i < n) {
            
        }
    }
    public static void main(String[] args) {
        Integer[] nums = {9,3,4,null,null,1,null,null,2,null,6};
        System.out.println(nums);
    }
}
/*
官方解答
https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/solutions/650583/yan-zheng-er-cha-shu-de-qian-xu-xu-lie-h-jghn/
*/