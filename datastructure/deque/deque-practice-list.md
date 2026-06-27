deque 相關知識直接看題目

java, python
優先看 239-max-sliding-window
再來看 1438-longest-subarray


golang

golang要創建一個隊列的時候應該怎麼寫

// 創建佇列
queue := []int{}

// 入佇列（尾部添加）
queue = append(queue, value)

// 出佇列（頭部移除）
value := queue[0]
queue = queue[1:]


在 Go 中用 queue := []*TreeNode{}（slice）作為佇列完全正確且推薦，原因如下：  

✅ 為什麼用 slice 當佇列是合理的？

1. Go 沒有內建的 deque 類型
    不像 Python 有 collections.deque，Java 有 ArrayDeque。
    Go 的標準庫中，container/list 是雙向鏈結串列（double-linked list），但效能不如 slice。


2. slice 作為佇列的優勢
    語法簡潔：無需額外 import 其他套件。
    記憶體友善：底層是動態陣列，記憶體連續，對快取（Cache）非常友善。
    官方認可：LeetCode 官方題解也採用這種方式。

3. 面試中的接受度
    面試官理解 Go 的語言特性。
    關鍵在於你能否解釋清楚：
        為什麼用 slice？
        時間與空間複雜度為何？
        出佇列時 queue = queue[1:] 會創建新的切片（slice header），但底層仍共享同一個陣列。

golang deque實戰
101-is-symmetric/solution.go