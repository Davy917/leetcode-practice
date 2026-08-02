class KthLargest {
    PriorityQueue<Integer> pq;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>(k);
        for(int num: nums)
            add(num);
    }
    public int add(int val) {
        pq.add(val);
        if (pq.size() > k){
            pq.poll();
        }
        return pq.peek();
    }
    public static void main(String[] args) {
        int[] nums = {4,5,8,2};
        KthLargest kl = new KthLargest(3, nums);
        System.out.println("Ans = " + kl);
        System.out.println("add = " + kl.add(7));
    }
}
 /*
 一般 Queue（FIFO）：
入隊：3, 1, 5, 2
出隊：3 → 1 → 5 → 2    （誰先來誰先走）

PriorityQueue（優先權）：
入隊：3, 1, 5, 2
出隊：1 → 2 → 3 → 5    （誰最小誰先走）

在 Java 的 PriorityQueue，最常用就這幾組:「插入、刪除、讀取頂端」。

1. 插入
    offer(e): 插入元素，成功回傳 true（佇列有容量限制時，失敗回 false）
    add(e): 也能插入；若失敗會丟例外（一般 PriorityQueue 多半不會失敗）
    複雜度: 平均/最壞通常是O(logn)
2.  讀取（不刪除）
    peek(): 讀取堆頂元素（預設是最小值），空佇列回 null
    element(): 也是讀取堆頂，但空佇列會丟例外
    複雜度O(1)
3. 刪除堆頂
    poll(): 刪除並回傳堆頂，空佇列回 null
    remove(): 刪除並回傳堆頂，空佇列丟例外
    複雜度O(logn)
4. 刪除指定元素
    remove(Object o):刪除某個值（不是堆頂）
    複雜度: 先找元素約O(n), 再重排O(logn), 整體常看作O(n)
5. 其他常用
    size():目前元素數量,O(1)
    isEmpty:是否為空,O(1)
    clear():清空
    contains(): 是否包含某值
補充:
Java 預設是最小堆（min-heap）
*/