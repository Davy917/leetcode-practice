class Node{
    int val;
    Node next;
    Node(int val){
        this.val = val;
        this.next = null;
    }
}
class CycleLinkedList{
    Node head;
    Node tail;
    void AddAtTail(int val){
        Node newNode = new Node(val);
        if (head == null){
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next!=null){
            current = current.next;
        }
        current.next = newNode;
        current = newNode;
        tail = newNode;
    }
    void PrintList(Node head){
        System.out.print("[");
        Node current = head;
        while (current != null){
            System.out.print(current.val);
            if (current.next != null){
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}
class Solution142 {
    public Node detectCycle(Node head) {
        if (head == null){ return null; }
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null){//注意

            System.out.printf("slow = %s, ", slow.val);
            System.out.printf("fast = %s%n", fast.val);
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast){
                //一個從 head、一個從相遇點，同速前進，再次相遇的位置才是入口
                System.out.println("find loop!");
                Node current = head;
                while (current != slow){
                    System.out.printf("current = %s, ", current.val);
                    System.out.printf("slow = %s%n, ", slow.val);
                    current = current.next;
                    slow = slow.next;
                }
                System.out.print("ans = " + slow.val);
                return slow;
            }
        }
        return null;
    }

    static void main(String[] args) {
        Solution142 sol = new Solution142();
        CycleLinkedList list = new CycleLinkedList();
        int pos = 1;//pos = -1代表無環, pos = 0代表head形成環
        int[] nums = {3, 2, 0, -4, 8, 9};
        for (int i : nums){
            list.AddAtTail(i);
        }
        //list.PrintList(list.head);

        //手動製造環形鏈表
        Node current = list.head;

        for (int i=0; i<pos; i++){
            current = current.next;
        }
        list.tail.next = current;
        //System.out.println("tail.val = " + list.tail.val);
        sol.detectCycle(list.head);
    }
}
/**
 * detectCycle 逐步進行推倒
 *
 * head
 *   |
 *   v
 * (0)3 -> (1)2 -> (2)0 -> (3)-4 -> (4)8 -> (5)9
 *          ^                                |
 *          |________________________________|
 *                      cycle back to (1)2
 * 定義：
 * a：head 到入口(2)的距離（步數）
 * c：環長
 * b：入口到「第一次相遇點」的距離（沿環走）
 *
 *在這個例子：
 * 入口是 index 1，所以 a = 1（3 -> 2 走 1 步）
 * 環包含 (1)2 -> (2)0 -> (3)-4 -> (4)8 -> (5)9 -> 回到(1)2 所以 c = 5
 * b 要等我們算出第一次相遇點在哪裡。
 *初始化：
 * slow = head = (0)3
 * fast = head = (0)3
 * Round 1
 * slow at (1)2
 * fast at (2)0
 * Round 2
 * slow at (2)0
 * fast at (4)8
 * Round 3
 * slow at (3)-4
 * fast at (1)2
 * Round 4
 * slow at (4)8
 * fast at (3)-4
 * Round 5
 * slow at (5)9
 * fast at (5)9   <-- 第一次相遇點在 (5)9（值 9）
 * 當前:
 * a = 1, c = 5, b = 4
 * 公式:
 * a + b = k * c
 * 驗證:
 * a + b = 1 * c
 * 所以 k = 1
 *
 * 把一個指標 p1 放回 head (0)3,另一個指標 p2 留在相遇點 (5)9, 兩個都「每次走 1 步」
 * p1 at (0)3
 * p2 at (5)9
 * Step 1（各走 1）
 * p1 at (1)2
 * p2 at (1)2  <-- 第二次相遇點是入口 (1)2
 * 推倒式驗證:
 * 已知 a + b = k * c
 * 所以 a = k * c - b
 *「從相遇點回到入口」沿環的距離是 => c - b
 * 拆成 a = (k - 1) * c + (c - b)
 * 意思是：從相遇點走 a 步
 * 會先繞 (k-1) 圈（每圈 c 步）
 * 最後再走 (c-b) 步就回入口
 * 在這個例子 k = 1：
 * a = (1-1)*5 + (5-4) = 0 + 1 = 1
 * 相遇點 (9) 走 1 步正好回入口 (2)
 * 同時 head 走 1 步也到入口 (2)
 * 因此第二次相遇在入口
 */