//常數額外空間
class Solution125 {
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length()-1;
        while (l < r){
            while (l < r && !Character.isLetterOrDigit(s.charAt(l)))
                l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r)))
                r--;
            char left = Character.toLowerCase(s.charAt(l));
            char right = Character.toLowerCase(s.charAt(r));
            if (left != right)
                return false;
            l++;
            r--;
        }
        return true;
    }

    static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println("Ans = " + isPalindrome(s));
    }
}
/*
Character.isLetterOrDigit(char ch)，功能是判斷一個字元是否為：
字母（Letter）
數字（Digit）
若是則回傳 true，否則回傳 false
 */