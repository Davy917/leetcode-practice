import java.util.*;

class Solution557 {
    public String P0557_reverseWords(String s) {

        String[] words = s.split(" ");
        System.out.println(Arrays.toString(words));

        // 2. 反转每个单词
        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
            /*
            拆解
            String word = "Hello";  // words[i] 的值

            // 步骤1: 创建 StringBuilder
            StringBuilder sb = new StringBuilder(word);  // sb = "Hello"

            // 步骤2: 反转
            sb.reverse();  // sb = "olleH"

            // 步骤3: 转回 String
            String reversed = sb.toString();  // reversed = "olleH"

            // 步骤4: 赋值回数组
            words[i] = reversed;  // words[i] = "olleH"
             */
        }

        return String. join(" ", words);
    }
    public String P0557_reverseWordsStringBuilder(String s){
        StringBuilder reverseString = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != ' '){
                word.append(s.charAt(i));
                if (i == s.length() - 1){
                    for (int j = word.length() - 1; j >= 0; j--){
                        reverseString.append(word.charAt(j));
                    }
                }
            }
            else {
                for (int j = word.length() - 1; j >= 0; j--){
                    reverseString.append(word.charAt(j));
                }
                reverseString.append(' ');
                word.setLength(0);
            }
        }
        return reverseString.toString();
    }
    static void main(String[] args) {
        System.out.println(
                new Solution557().P0557_reverseWords(" Let's    take  LeetCode contest "));
//        System.out.println(
//                new Solution557().P0557_reverseWordsStringBuilder(" Let's  take  LeetCode contest "));
    }
}

/*
输入：s = "Let's take LeetCode contest"
输出："s'teL ekat edoCteeL tsetnoc"

输入： s = "Mr Ding"
输出："rM gniD"
 */