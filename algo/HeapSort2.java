public class HeapSort2 {
    static void heapify(int[] tree, int size, int parent){
        if (parent >= size){//返回條件不會寫
            System.out.println("[EXIT] parent >= size (出界終止)");
            return;
        }
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        int largest = parent;
        if (leftChild < size && tree[leftChild] > tree[largest]){//注意要加 leftChild < size
            largest = leftChild;
        }
        if (rightChild < size && tree[rightChild] > tree[largest]){//注意要加 rightChild < size
            largest = rightChild;
        }
        //TODO
    }
    
    
    
    static void main(String[] args) {
        int[] tree = {4, 10, 6, 5, 2, 3};
        heapify(tree, 6, 0);
    }
}
