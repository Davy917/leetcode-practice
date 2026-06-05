import java.util.HashSet;

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
        this.next = null;
    }
}
class Solution160 {
    //自己寫的hashSet版本
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        while (cur != null){
            set.add(cur);
            cur = cur.next;
        }
        ListNode visitor = headB;
        while (visitor != null){
            if (set.contains(visitor))
                return visitor;
            visitor = visitor.next;
        }
        return null;
    }
    //官方的雙指針
    public ListNode getIntersectionNode_v2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB){
            pA = pA.next;
            pB = pB.next;
            if (pA == null && pB != null){
                pA = headB;
            }
            if (pB == null && pA != null){
                pB = headA;
            }
        }
        return pA;
    }
}
/*
官方解答:
https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/811625/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/

目前最好的解釋來自:
https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/12624/intersection-of-two-linked-lists-shuang-zhi-zhen-l/

我来解释一下，为什么把链表连起来，就可以得到相交的部分。

首先是两个链表（约定，值相同代表同一节点，0 代表空节点）
A表：[1, 2, 3, 7, 8, 9]
B表：[4, 5, 7, 8, 9]

连接两个链表（表与表之间用 0 隔开）
AB表：[1, 2, 3, 7, 8, 9, 0, 4, 5, 7, 8, 9, 0]
BA表：[4, 5, 7, 8, 9, 0, 1, 2, 3, 7, 8, 9, 0]

观察连接后的两个表，可以发现相交的部分整齐的排列在末尾，
只需要逐个比较这两张表的节点，就能找到相交的起始位置。

如果没有相交会如何？会陷入死循环吗？
A表：[1, 2, 3]
B表：[4, 5]

连接两个链表（表与表之间用 0 隔开）
AB表：[1, 2, 3, 0, 4, 5, 0]
BA表：[4, 5, 0, 1, 2, 3, 0]

观察连接后的两个表，可以发现末尾相交的部分必然为空，
参照上面的逻辑，返回首个相同的节点，为空是符合题意的。

如果连接两表时，不用 0 隔开，表不相交时，就会陷入死循环。

但是写代码时，不可能往链表中插入空节点，所以就用一个指针，模拟遍历两个相交表的过程，
当指针指向空时，重新指向另一个链表的头节点，否则就指向下一个节点。
*/