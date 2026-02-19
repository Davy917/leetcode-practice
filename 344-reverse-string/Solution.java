//用三杯交換法實現
class Solution344 {
    public void P0344_reverseString(char[] s) {
        if (s.length == 0){
            return;
        }
        char temp = ' ';

        for (int i = 0; i< s.length / 2; i++){
            temp = s[i];
            s[i] = s[s.length-i-1];
            s[s.length-i-1] = temp;
        }
    }

    public static void main(String[] args) {
        Solution344 sol = new Solution344();
        char[] c = {'h','e','l','l','o'};
        sol.P0344_reverseString(c);
        System.out.println(new String(c));
    }
}