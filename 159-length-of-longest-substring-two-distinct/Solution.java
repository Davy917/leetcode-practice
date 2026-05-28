import java.util.HashMap;

class Solution159 {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] S = s.toCharArray();
        var counting = new HashMap<Character, Integer>();
        int l = 0, r = 0;
        int maxLength = 0;
        while (r < s.length()){
            counting.put(S[r], counting.getOrDefault(S[r], 0) + 1);

            while (counting.size() == 3){
                counting.put(S[l], counting.getOrDefault(S[l], 0) - 1);
                if (counting.get(S[l]) == 0)
                    counting.remove(S[l]);
                l++;
            }
            r++;
        }
        return maxLength;
    }
    static void main(String[] args) {
        String s = "eceba";
        System.out.println("Ans = " + lengthOfLongestSubstringTwoDistinct(s));
    }
}
/*
思路:
紀錄每個走過的字符的出現次數
r走到第三個字符的時候要移動 l
l 移動到 counting[s] = 0 停止
 */