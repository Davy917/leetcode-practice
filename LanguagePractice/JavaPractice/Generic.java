package JavaPractice;

import java.util.*;

public class Generic {
    public static void listNoUseGeneric(){
        //如果不使用泛型
        ArrayList mainList = new ArrayList();
        //需求存放學生的成績
        mainList.add(78);
        mainList.add(88);
        mainList.add(56);
        mainList.add(33);
        //問題一: 類型不安全
        mainList.add(("x"));
        System.out.println("mainList" + mainList);

        for (Object score : mainList){
            //問題二: 強轉時, 會出現類型轉換異常
            int studentScore = (Integer) score;
            System.out.println(studentScore);
        }
        return;
    }
    public static void listUseGeneric(){
        //在集合中使用泛型的情況:
        ArrayList<Integer> subList = new ArrayList<>();
        subList.add(78);
        subList.add(68);
        subList.add(77);
        subList.add(91);

        subList.listIterator();
        Iterator<Integer> iterator = subList.listIterator();
        while(iterator.hasNext()){
            int studentScore = iterator.next();
            System.out.println("studentScore = " + studentScore);
        }
    }
    public static void mapUseGeneric(){
        Map<String, Integer> map = new HashMap<>();
        map.put("Tom", 87);
        map.put("Saturn", 77);
        map.put("Gary", 64);
        //泛型嵌套
        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        //Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();
        var iterator = entry.iterator();//此時var被編譯器推斷為 Iterator<Map.Entry<String, Integer>>
        while (iterator.hasNext()){
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + " -----> " + value);
        }
    }

    static void main(String[] args) {
        //listNoUseGeneric();
        //listUseGeneric();
        mapUseGeneric();
    }
}
/*
泛型說明:
14:00開始看
https://www.youtube.com/watch?v=FIta2Jr0EVQ

mapUseGeneric, listUseGeneric 見下面影片
https://www.youtube.com/watch?v=iwh5wKKp3rsf
 */