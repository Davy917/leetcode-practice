/*
官方解答看java
自己寫的看js
*/
import java.util.*;

class RandomizedSet {
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random rand;//getRandom方法會用到

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();// 創建 Random 對象
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){return false;}
        map.put(val, list.size());
        list.add(val);//等價於list.add(channelList.size(), val);
        System.out.println("After insert:\n" + "channelList = " + list + ", map = " + map);
        return true;
    }
    // Remove: O(n)
    public boolean remove(int val) {
        //(2)先有畫面才能把remove寫出來
        if(!map.containsKey(val)){return false;}
        //再來才是這邊，為什麼是這樣拆??
        int valIndex = map.get(val);//得到val在list中的下標
        int lastIndex = list.size()-1;//得到list中最後一個val的下標
        int lastElement = list.get(lastIndex);//得到list中最後一個val
        System.out.println("remove setup:\n"+
                "valIndex = " + valIndex + ", lastIndex = " + lastIndex + ", lastElement = " + lastElement);
        //(1)這邊會先寫出來
        map.put(lastElement, valIndex);//把map最後一個元素的下標改成val的下標
        list.set(valIndex, lastElement);//把要remove的val，替換成list最後一個val
        System.out.println("Before remove:\n" + "channelList = " + list + ", map = " + map);

        map.remove(val);
        list.remove(lastIndex);//直接把list最後一個val拔掉
        System.out.println("After remove:\n" + "channelList = " + list + ", map = " + map);
        return true;
    }

    // New simplyRemove: O(n)
    public boolean simplyRemove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int idx = map.get(val);
        // remove the element at idx (this shifts subsequent elements left)
        list.remove(idx);
        // remove the key from map
        map.remove(val);
        System.out.println("After simplyRemove:\n" + "channelList = " + list + ", map = " + map);
        // update indices in map for elements that were shifted left
        for (int i = idx; i < list.size(); i++) {
            map.put(list.get(i), i);
        }
        //System.out.println("After simplyRemove:\n" + "channelList = " + channelList + ", map = " + map);
        return true;
    }

    public int getRandom() {
        int randomIndex = rand.nextInt(list.size());//生成一個隨機的下標
        return list.get(randomIndex);//再用這個下標去找值，並return
    }
    static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.remove(2);
        randomizedSet.insert(2);
        randomizedSet.getRandom();
        randomizedSet.remove(1);
        randomizedSet.insert(2);
        randomizedSet.getRandom();
    }
}