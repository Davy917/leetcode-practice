class Solution58 {
    public int lengthOfLastWord(String s) {

        //宣告計數器
        int Ans = 0;
        //進入迴圈判定
        for (int i = s.length()-1; i>=0 ; i--){

            System.out.println(i);
            if (s.charAt(i) == ' ' && Ans == 0){
                System.out.println("in if");
                continue;
            }
            else if (s.charAt(i) == ' ' && Ans != 0){
                System.out.println("in elif");
                return Ans;
            }
            else {
                Ans += 1;
                System.out.println("in else" + Ans);
            }
        }
        return Ans;
    }

    static void main(String[] args){
        Solution58 sol = new Solution58();
        String s1 = "emoon  ";
        System.out.println(sol.lengthOfLastWord(s1));
    }
}