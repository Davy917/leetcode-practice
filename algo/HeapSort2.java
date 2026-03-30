//全遞歸版本
import java.util.Arrays;
public class HeapSort2 {
    static void heapify(int[] tree, int size, int parent){
        if (parent >= size){//返回條件不會寫
            return;
        }
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        int largest = parent;
        if (leftChild < size && tree[leftChild] > tree[largest]){
            largest = leftChild;
        }
        if (rightChild < size && tree[rightChild] > tree[largest]){
            largest = rightChild;
        }
        if (largest != parent){
            swap(tree, largest, parent);
            heapify(tree, size, largest);
        }
    }

    static void buildHeap(int[] tree, int size){
        int lastNode = size - 1;
        int lastNodeParent = (lastNode - 1)/2;
        buildHeapRec(tree, size, lastNodeParent);
    }
    static void buildHeapRec(int[] tree, int size, int lastNodeParent){
        if (lastNodeParent < 0)
            return;
        heapify(tree, size, lastNodeParent);
        buildHeapRec(tree, size, lastNodeParent - 1);//注意不要寫成lastNodeParent--
    }

    static void heapSort(int[] tree, int size){
        buildHeap(tree, size);
        heapSortRec(tree, size);
    }
    static void heapSortRec(int[] tree, int size){
        if (size <= 1)
            return;
        swap(tree, size - 1, 0);
        heapify(tree, size - 1, 0);//注意 size - 1
        heapSortRec(tree, size - 1);
    }

    static void swap(int[] tree, int i, int j){
        int temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

    static void main(String[] args) {
        int[] tree = {4, 10, 6, 5, 2, 3};
        //heapify(tree, 6, 0);
        //buildHeap(tree, 6);
        heapSort(tree, 6);
        System.out.println(Arrays.toString(tree));
    }
}
