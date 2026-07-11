const myString = 'hello'
// 1. 手動迭代 (使用 next() 方法)
var iterator = myString[Symbol.iterator]()
let result = iterator.next()
while(!result.done){
    console.log(result.value)
    result = iterator.next()
}

// 2. 使用 for...of 迴圈
for (let c of myString)
    console.log(c) // 自動調用 Symbol.iterator

// 3. 使用展開運算符 (...)
const chars = [...myString]

/*
Iterator
https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Iteration_protocols


執行代碼:
node LanguagePractice/JSPractice/Iterator.js

[FAQ]
問題1. JS 迭代器的 done, 與 Java 的 hasNext()概念是否一致 ?

Java 的 hasNext()：
問法：「還有下一個嗎？」
有資料時回傳 true，沒資料時回傳 false。

JavaScript 的 done：
問法：「結束了嗎？」
有資料時為 false（還沒結束），沒資料時為 true（已經結束）。

問題2. myString[Symbol.iterator]() 是什麼意思?

拆解:
Symbol.iterator                 //JavaScript 內置符號，標記「這個對象可以被迭代」
myString[Symbol.iterator]       //取得字符串上的迭代方法函數
myString[Symbol.iterator]()     //執行這個方法，返回一個「迭代器對象」

類比：
const methodName = Symbol.iterator
const iteratorMethod = myString[methodName]     // 取得方法
const iterator = iteratorMethod()               // 執行方法獲得迭代器

更清楚的類比（Java vs JavaScript）

JavaScript 手動迭代
const iterator = myString[Symbol.iterator]()    // 獲取迭代器（類似 iterator()）
while (!iterator.next().done) {                 // 每次呼叫 next()
......
}

Java 手動迭代
Iterator<Character> iterator = myString.iterator();     // 獲取迭代器
while (iterator.hasNext()) {                            // 每次檢查 hasNext()
......
}
*/