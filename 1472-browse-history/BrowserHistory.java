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
    Node head;
    Node tail;
    public DoublyLinkedList(Node homepage){
        //用 homepage 初始化浏览器类。
        this.head = homepage;
        this.tail = homepage;
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
class BrowserHistory {
    int seq;
    Node firstNode, current;
    DoublyLinkedList channelList;
    public BrowserHistory(String homepage) {
        //用 homepage 初始化浏览器类。
        firstNode = new Node(homepage, 1);
        channelList = new DoublyLinkedList(firstNode);
        channelList.head = firstNode;
        channelList.tail = firstNode;
        current = firstNode;
    }

    public void visit(String url) {
        //从当前页跳转访问 url 对应的页面 。执行此操作会把浏览历史前进的记录全部删除。
        System.out.printf("你原本在瀏覽%s", current.nodeName + "。");
        Node newNode = new Node(url, ++seq);//注意++seq 與 seq++不同
        newNode.prev = current;//讓新節點知道「上一頁是誰」
        current.next = newNode;//讓舊的 current 知道「下一頁是誰」
        current = newNode;//告訴 BrowserHistory：「我現在人在 newNode 這頁了」（很關鍵）
        System.out.printf("訪問%s%n", url);
        channelList.printList(channelList.head);
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