package JavaPractice;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/*
集合元素的遍歷操作, 使用迭代器Iterator接口
內部的方法: hasNext() 和 next()
 */
public class IteratorPractice {
    public static void IteratorTest(){
        var coll = new ArrayList<Integer>();
        coll.add(123);
        coll.add(456);
        coll.add(789);
        Iterator<Integer> iter = coll.listIterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
    static void main(String[] args) {
        IteratorTest();
    }
}

/*
迭代器說明:
實際使用方式從7:45開始看
https://www.youtube.com/watch?v=6SGkAM0DT5o&t=166s

圖解說明:
https://www.youtube.com/watch?v=zfB-kyu418g
 */