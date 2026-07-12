isBadVersion = function(version) {
    return version >= 4;
};

/**
 * @return {function}
 * @param isBadVersion
 */
var solution = function(isBadVersion) {
    return function(n) {
        let [left, right] = [1, n]
        while (left < right){
            const mid = Math.floor(left + (right - left) / 2)
            if (!isBadVersion(mid))
                left = mid + 1
            else
                right = mid
        }
        return right
    };
};
if (require.main === module){
    const finder = solution(isBadVersion)
    console.log(`Ans = ${finder(5)}`)
}

/*
[FAQ]
問題 1.
mid 使用const 或是 let 宣告, 有差別嗎?

這取決於 `mid` 的宣告位置：

1. 在 `while` 迴圈「內部」宣告：
   每次迭代都會建立新的區塊作用域。此時 `let` 和 `const` 在邏輯與效能上無差別。推薦使用 `const`，因為 `mid` 在該次迭代中不應被重新賦值，可避免意外修改。

2. 在 `while` 迴圈「外部」宣告：
   必須使用 `let`。因為 `mid` 需要在每次迴圈中被重新賦值，若用 `const` 會引發 `TypeError`。

問題 2.
當我們寫這一句的時候, 實際上finder是什麼
const finder = solution(isBadVersion)

這行執行時：
    1.solution(isBadVersion) 被呼叫
    2.solution 內部 return function(n) { ... } 執行
    3.返回一個新的函式給 finder
所以 finder 是 closure（閉包函式），它：
    •可以直接使用 isBadVersion 參數
    •等著接收參數 n 來執行二分搜尋

// finder 就是這個函式
finder = function(n) {
    let [left, right] = [1, n]
    while (left < right) {
        // ... 搜尋邏輯
    }
    return right
}

// 所以下一行呼叫 finder(5)，就是執行那個返回的函式
console.log(`Ans = ${finder(5)}`)  // finder 被當成函式呼叫

簡單來說： finder = 函式，可以直接執行 finder(5)。

更多範例:
查看閉包函式
LanguagePractice/JSPractice/function.js
 */