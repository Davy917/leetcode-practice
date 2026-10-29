const Heap = (function(){
    function Heap(){
        this.heap = []
        this.size = 0

    }
    function heapify(parent) {
        if(parent >= this.size)
            return
        let leftChild = parent * 2 + 1
        let rightChild = parent * 2 + 2
        let smallest = parent

        if(leftChild < this.size && this.heap[smallest] > this.heap[leftChild])
            smallest = leftChild

        if(rightChild < this.size && this.heap[smallest] > this.heap[rightChild])
            smallest = rightChild

        if(smallest != parent){
            [this.heap[parent], this.heap[smallest]] = [this.heap[smallest], this.heap[parent]]
            heapify.call(this, smallest)   // ← 把「當前的 Heap instance」當作 buildHeap 內的 this
        }
    }
    function siftUp(index){
        const parent = Math.floor((index-1)/2)
        if(parent < 0)
            return
        let current = index
        if(parent >= 0 && this.heap[parent] > this.heap[current]){
            [this.heap[parent], this.heap[current]] = [this.heap[current], this.heap[parent]]
            current = parent
            siftUp.call(this, current)
        }
    }

    function buildHeap() { //目前用不到, 之後可能會用到
        const lastParent = Math.floor(this.size / 2) - 1
        for (let i = lastParent; i > -1; i--)
            heapify.call(this, i)
    }

    Heap.prototype.peek = function(){
        return this.heap[0]
    }

    Heap.prototype.push = function(num){
        this.heap.push(num)
        this.size++
        const n = this.size-1
        siftUp.call(this, n)
    }

    Heap.prototype.pop = function(){
        let n = this.size-1;
        [this.heap[0], this.heap[n]] = [this.heap[n], this.heap[0]];
        this.heap.pop()
        this.size--
        heapify.call(this, 0)
    }

    return Heap
})();

if (require.main === module){
    const minHeap = new Heap()
    minHeap.push(-1)
    minHeap.push(-2)
    minHeap.push(-3)
    minHeap.push(1)
    minHeap.push(-4)
    minHeap.pop()
    console.log(minHeap.heap)
}
module.exports = Heap

/*
調用範例:
347-top-k-frequent\solution.js

閉包:
const Heap = (function(){
                // ↑ 第一個括號：包裹「函式定義」
                // 內容：Constructor + private methods + Prototype methods
})();
// ↑↑ 第二個括號：「立即執行」函式，並傳回結果給 Heap

核心邏輯：
第一個 () = 「函式定義」的包裝
第二個 () = 「執行」那個函式
return Heap = 把公開的建構式傳回來

FAQ:
兩句的差異在哪裡?
[nums[parent], nums[smallest]] = [nums[smallest], nums[parent]]
[parent, smallest] = [smallest, parent]

第一句→ 交換的是 陣列 nums 裡兩個位置的值。
也就是真的改動 heap 內容（資料被交換了）。

第二句→ 交換的是 兩個變數本身（index 變數）。
nums 陣列內容不會因這句直接改變。

傳入call的參數都有包含this, 請問是什麼意思?
例如: buildHeap.call(this)
*/