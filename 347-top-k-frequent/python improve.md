## 效能瓶頸在哪？

既然邏輯沒錯，執行時間不理想主要來自：

1. **自寫遞迴 `heapify`** — Python 函式呼叫的開銷遠大於內建 `heapq`（底層 C 實作）
2. **固定迭代 20001 次** — 即使只有 10 個獨特元素，也要跑完 20001 個 slot
3. **逐一插入而非批量建堆** — 先收集 K 個元素再一次 `heapify` 會比逐個 push 快

## 優化建議（保持你的 Min-Heap 思路）

```python
import heapq
from collections import Counter

class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        count = Counter(nums)
        
        min_heap = []
        for num, freq in count.items():
            heapq.heappush(min_heap, (freq, num))
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        
        return [num for freq, num in min_heap]
```

改動只有三點：
- `Counter` 取代手動陣列計數 → 只遍歷存在的元素
- `heapq` 取代自寫 heapify → C 級效能
- `(freq, num)` tuple 入堆 → 自動處理頻率相同的不同元素

你可以試試看，用時的差異應該會很明顯。