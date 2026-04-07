package HeapSort;

import java.util.Arrays;

class HeapSort_basic {
    /**
     * buildHeap: 將陣列原地調整為最大堆（max-heap）。
     * heapify: 指定子樹進行最大堆化，使其符合父大於子。
     * heapSort: 使用堆排序演算法對陣列進行原地升序排序。
     * @param size 代表的是這顆樹裡面有多少個節點
     * @param parent 代表要被操作的父節點，預設它比左右子節點還大
     */
    void heapify(int[] tree, int size, int parent){
        if (parent >= size){
            System.out.println("[EXIT] parent >= size (出界終止)");
            return;
        }
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        int largest = parent;

        
        if (leftChild < size && tree[leftChild] > tree[parent]){
            System.out.println("leftChild " + tree[leftChild] + " > parent " + tree[parent]);
            largest = leftChild;
        }

        if (rightChild < size && tree[rightChild] > tree[largest]){
            System.out.println("rightChild " + tree[rightChild] + " > largest(" + tree[largest] + ")");
            largest = rightChild;
        }

        if (largest != parent){
            swap(tree, largest, parent);
            System.out.println("  After swap tree = " + Arrays.toString(tree));
            heapify(tree, size, largest);//注意這邊回傳給 parent 的是 largest
        } else {
            System.out.println("  [EXIT] largest == parent，已是 max-heap，無須交換");
        }
    }
    void buildHeap(int[] tree, int size){
        int lastNode = size - 1;
        int parent = (lastNode-1)/2;
        System.out.println(tree[parent]);
        for (int i = parent; i >= 0 ; i--) {
            //迴圈中的 i 就是 index 的意思
            heapify(tree, size, i);
        }
    }

    void heapSort(int[] tree, int size){
        buildHeap(tree, size);
        for (int i = size - 1; i >= 0 ; i--) {
            //迴圈中的 i 就是 index 的意思
            int temp = tree[0];
            tree[0] = tree[i];
            tree[i] = temp;
            heapify(tree, i, 0);
        }
    }
    void swap(int[] tree, int largest, int parent){
        int temp = tree[parent];
        tree[parent] = tree[largest];
        tree[largest] = temp;
    }
/*
heapify 過程
           4
        ／   ＼
      10       3
    ／   ＼   ／  ＼
   5      1 2<--------lastNode

           10
        ／   ＼
       4       3
    ／   ＼   ／  ＼
   5      1 2

           10
        ／   ＼
       5       3
    ／   ＼   ／  ＼
   4      1 2
 */
    public static void main(String[] args) {
        int[] tree = {4, 10, 3, 5, 1, 2};
        int size = 6;
        HeapSort_basic heapSort = new HeapSort_basic();
        
        System.out.println("====== Initial tree ======");
        System.out.println(Arrays.toString(tree));
        System.out.println("\n====== Start heapify ======");

        //下面三種方法選一種實現:
        heapSort.heapify(tree, size, 0);
        //heapSort.buildHeap(tree, size);
        //heapSort.heapSort(tree, size);

        System.out.println("\n====== Final result ======");
        System.out.println("tree = " + Arrays.toString(tree));
        for (int i = 0; i < size; i++)
            System.out.printf("tree[%d] = %d%n", i, tree[i]);
    }
}
// 代碼出處
// https://www.youtube.com/watch?v=j-DqQcNPGbE