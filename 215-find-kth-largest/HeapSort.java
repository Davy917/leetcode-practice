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
        int lastNode = size - 1;
        int lastNode_parent = (lastNode - 1) / 2;

        while (lastNode_parent >= 0){
            heapify(tree,size, lastNode_parent--);
            System.out.println("back to buildHeap, lastNode_parent = " + lastNode_parent);
        }
    }

    void heapSort(int[] tree, int size){
        buildHeap(tree, size);
        //迴圈中的i就是index
        int index = size - 1;
        int temp = 0;
        while (index >= 0){
            temp = tree[0];
            tree[0] = tree[index];
            tree[index] = temp;
            heapify(tree, index, 0);//注意先後順序
            index--;//注意先後順序
            //debug
            System.out.println("back to heapSort");
            System.out.println(Arrays.toString(tree));
        }
    }
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] tree = {4, 10, 3, 5, 1, 2};
        int[] tree2 = {4, 5, 1, 3, 2};
        int size = 5;
        //heapSort.heapify(tree, size, 0);
        heapSort.heapSort(tree2, size);
        //heapSort.buildHeap(tree2, size);
        System.out.println("ans = " + tree2[tree2.length - 5]);
    }
}