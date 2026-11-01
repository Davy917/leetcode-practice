function ListNode(val){
    this.val = val
    this.next = null
}
/**
 * @param {ListNode} head
 * @return {boolean}
 */
var hasCycle = function(head) {
    const dummy = new ListNode(1000000, head);
    let [slow, fast] = [dummy, dummy]
    while(fast.next !== null && fast.next.next !== null){
        slow = slow.next
        fast = fast.next.next
        if(slow === fast)
            return true
    }
    return false
};