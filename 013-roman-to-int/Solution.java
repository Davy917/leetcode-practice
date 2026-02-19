import java.util.Map;

class Solution13 {
    public int P0013_romanToInt(String s) {
        char[] c = s.toCharArray();
        /*
        //基本宣告方式
        Map<Character,Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);*/

        Map<Character,Integer> map = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
        );

        //由後往前
        int result = map.get(c[s.length()-1]);

        for (int i=s.length()-2; i>=0; i--){
            System.out.println("i = " + i);
            //特殊情況
            if (map.get(c[i+1]) > map.get(c[i])){
                result -= map.get(c[i]);
            }
            //一般情況
            else {
                result += map.get(c[i]);
            }
            System.out.println("result = " + result);
        }
        return result;
    }


    static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(new Solution13().P0013_romanToInt(s));
    }
}