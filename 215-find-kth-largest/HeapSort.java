import java.util.Arrays;

class HeapSort{
    void heapify(int[] tree,int size, int parent){
        if (parent >= size){
            return;
        }
        int leftChild = parent * 2 + 1;
        int rightChild = parent * 2 + 2;
        int largest = parent;
        System.out.println("leftChild index= " + leftChild + ", rightChild index = " + rightChild);
        if (leftChild < size && tree[leftChild] > tree[largest]){ //注意要加 leftChild < size
            System.out.println("leftChild > parent");
            largest = leftChild;
        }
        if (rightChild < size && tree[rightChild] > tree[largest]){ //注意要加 rightChild < size
            System.out.println("rightChild > parent");
            largest = rightChild;
        }
        if (largest != parent){
            swap(tree, largest, parent);
            System.out.println(Arrays.toString(tree));
            System.out.println("largest val " + tree[largest] + " largest index " + largest);
            heapify(tree, size, largest);
        }
    }
    void swap(int[] tree, int largest, int parent){
        int temp = tree[parent];
        tree[parent] = tree[largest];
        tree[largest] = temp;
    }

    void buildHeap(int[] tree, int size){
        //TODO buildHeap方法重寫
        //迴圈中的i就是index
        for (int i = size-1; i >= 0; i--) {
            heapify(tree, size, i);
            System.out.println("back");
            System.out.println(Arrays.toString(tree));
        }
    }

    void heapSort(int[] tree, int size){
        //迴圈中的i就是index
        for (int i = size-1; i >= 0; i--) {
            heapify(tree, i + 1, 0);
            int temp = tree[0];
            tree[0] = tree[i];
            tree[i] = temp;
            System.out.println("back");
            System.out.println(Arrays.toString(tree));
        }
    }
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] tree = {4, 10, 3, 5, 1, 2};
        int[] tree2 = {1, 2, 3, 4, 5, 10};
        int size = 6;
        //heapSort.heapify(tree, size, 0);
        //heapSort.heapSort(tree, 6);
        heapSort.buildHeap(tree2, size);
    }
}