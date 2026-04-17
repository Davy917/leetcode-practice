class Solution704 {
    public static int search(int[] nums, int target) {
        int left = -1;
        int right = nums.length;

        while (left + 1 != right){
            int middle = (left + right) / 2;
            System.out.println("middle = " + middle);
            if (target > nums[middle]){
                left = middle;
                System.out.println("left = " + left);
            }
            else if (target < nums[middle])
                right = middle;
            else
                return middle;
        }
        return -1;
    }
    static void main(String[] args) {
        int[] arr = {-1, 0, 3, 4, 5, 9, 12};
        int target = 5;
        int ans = search(arr, target);
        System.out.println("ans = " + ans);
    }
}

/*
   -1, 0, 3, 4, 5, 9, 12
-1, 0, 1, 2, 3, 4, 5, 6, 7
 */

/*
概念來自:
https://www.youtube.com/watch?v=JuDAqNyTG4g

也可見leetbook模板3:
https://leetcode.cn/leetbook/read/binary-search/xe22ch/
 */