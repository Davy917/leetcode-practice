可以把 `dfs` 和 `findMaxFreq` 都收進主函式裡，利用閉包共用 `count` 和 `maxFreq`：

```js
var findFrequentTreeSum = function(root) {
    const count = new Map();
    let maxFreq = 0;

    function dfs(node) {
        if (!node) return 0;
        const sum = dfs(node.left) + dfs(node.right) + node.val;
        const freq = (count.get(sum) || 0) + 1;
        count.set(sum, freq);
        maxFreq = Math.max(maxFreq, freq);
        return sum;
    }

    dfs(root);

    const result = [];
    for (const [sum, freq] of count) {
        if (freq === maxFreq) result.push(sum);
    }
    return result;
};
```

**關鍵簡化點：**
- `dfs` 變成內部函式，不用額外傳 `count`
- 在 `dfs` 裡順便更新 `maxFreq`，省掉第一個迴圈
- `findMaxFreq` 只剩一個迴圈，直接內聯就好

整體從 3 個函式變成 1 個，邏輯不變但更緊湊 👍

> 順帶一提，你 `dfs` 裡 `null` 節點回傳的是 `null`，在 JS 裡 `null + 數字` 會變成 `0 + 數字`，雖然結果剛好對，但回傳 `0` 語意更清楚也更安全。