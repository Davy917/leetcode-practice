//自己寫的
import java.util.HashMap;

class Solution159 {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] S = s.toCharArray();
        HashMap<Character, Integer> counting = new HashMap<>();
        int l = 0, r = 0;
        int curLen = 0, maxLen = 0;
        while (r < s.length()){
            counting.put(S[r], counting.getOrDefault(S[r], 0) + 1);
            curLen++;
            while (counting.size() == 3){
                counting.put(S[l], counting.getOrDefault(S[l], 0) - 1);
                curLen--;
                if (counting.get(S[l]) == 0)
                    counting.remove(S[l]);
                l++;
            }
            maxLen = Math.max(maxLen, curLen);
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
l 移動到 counting.get(S[l]) == 0 停止

用hashmap實現很直覺
HashMap<Character, Integer> counting = new HashMap<>();

但要追求最高效的話應該用Array
使用陣列代替 HashMap，假設是標準 ASCII
int[] counts = new int[128];
 */