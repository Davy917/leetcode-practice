class Solution345 {
    public static boolean isVowel(char ch){
        return "aeiouAEIOU".indexOf(ch) < 0;
    }
    public static void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static String reverseVowels(String s) {
        int l = 0, r = s.length()-1;
        char[] arr = s.toCharArray();
        while (l < r){
            while (l < r && isVowel(s.charAt(l)))
                l++;
            while (l < r && isVowel(s.charAt(r)))
                r--;
            swap(arr, l, r);
            l++;
            r--;
        }
        return new String(arr);
    }
    static void main(String[] args) {
        String s = "IceCreAm";
        System.out.println("Ans = " + reverseVowels(s));
    }
}
/*
indexOf 是用來找「某個字元/子字串第一次出現的位置（索引）」的方法。
indexOf 就是「找位置，找不到回 -1」
 */