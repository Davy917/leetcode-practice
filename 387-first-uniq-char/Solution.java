import java.util.HashMap;
import java.util.Map;

class Solution387 {
    public int P0387_firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {return i;}
        }
        return -1;
    }
    static void main(String[] args) {
        System.out.println(new Solution387().P0387_firstUniqChar("loveleetcode"));
    }
}

/*
merge方法
如果 key 不存在 或 对应值为 null：
    → 直接放入 value
否则（key 已存在且有非 null 值）：
    → 执行 remappingFunction(旧值, 新值)
    → 用函数结果更新 Map

如果函数结果为 null：
    → 移除该键值对

說明 map.merge(s.charAt(i), 1, Integer::sum);
當鍵不存在時，直接把第二個參數 value 放入（所以第一次遇到字元時放入 1）。
當鍵已存在時，呼叫 remappingFunction.apply(oldValue, value)，並把回傳結果當作新的值放回 Map。
Integer::sum 是一個方法參考，相當於 (a, b) -> Integer.sum(a, b)，也等同於 (a, b) -> a + b（自動拆箱/裝箱處理）——因此每次都把舊值和傳入的 1 相加，得到舊值 + 1。

// java
map.merge(ch, 1, Integer::sum);
// 等價於
map.merge(ch, 1, (oldV, newV) -> oldV + newV);
所以看不到明寫 +1，是因為你把 1 當作 value 傳入，然後 Integer::sum 把 oldValue 和 1 相加並回傳結果。
 */

/*
第一次練習:
        Map<Character, Integer> map = new HashMap<>();

        for (int i=0; i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
            else {
                map.put(s.charAt(i), 1);
            }
            //map.merge(s.charAt(i), 1, Integer::sum); //上面if-else可以簡化成merge
            System.out.println(map);
        }

        for (int i=0; i<s.length(); i++){
            if (map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
 */