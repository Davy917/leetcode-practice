class Solution28 {
    public int strStr(String haystack, String needle) {
        //宣告雙指針以及錨點
        int i = 0;
        int j = 0;
        int Anchor = 0;
        while (i<haystack.length()){
            System.out.println(i + " " + j + " " + Anchor);
            //如果不匹配
            if (haystack.charAt(i) != needle.charAt(j)){
                System.out.println("不匹配 "+ i +" "+ j +" "+ Anchor);
                //雙指針重置
                Anchor += 1;
                j = 0;
                i = Anchor;
            }
            //如果匹配
            else {
                //如果全部匹配
                if (j == needle.length()-1){
                    return Anchor;
                }
                //雙指針往前推進
                i += 1;
                j += 1;
            }

        }
        //沒找到匹配的下標
        return -1;
    }

    static void main(String[] args){
        String s1 = ("Mississippi");
        String s2 = ("issip");
        Solution28 sol = new Solution28();
        System.out.println(sol.strStr(s1,s2));
    }
}