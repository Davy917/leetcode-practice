//自己寫的
class Solution11 {
    public static int maxArea(int[] height) {
        int curArea = 0, maxArea = 0;
        int l = 0, r = height.length-1;
        while (l < r){
            if (height[l] <= height[r]){
                curArea = height[l] * (r-l);
                l++;
            }
            else {
                curArea = height[r] * (r-l);
                r--;
            }
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
    public static void main(String[] args) {
        int[] height = {1,1};
        System.out.println("Ans = " + maxArea(height));
    }
}
/*
思路:
每次比較 height[l], height[r], 剔除掉小的那邊
如果相等的情況, 剔除 l, r 哪邊都無所謂,
記錄當下的區域面積, 並跟 maxArea 做比較
循環往復, 就能找到答案
*/