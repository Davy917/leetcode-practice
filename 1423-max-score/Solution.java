/*
官方解答
 */
import java.util.Arrays;
class Solution1423 {
    public static int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        for (int val: cardPoints)
            sum += val;

        int windowSize = cardPoints.length - k;
        int windowMinVal = 0;
        for (int index = 0; index < windowSize; index++)
            windowMinVal += cardPoints[index];

        int left = 0, minVal = windowMinVal;
        for (int right = windowSize-1; right < cardPoints.length; right++) {
            if (right > windowSize-1)
                windowMinVal += cardPoints[right];
            minVal = Math.min(minVal, windowMinVal);
            windowMinVal -= cardPoints[left++];
        }
        return sum - minVal;
    }
    static void main(String[] args) {
        int[] cardPoints = {1,79,80,1,1,1,200,1};
        int k =7;
        System.out.println("Ans = " + maxScore(cardPoints, k));
    }
}
/*
官方解答:
https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/solutions/514347/ke-huo-de-de-zui-da-dian-shu-by-leetcode-7je9/

一開始完全沒想法:
直到看到這句
我们可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
 */