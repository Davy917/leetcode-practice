//官方解答
import java.util.*;

class Solution {
    public static boolean isValidSerialization(String preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int n = preorder.length();
        int i = 0;
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',')
                i++;
            else if (preorder.charAt(i) == '#') {
                int top = stack.pop() - 1;
                if (top > 0) //如果還有剩餘槽位
                    stack.push(top); //重新壓入棧
                i++;
            }
            else{
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0)
                    stack.push(top);
                stack.push(2);
            }
            System.out.println(stack);
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println("Ans = " + isValidSerialization(preorder));
    }
}
/*
Stack方法練習
datastructure/Stack/StackFuncPractice.java

官方解答
https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/solutions/650583/yan-zheng-er-cha-shu-de-qian-xu-xu-lie-h-jghn/

FAQ:
第23行的while迴圈作用是什麼
當我們遍歷到數字節點時, 還要考慮到該數字可能是十位數或百位數以上, 所以我們需要用這個while迴圈跳過此數字

第11行會在什麼情境下觸發

以示例 "9,#,#,1" 为例：
初始: 栈 = [1]

遇到 '9':
  - 消耗1个槽位，新增2个
  - 栈 = [2]

遇到第一个 '#':
  - 消耗1个槽位
  - 栈 = [1]

遇到第二个 '#':
  - 消耗1个槽位
  - 栈 = []  ← 栈变为空

遇到 '1':
  - 进入 while 循环
  - 检查 if (stack.isEmpty()) → true
  - 返回 false

关键理解：
    栈为空表示当前树的所有槽位都已被填满
    但如果序列还有剩余字符（i < n），说明有多余的节点无法放置
    这违反了前序序列化的规则
*/