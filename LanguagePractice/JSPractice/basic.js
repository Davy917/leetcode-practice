//三元運算子
3 > 1 ? console.log("true"): console.log("false")

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

// console.log(k) //❌ ReferenceError: Cannot access 'k' before initialization
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
LanguagePractice/JSPractice/Compare4Language/var vs let變數作用範圍.md
 */

//const 宣告常數之後是不能修改值的
const dog = 'Peter'
//const 也可以用來定義物件, 而且可以改變物件中的屬性
const person = {
    name: 'huang',
    age: 20
}
person['name'] = 'Jason'
console.log(person)

// 解構賦值, 能夠一次給多個變數賦值
// 常數
let [g,h,j] = [1,2,3]
console.log(a)
// 物件
let {a: val, b: val2} = {d:111, b: 222}

// 樣板字面值, 使用反引號 ` 來組合字串，直接在字串內插入變數，無須再使用繁瑣的 + 號連接。

let name = 'Tom'
let str = `hello ${name}`
console.log(str)

/*...擴展運算子
見文檔
LanguagePractice/Compare4Language/Spread operator.md
*/

let arr1 = [1,2,3,4,5]
console.log(Math.min(arr1)) // 會印出NaN因為 Math.min 不接受 Array
console.log(Math.min(...arr1)) // 相當於把 1, 2, 3, 4, 5 拆出來，放進新陣列中,才能得到預期中的最小值

const fruits = ['apple', 'banana'];
const veggies = ['tomato', 'potato'];

// 合併兩個陣列
const food = [...fruits, ...veggies]; 
console.log(food); // ['apple', 'banana', 'tomato', 'potato']

// 在中間插入其他元素
const moreFood = ['bread', ...fruits, 'milk'];
console.log(moreFood); // ['bread', 'apple', 'banana', 'milk']

//執行代碼 node LanguagePractice/JSPractice/basic.js