import LinkedList.LinkedList;
import LinkedList.ListNode;

class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fst = dummy, sec = dummy;
        int i = -1; //因為從dummy出發, 所以初始化成-1, 要初始化成 0 也行
        while (i < n-1){
            fst = fst.next;
            i++;
        }
        while (fst.next != null){
            fst = fst.next;
            sec = sec.next;
        }
        if (sec.next != null)
            sec.next = sec.next.next;
        if (sec == dummy)
            head = sec.next;
        return head;
    }

    static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        LinkedList ll = new LinkedList();
        ListNode head = ll.buildLinkedList(nums);
        ll.printList(head); //執行前
        Solution19 sol = new Solution19();
        ll.printList(sol.removeNthFromEnd(head, 2)); //執行後
    }
}
/*
自己寫的
進階嘗試: 實現一趟掃描
也就是說, 知道現在已經走到終點 + 知道終點往前 n-1 個節點要被刪掉

想到什麼?
1. 快慢指針, 快指針走到終點的當下, 慢指針剛好拿來刪掉節點
2. 再來是涉及到刪除新增節點, 用dummy會比較方便

針對第一點再細想:
快慢指針之間的距離 == n, 這樣快指針走到終點, 慢指針剛好刪掉它的下一個
所以嚴格來說也不是快慢指針, 而是等速等距前進的雙指針, 更像是滑動窗口

代碼結構:
宣告dummy
把第一, 第二指針放在dummy
for迴圈讓第一指針停在窗口右邊界, 此時窗口已形成
兩個指針同時向右移動, 走到底為止
最後, sec的右鄰居就是倒數第 n 個, 把它刪除
 */