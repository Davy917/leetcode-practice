import java.util.HashMap;
import java.util.Map;

//每一個channel是一個Node
class Node{
    String nodeName;
    int num;
    Node prev;
    Node next;
    Node(String nodeName, int num){
        this.nodeName = nodeName;
        this.num = num;
        this.prev = null;//注意
        this.next = null;//注意
    }
}

//寫一個雙鏈表，把每一個channel串在一起
class DoublyLinkedList{
    int size;
    Node head;
    Node tail;
    public DoublyLinkedList(Node homepage, int seq){
        //用 homepage 初始化浏览器类。
        this.head = homepage;
        this.tail = homepage;
        this.size = seq;
    }
    //從頭打印整個List
    void printList(Node head){
        if (head == null){ System.out.println("[]"); return;}
        System.out.print("[");
        Node current = head;

        while (current != null){
            System.out.printf("nodeName = %s", current.nodeName);
            System.out.printf(", seq = %s", current.num);
            current = current.next;
            if (current != null){
                System.out.println(", ");
            }
        }
        System.out.println("]");
    }
}
//map的鍵存的是channel的順位，值存的是channel的地址
class BrowserHistory {
    int seq;
    Node firstNode, current;
    DoublyLinkedList channelList;
    Map<Integer, Node> map;
    public BrowserHistory(String homepage) {
        //用 homepage 初始化浏览器类。
        seq = 1;
        firstNode = new Node(homepage, 1);
        channelList = new DoublyLinkedList(firstNode, seq);
        channelList.head = firstNode;
        channelList.tail = firstNode;
        map = new HashMap<>();
        map.put(seq, firstNode);
        current = firstNode;
    }

    public void visit(String url) {
        //从当前页跳转访问 url 对应的页面 。执行此操作会把浏览历史前进的记录全部删除。
        System.out.printf("你原本在瀏覽%s", current.nodeName + "。");
        Node newNode = new Node(url, ++seq);//注意++seq 與 seq++不同
        newNode.prev = channelList.tail;
        channelList.tail.next = newNode;//修改的是「舊尾巴節點」的 next 指標
        channelList.tail = newNode;//修改的是「鏈表物件」的 tail 欄位
        map.put(seq, newNode);
        System.out.printf("訪問%s%n", url);
        channelList.printList(channelList.head);
        current = channelList.tail;//每一次動作完都要記得更新current
    }

    public String back(int steps) {

        System.out.printf("你原本在瀏覽%s", current.nodeName + "。");

        for (int i = 0; i<steps; i++){
            if (current.prev == null){
                System.out.printf(" 后退到 %s%n", current.nodeName);
                return current.nodeName;
            }
            current = current.prev;
        }
        System.out.printf(" 后退到 %s%n", current.nodeName);
        return current.nodeName;
    }

    public String forward(int steps) {
        System.out.printf("你原本在瀏覽%s", current.nodeName + "。");
        for (int i=0; i<steps; i++){
            if (current.next == null){
                System.out.printf(" 前進到 %s%n", current.nodeName);
                return current.nodeName;
            }
            current = current.next;
        }
        System.out.printf(" 前進到 %s%n", current.nodeName);
        return current.nodeName;
    }


    static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        browserHistory.back(1);                   // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        browserHistory.back(1);                   // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        browserHistory.forward(1);                // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        browserHistory.forward(2);                // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        browserHistory.back(2);                   // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
        browserHistory.back(7);                   // 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */

/*
你有一个只支持单个标签页的 浏览器，最开始你浏览的网页是homepage，你可以访问其他的网站url也可以在浏览历史中后退steps步或前进steps步。

请你实现 BrowserHistory 类：

BrowserHistory(string homepage) ，用 homepage 初始化浏览器类。
void visit(string url) 从当前页跳转访问 url 对应的页面  。执行此操作会把浏览历史前进的记录全部删除。
string back(int steps) 在浏览历史中后退 steps 步。如果你只能在浏览历史中后退至多 x 步且 steps > x ，那么你只后退 x 步。请返回后退 至多 steps 步以后的 url 。
string forward(int steps) 在浏览历史中前进 steps 步。如果你只能在浏览历史中前进至多 x 步且 steps > x ，那么你只前进 x 步。请返回前进 至多 steps步以后的 url 。
 */