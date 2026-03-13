import java.util.List;

class Node{
    int val;
    Node prev;
    Node next;
    Node child;
    Node(int val, Node prev, Node next, Node child){
        this.val = val;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }
}
class LinkedList{
    Node head;
    Node tail;
    void AddAtTail(Node newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
            System.out.printf("add %s at head%n", newNode.val);
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        System.out.printf("add %s at tail%n", newNode.val);
    }
}
class Solution430 {
    public Node flatten(Node head) {
        return head;
    }

    static void main(String[] args) {
        Integer[] nums = {1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12};
        LinkedList mainList = new LinkedList();
        for(Integer i : nums){

        }
    }
}