package TreeSet;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class User implements Comparable{
    String name;
    int age;
    User(String name, int age){
        this.name = name;
        this.age = age;
    }
    //按照姓名從大到小排列
    @Override
    public int compareTo(Object o) {
        if (o instanceof User) //判斷o是否是User的實例
        {
            User user = (User)o;
            return -this.name.compareTo(user.name);
        }
        else
            throw new RuntimeException("輸入的類型不匹配");
    }
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}

public class MyTreeSet {
    /*
     1. 向TreeSet中添加的數據, 要求是相同類的對象
     2. 兩種排序方式: 自然排序, 訂製排序

     3.自然排序中, 比較兩個對象是否相同的標準: compareTo()返回0, 不再是equals()
     4.訂製排序中, 比較兩個對象是否相同的標準: compare()返回0, 不再是equals()
     */
    public static void ts(){
        var set = new TreeSet<>();
        set.add(123);
        try {
            set.add("AA");
        } catch (ClassCastException e) {
            System.out.println("自訂錯誤：TreeSet 內元素型別必須一致，無法加入 AA。");
        }
        set.add(456);
        System.out.println(set);
        set.clear();
        //新增User對象到TreeSet
        set.add(new User("Tom", 25));
        set.add(new User("Alex", 33));
        set.add(new User("Davy", 24));
        set.add(new User("Jack", 47));
        set.add(new User("Jack", 33)); //注意打印這筆會消失, 原因寫在下方

        //搭配迭代器
        var iter = set.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
    }
    public static void ts2(){
        var com = new Comparator<>() {
            //按照年齡從小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof  User) //判斷o1, o2是否是 User 的實例
                {
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.age, u2.age);
                }
                else
                    throw new RuntimeException("輸入的類型不匹配");
            }
        };
        var set = new TreeSet<>(com); //使用訂製排序, 括號內要填入物件名稱
        set.add(new User("Tom", 25));
        set.add(new User("Alex", 33));
        set.add(new User("Davy", 24));
        set.add(new User("Jack", 47));
        set.add(new User("Jack", 33));

        //搭配迭代器
        var iter = set.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
    }

    static void main(String[] args) {
        ts2();
    }
}

/*
Set接口: 存儲無序的, 不可重複的數據
TreeSet: 可以按照添加對象的指定屬性, 進行排序

TreeSet 不能放「相同元素」。
如果判定相同，後加入的會被忽略（不會報錯）。
在 TreeSet 裡，「相同」主要看排序比較結果：
自然排序：看 compareTo(...)
自訂排序：看 Comparator.compare(...)
只要比較結果是 0，TreeSet 就認為是重複元素，不會再加入。

所以：
new User("Jack", 47)
new User("Jack", 33)

兩者 name 相同，compareTo 結果會是 0，因此第二個會被視為重複而消失。
註解「這筆會消失」正是這個原因。

如果我們不覆寫compare, compareTo, 那麼這兩個東西原先是什麼, 以及他們在做什麼 ??
請見 LanguagePractice/JavaPractice/compareTo_vs_compare.md

額外補充:
第16行, 第26行
當我們在類型裡面做了override具體是覆寫了什麼

@Override 的作用：
    告訴編譯器「這裡是覆寫父類別或介面的方法」
    如果方法名稱、參數型別、回傳型別不符合，編譯器會報錯
    可以避免把「想覆寫」寫成「新方法」
簡單說：
    compareTo：覆寫 Comparable 的方法，控制排序規則
    toString：覆寫 Object 的方法，控制輸出格式

TreeSet教學
https://www.youtube.com/watch?v=8Wxnw17OpYs
訂製排序
https://www.youtube.com/watch?v=SzLxAGZwnj4
instanceof教學
https://www.youtube.com/watch?v=kQtKcNX4O44
 */