import java.util.*;
//Package Solution19;
class Solution20 {
    public boolean isValid(String s) {
        //#如果輸入的是奇數,代表永遠不會全部都配對完
        if(s.length()%2==1){
            return false;
        }
        //宣告一個字典
        Map<Character, Character> Pairs = Map.ofEntries(
                Map.entry(')', '('),
                Map.entry('}', '{'),
                Map.entry(']', '[')
        );
        //宣告一個堆疊
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            //如果進來的是右括號
            if(Pairs.containsKey(c)) {
                //先看stack裡面有無左括號
                if(stack.isEmpty()) {
                    return false;
                }
                //stack中最新的那個左括號跟現在進來的這右括號不匹配
                if(stack.peek() != Pairs.get(c)){
                    return false;
                }
                //進來的是右括號,而且符合配對條件
                stack.pop();
            }
            //如果進來的是左括號
            else{
                //直接丟進stack
                stack.push(c);
            }
        }
        //如果stack空了,回傳True
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution20 sol = new Solution20();
        System.out.println(sol.isValid("(]{}"));    // true
    }
}