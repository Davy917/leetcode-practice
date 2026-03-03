import java.util.*;
public class PracticeMyHashSet {
    List<Integer>[] bucketSet;
    private static final int CAPACITY = 10000;
    public PracticeMyHashSet(){bucketSet = new LinkedList[CAPACITY];}

    public int hashFunction(int key){return key % CAPACITY;}

    public boolean contains(int key){

        int bucketIndex = hashFunction(key);
        List<Integer> bucketList = bucketSet[bucketIndex];

        if (bucketList == null){return false;}//bucketList的初始值是null, 所以可以這樣寫
        for (Integer i : bucketList){//只是要快速遍歷bucketList, 所以使用增強型for迴圈
            if (i == key){return true;}
        }
        return false;//沒找到返回false
    }
    public void add(int key){

        int bucketIndex = hashFunction(key);
        List<Integer> bucketList = bucketSet[bucketIndex];

        if (contains(key)){
            return;
        }
        if (bucketList == null){
            bucketList = new LinkedList<>();
        }
        bucketList.add(key);

    }
    public void remove(int key){

        int bucketIndex = hashFunction(key);
        List<Integer> bucketList = bucketSet[bucketIndex];

        if (bucketList == null){
            return ;
        }
        Iterator<Integer> iterator = bucketList.iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals(key)){
                iterator.remove();
                break;
            }
        }

    }

    static void main(String[] args) {
    }
}
/*
List<Integer>[]
是一個陣列，每個元素（每個槽）都是一個 List<Integer>，每個列表裝 Integer。
List<List<Integer>>
是一個列表，此列表每一個空間又裝著列表

拆解 Iterator<Integer> iterator = bucketList.iterator();

Iterator<Integer>：型別，表示一個可以遍歷 Integer 元素的迭代器（介面 Iterator，泛型為 Integer）。
iterator：變數名稱，指向該迭代器實例。
bucketList.iterator()：呼叫 List 的 iterator() 方法，回傳一個用來遍歷 bucketList 的 Iterator<Integer>。
若 bucketList 為 null，呼叫會拋出 NullPointerException。
整句意思是「從 bucketList 取得一個 Integer 元素的迭代器並指派給變數 iterator」，
常用於如下模式以安全地遍歷並在遍歷中移除元素（使用 iterator.remove()）：

public int hashFunction(int key){ return key % CAPACITY; }

private List<Integer> getBucket(int key) {
    return bucketSet[hashFunction(key)];
}

private List<Integer> ensureBucket(int key) {
    int idx = hashFunction(key);
    List<Integer> bucket = bucketSet[idx];
    if (bucket == null) {
        bucket = new LinkedList<>();
        bucketSet[idx] = bucket;
    }
    return bucket;
}

public boolean contains(int key){
    List<Integer> bucketList = getBucket(key);
    if (bucketList == null){ return false; }
    for (Integer i : bucketList){
        if (i.equals(key)){ return true; }
    }
    return false;
}

public void add(int key){
    if (contains(key)){
        return;
    }
    List<Integer> bucketList = ensureBucket(key);
    bucketList.add(key);
}
 */