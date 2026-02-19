import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution205 {
    public boolean P0205_isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i=0; i<s.length(); i++){
            Character sc = s.charAt(i);
            Character tc = t.charAt(i);
            if (map.containsKey(sc) && map.get(sc) != tc){return false;}
            if (!map.containsKey(sc) && set.contains(tc)){return false;}
            set.add(tc);
            map.put(sc, tc);
        }
        return true;
    }

    static void main(String[] args) {
        //System.out.println(new Solution205().isIsomorphic("paper", "title"));
        System.out.println(new Solution205().P0205_isIsomorphic("bar", "egg"));
    }
}

/*
第一次練習:
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i=0; i<s.length(); i++){
            Character sc = s.charAt(i);
            Character tc = t.charAt(i);
            if (map.containsKey(sc) && map.get(sc) != tc){return false;}
            if (!map.containsKey(sc) && set.contains(tc)){return false;}
            map.put(sc, tc);
            set.add(tc);
        }
        return true;
 */