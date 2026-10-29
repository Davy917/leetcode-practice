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

        if(leftChild < this.size && this.heap[smallest][1] > this.heap[leftChild][1])
            smallest = leftChild

        if(rightChild < this.size && this.heap[smallest][1] > this.heap[rightChild][1])
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
        if(parent >= 0 && this.heap[parent][1] > this.heap[current][1]){
            [this.heap[parent], this.heap[current]] = [this.heap[current], this.heap[parent]]
            current = parent
            siftUp.call(this, current)
        }
    }

    Heap.prototype.peek = function(){
        return this.heap[0][1]
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
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var topKFrequent = function(nums, k) {
    const freq = new Map()
    const minHeap = new Heap()
    for(let num of nums){
        if (freq.has(num))
            freq.set(num, freq.get(num)+1)
        else
            freq.set(num, 1)
    }
    for(let pair of freq){
        if(minHeap.size < k)
            minHeap.push(pair)
        else if(pair[1] > minHeap.peek()){
            minHeap.pop()
            minHeap.push(pair)
        }
    }
    return minHeap.heap.map(pair => pair[0])
};

if (require.main === module){
    const nums = [1,1,1,2,2,3]
    console.log("Ans = ", topKFrequent(nums, 1))
}
/*
基本解:
設計一個最大堆, 出現頻率最高的放堆頂, 取K次

進階解:
維護一個容量為k的最小堆, 如果有出現次數大於堆頂的, 就把堆頂元素換掉

最後整個最小堆就是答案

堆的實現直接拿這邊的來改:
datastructure\PriorityQueue\Heap.js
*/