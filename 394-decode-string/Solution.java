import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
class Solution394 {
    public static String decodeString(String s) {
        //初始化
        Deque<Object[]> stack = new ArrayDeque<>();
        String str = "";
        String num = "";
        for (char c : s.toCharArray()){
            System.out.println("c = " + c);
            switch (c){
                case '[':
                    Object[] temp = new Object[2];
                    temp[0] = num;
                    temp[1] = str;
                    stack.push(temp);
                    num = "";
                    str = "";
                    break;
                case ']':
                    Object[] popOut = stack.pop();
                    String m = (String) popOut[1]; //彈出的文字
                    int n = Integer.parseInt((String) popOut[0]); //彈出的數字
                    str = m + str.repeat(n);
                    break;
                default:
                    int index = 'z' - c; // 'a'對應97, 'z'對應122
                    if (0 <= index && index <= 25) //介在0 ~ 25之間的就是英文
                        str += c;
                    else //其它的都數字
                        num += c;
                    break;
            }
            System.out.printf("num = %s, str = %s, stack = %s\n", num, str, Arrays.deepToString(stack.toArray()));
        }
        return str;
    }

    public static void main(String[] args) {
        String s = "3[a2[c]2[b]]";
        String s2 = "100[leetcode]";
        System.out.println("Ans = " + decodeString(s));
    }
}
/*
看視頻題解得到的思路:
https://leetcode.cn/problems/decode-string/

"3[a2[c]2[b]]"

初始狀態
num = 0, str = "", stack = []

遇到3
num = 3, str = "", stack = []
遇到[
num = 0, str = "", stack = [[3,""]]<----入棧
遇到a
num = 0, str = "a", stack = [[3,""]]
遇到2
num = 2, str = "a", stack = [[3,""]]
遇到[
num = 0, str = "", stack = [[3,""],[2,"a"]]<-----入棧
遇到c
num = 0, str = "c", stack = [[3,""],[2,"a"]]
遇到]
num = 0, str = "acc", stack = [[3,""]]----->出棧
遇到2
num = 2, str = "acc", stack = [[3,""]]
遇到[
num = 0, str = "", stack = [[3,""], [2, "acc"]]<-----入棧
遇到b
num = 0, str = "b", stack = [[3,""], [2, "acc"]]
遇到]
num = 0, str = "accbb", stack = [[3,""]]----->出棧
遇到]
num = 0, str = "accbbaccbbaccbb", stack = []----->出棧

stack加入邏輯
[num, str]
stack彈出元素與當前str組合的邏輯:
str = 彈出的文字 + (str * 彈出的數字)
str = m + (str * n)
代碼結構:
for char : s{
    遇到 [ :
        1. 入棧[num, str]
        num, str初始化
    遇到 ] :
        1. popOut 出棧
        2. str = 彈出的文字 + (彈出的數字 * str)
    遇到文字:
        str = 文字
    遇到數字:
        num = 數字
}
*/