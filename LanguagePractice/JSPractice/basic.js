if (1 === 2)
    console.log('good')
else
    console.log('bad')

//三元運算子
3 > 1 ? console.log("true"): console.log("false")

var x = 1; //賦值運算子

// == vs ===
console.log("111 == '111", 111 == '111')
console.log("111 === '111", 111 === '111')

//次方運算子
var a = 2
console.log("a的5次方 = " + a ** 5)

//1.全域變數
var y = 1
function variable() {
    //2.區域變數
    var x = 1
}
//3.不是變數 => 全域的屬性
y = 3
/*
什麼是屬性, 什麼是物件
LanguagePractice/JSPractice/object.js
 */

//let vs var
console.log(z) // undefined （已宣告，未賦值）
var z = 10
console.log(z)

console.log(k) //❌ ReferenceError: Cannot access 'k' before initialization
let k = 10
console.log(k)

/*

var 會被提升（hoisting），所以在宣告前存取會得到 undefined
let 不能在宣告前存取，會報 ReferenceError

補充說明：
1.
不是「全域」的問題
    var 是「函式作用域」（function-scoped），let 是「區塊作用域」（block-scoped，{}內）
2.
let 的「暫時死區」(TDZ)
    let 從進入作用域到實際執行宣告行，這段期間存取會報錯（不是 undefined）
md文檔
LanguagePractice/JSPractice/MDLibrary/var vs let變數作用範圍.md
 */