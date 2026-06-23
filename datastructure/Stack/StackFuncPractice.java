package Stack;

import java.util.*;
public class StackFuncPractice {
    static void Practice(){
        Deque<String> stack = new ArrayDeque<>();
        stack.push("first book");
        stack.push("second book");
        stack.push("third book");
        String topElement = stack.pop();
        System.out.println("topElement = " + topElement);
        System.out.println("Stack.size = " + stack.size());
    }
    public static void main(String[] args) {
        Practice();
    }
}
/*
stack相關題目看331題
331-is-valid-serialization/Solution.java

如果你在 Stack 已經沒有任何元素 的情況下呼叫 pop()，程式會直接崩潰並拋出異常（Exception）：  

    如果你用的是 java.util.Stack：會拋出 EmptyStackException。
    如果你用的是 ArrayDeque 或 LinkedList：會拋出 NoSuchElementException。
*/