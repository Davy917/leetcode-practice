//兩種宣告方式
//1. function 名稱 (參數列表) { 函式內容 }
function helloWorldFunction(){
    console.log("hello world!")
}
helloWorldFunction()
//2. 型別 函式名 = function (參數列表) { 函式內容 }
var helloJSFunction = function (){
    console.log("hello JS!")
}
helloJSFunction()

/*
補充:
第二種方式為函式表達式, 必須在函式已經宣告之後才能調用它
函式表達式通常指把函式直接賦值給變量
 */

/*
回調函式 Callback Function - 把函數作為參數傳遞
java 要用 lambda才能
*/
function callbackFunc(myFunction){
    myFunction('Hi!')
}
callbackFunc(console.log)
console.log("console.log is a " + typeof (console.log) + "!")


//匿名函式
function anonymousFunc(myfunction){
    myfunction('Hi!')
}
anonymousFunc(function (message)
    {
        console.log("message is :", message)
    })

//立即函式 (第 2 種最符合立即函式的典型用法。)
//1. 加上 return（推薦）
var sayHi = function (name){
    let s = "Hi, " + name + " !"
    console.log(s)
    return s //注意
}('Peter')

//2. 直接執行，不指派
(function (name){
    let s = "Hi, " + name + " !"
    console.log(s)
    return s //注意
})('Peter')


// 箭頭函式寫法
const add = (a, b) => a + b; 

// 傳統寫法
const add = function(a, b) {
  return a + b;
};

/*
Python 寫法
double_num = lambda x: x * 2

Java 寫法
Function<Integer, Integer> doubleNum = x -> x * 2;

在計算機科學中，這種「匿名、簡短、可傳遞的函式」通常統稱為 Lambda 表達式（Lambda Expressions） 或 匿名函式（Anonymous Functions)
許多語言也同樣使用 => 或 -> 符號來表達。  
*/

const obj = {element1: 1, element2: 2}
function myFunc1(){
    console.log("output 1 ", this.element1)
    setTimeout(function(){
        console.log("output 2 ", this.element1) // 會得到undefined
    }, 1000)
}

myFunc1.call(obj) // call(obj) 的作用是將 myFunc1 內部的 this 強制綁定（bind）為 obj 物件。

/*
為什麼會得到undfined ?
關鍵點： 在非嚴格模式（non-strict mode）下，由 setTimeout 延遲呼叫的普通回呼函式，其內部的 this 預設會指向全域物件（Global Object）
，在瀏覽器環境中是 window，在 Node.js 環境中是 global。

可以透過以下幾種函式寫法來修正

箭頭函式（Arrow Function）
箭頭函式沒有自己的 this，它會繼承外層（定義時所在環境）的 this。此時外層的 this 是 obj。  
*/
function myFunc1(){
    console.log("output 1 ", this.element1)
    setTimeout(() => {
        // 這裡的 this 會繼承外層 myFunc1 的 this (即 obj)
        console.log("output 2 ", this.element1) 
    }, 1000)
}
/*
使用 .bind(this)
在傳入 setTimeout 的函式後面使用 .bind(this)，將當前的 this（即 obj）綁定到該函式中。  
*/
function myFunc1(){
    console.log("output 1 ", this.element1)
    setTimeout(function(){
        console.log("output 2 ", this.element1)
    }.bind(this), 1000) // 強制綁定外層的 this
}

/*
使用變數暫存 this（傳統做法）
在進入 setTimeout 之前，先用一個變數（通常命名為 self 或 that）把 this 存起來。  
*/
function myFunc1(){
    const self = this; // 這裡的 this 是 obj
    console.log("output 1 ", this.element1)
    setTimeout(function(){
        console.log("output 2 ", self.element1) // 使用暫存的變數
    }, 1000)
}