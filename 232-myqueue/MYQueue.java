import java.util.*;

class MyQueue {
    private Deque<Integer> stack;
    private Deque<Integer> temp;
    private int t;
    public MyQueue() {
        this.stack = new ArrayDeque<>();
        this.temp = new ArrayDeque<>();
        this.t = 0;
    }

    public void push(int x) {
        stack.push(x);
        if (stack.size() == 1) {
            t = x;
        }
    }
    public int pop() {
        int n = stack.size();
        for (int i = 1; i < n; i++) {
            int toAdd = stack.pop();
            temp.push(toAdd);
        }
        int toReturn = stack.pop();
        int m = temp.size();
        for (int i = 0; i < m; i++) {
            int toAdd = temp.pop();
            if (i == 0)
                t = toAdd;
            stack.push(toAdd);
        }
        return toReturn;
    }

    public int peek() {
        return t;
    }
    public boolean empty() {
        return stack.isEmpty();
    }
}
/*
自己寫的

相似題
225-mystack/solution.py

這題考察的依然是pop的實作

預期結果:
[1, 2, 3] after pop--->[2, 3], 1

步驟:
Step1:
stack = [1, 2], temp = [3]
Step2:
stack = [1], temp = [3, 2]
Step3:
stack = [], temp = [3, 2] #toReturn = 1
Step4:
stack = [2], temp = [3]
Step5:
stack = [2, 3], temp = []

代碼結構:
stack尾端取出, 加入temp
stack尾端取出, 加入temp
stack尾端取出, toReturn = 1
temp尾端取出, 加回stack
temp尾端取出, 加回stack

結論:
長度為n的stack, 從尾端彈出n次, 第n次彈出的就是我們要的值
做完上面步驟之後, 要再把temp加回去stack, 這樣才能確保stack的正確性

每pop一次, 都需要重複上述動作
*/