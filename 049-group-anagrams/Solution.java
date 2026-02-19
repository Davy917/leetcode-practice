import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

class Solution049 {
        public List<List<String>> P0049_groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs){
            //System.out.println(str);
            char[] arr = str.toCharArray();//toCharArray() 是 String 類別的方法，用於將字串轉換成字元陣列（char[]）。
            Arrays.sort(arr);//原始型別陣列支援：int[], long[], short[], byte[], char[], float[], double[]。物件陣列（例如 String[]、Integer[]）可排序
            String sortedStr = new String(arr);

            //-------觀察toAdd的變化
            List<String> toAdd =
                    map.getOrDefault(sortedStr, new ArrayList<>());
            System.out.println("toAdd = " + toAdd);
            toAdd.add(str);
            System.out.println("toAdd.add = " + toAdd);
            map.put(sortedStr, toAdd);//放入键值对，如果 key 已存在则覆盖旧值
            //map.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);//可以簡化成一句, 優雅但是可讀性較差
            //-------
        }
/*
不能寫
        return map;
         因為方法簽名是 List<List<String>>，
         但 map 的型別是 Map<String, List<String>>，兩者不相容。
         要回傳符合簽名的結果，可以把 map.values() 轉成 List<List<String>>，或改變方法的回傳型別。*/
        return new ArrayList<>(map.values());
    }

    static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution049().P0049_groupAnagrams(strs));
    }
}
/*
迴圈次數	str 	sortedStr	map 狀態
1	    "eat"	"aet"	    {"aet": ["eat"]}
2	    "tea"	"aet"	    {"aet": ["eat", "tea"]}
3	    "tan"	"ant"	    {"aet": ["eat", "tea"], "ant": ["tan"]}
4	    "ate"	"aet"	    {"aet": ["eat", "tea", "ate"], "ant": ["tan"]}
5	    "nat"	"ant"	    {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"]}
6	    "bat"	"abt"	    {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"], "abt": ["bat"]}

toAdd是什麼??
toAdd 是指向 List<String> 的參考，等於 map.getOrDefault(sortedStr, new ArrayList<>()) 的回傳值。
這個 List 的每個元素都是原始的 str（未排序的字串），因為程式呼叫 toAdd.add(str)。
若 sortedStr 已存在於 map，修改 toAdd 會直接反映在 map；若不存在，getOrDefault 回傳新的 ArrayList，接著用 map.put(sortedStr, toAdd) 將它放入 map。
可簡化為：map.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
 */