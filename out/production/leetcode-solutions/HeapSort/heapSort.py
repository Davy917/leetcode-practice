class heapSort:
    def heapify(self, tree: list, size: int, parent: int) -> None:
        leftchild = 2 * parent + 1
        rightchild = 2 * parent + 2
        largest = parent

        if  leftchild < size and tree[leftchild] > tree[largest]:
            largest = leftchild

        if rightchild < size and tree[rightchild] > tree[largest]:
            largest = rightchild

        if largest != parent:
            self.swap(tree, largest, parent)#swap過後, largest就變成原parent的index
            self.heapify(tree, size, largest)

    def build_heap(self, tree: list, size: int) -> None:
        lastNode = size - 1
        lastNodeParent = (lastNode - 1)//2
        for i in range(lastNodeParent, -1, -1):
            self.heapify(tree, size, i)

    def heap_sort(self, tree: list, size: int) -> None:
        self.build_heap(tree, size)
        index = size-1
        while index > 0:
            self.swap(tree, index, 0)
            self.heapify(tree, index, 0)
            index -= 1

    def swap(self, tree: list, i, j):
        temp = tree[i]
        tree[i] = tree[j]
        tree[j] = temp

if __name__ == "__main__":
    tree = [4, 10, 3, 5, 1, 2]
    print(tree)
    #heapSort().build_heap(tree, 6)
    heapSort().heap_sort(tree, 6)
    print(tree)