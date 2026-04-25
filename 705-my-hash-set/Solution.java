import java.util.*;
import java.util.LinkedList;

class P0705_MyHashSet {

    List<Integer>[] bucketSet;//每個bucket是一個LinkedList
    int capacity = 10000;//bucket的總數

    public P0705_MyHashSet() {
        bucketSet = new LinkedList[capacity];//創建10000個空bucket, 每個bucket初始值為null
    }

    private int hashFunction(int key){
        return key % capacity;//取餘運算, 決定key放在哪個bucket
    }

    public boolean contains(int key) {
        int bucketIndex = hashFunction(key);//呼叫hashFunction,計算桶編號, 把結果存入bucketIndex
        List<Integer> bucketList = bucketSet[bucketIndex];
        if (bucketList == null){return false;}
        for (Integer item : bucketList){
            if (item == key){
                return true;
            }
        }
        return false;
    }

    public void add(int key) {
        if (contains(key)){
            return;
        }
        int bucketIndex = hashFunction(key);
        if (bucketSet[bucketIndex] == null){
            bucketSet[bucketIndex] = new LinkedList<>();
        }
        bucketSet[bucketIndex].add(key);
    }

    public void remove(int key) {
        int bucketIndex = hashFunction(key);
        List<Integer> bucketList = bucketSet[bucketIndex];
        if (bucketSet[bucketIndex] == null){
            return;
        }
        Iterator<Integer> iterator = bucketList.iterator();
        while (iterator.hasNext()){
            if (iterator.next() == key){
                iterator.remove();
                break;
            }
        }
    }

    public static void main(String[] args) {
        String[] ops = {"MyHashSet","add","add","contains","contains","add","contains","remove","contains"};
        System.out.println(ops.length);
        int[][] vals = { {}, {1}, {2}, {1}, {3}, {2}, {2}, {2}, {2} };
        Object[] res = new Object[ops.length];//宣告並建立一個長度為 ops.length 的 Object[] 陣列，用來收集每個操作的回傳值，目前 ops.length 的值為9。
        P0705_MyHashSet myHashSet = null;//宣告一個局部變數 myHashSet，型別為 P0705_MyHashSet，並用 null 初始化。

        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case "MyHashSet":
                    myHashSet = new P0705_MyHashSet();
                    res[i] = null;
                    break;
                case "add":
                    myHashSet.add(vals[i][0]);
                    res[i] = null;
                    break;
                case "remove":
                    myHashSet.remove(vals[i][0]);
                    res[i] = null;
                    break;
                case "contains":
                    res[i] = myHashSet.contains(vals[i][0]);
                    break;
                default:
                    res[i] = null;
            }
        }
        System.out.println(Arrays.toString(res));
    }
}

/**
 * 定義 ops（操作名稱序列）與 vals（對應參數陣列），模擬 LeetCode 的輸入格式。
 * 建立 res 陣列用來收集每個操作的回傳值（非回傳值位置放 null）。
 * 宣告 MyHashSet myHashSet = null;，在遇到 "MyHashSet" 操作時建立實例。
 * 
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 *
 *
 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。

 实现 MyHashSet 类：

 void add(key) 向哈希集合中插入值 key 。
 bool contains(key) 返回哈希集合中是否存在这个值 key 。
 void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。

 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）

 y = x % 5  (哈希函数)

 左侧绿框          中间蓝色区域         右侧蓝框数组        右侧绿框链表
 (输入)           (哈希计算)          (bucketSet)       (bucketList)
 ┌────┐                              ┌────┐
 │ 0  │────────────────────────────→ │ 0  │──→ ┌───┐    ┌───┐
 ├────┤                              ├────┤    │ 0 │──→ │ 5 │
 │ 1  │                              │ 1  │──→ ┌───┐
 ├────┤                              ├────┤    │ 1 │
 │ 5  │← bucketIndex = 5             │ 2  │
 ├────┤  (这是输入的 key)              ├────┤
 │ 6  │                              │ 3  │
 └────┘                              └────┘
 ↑
 bucketSet[bucketIndex]
 指向某个桶的链表
 */