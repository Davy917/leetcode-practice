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
const originAdd = function(a, b) {
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

/*
閉包函式 closure function
必看影片
https://www.youtube.com/watch?v=aHrvi2zTlaU
實戰
278-first-bad-version/solution.js

閉包（Closure） 的核心是：內部函式「記住」了外部函式的變數，即使外部函式已經執行完畢。
*/
function human(name) {
    function sayHi() {
        console.log(`Hi I am ${name}`)
    }
    function sayHowYouFeel() {
        console.log(`${name} is feeling good!`)
    }
    return{
        sayHi,
        sayHowYouFeel
    }
}
const sina = human('Sina')
const qoli = human('Qoli') //此時 qoli = 函式，會「記住」傳進去的 'Qoli'，可以稍後執行。
qoli.sayHi()
/*

human 執行完畢後，name 並沒有被銷毀，而是被回傳的 sayHi 和 sayHowYouFeel 各自閉包住了。
而且每次呼叫 human() 都會建立獨立的閉包，所以 sina 和 qoli 各自記住不同的 name。

sina 實際上就是這個物件：
{
    sayHi: [Function],
    sayHowYouFeel: [Function]
}

你可以這樣理解 JS 引擎底層發生的事：
human('Sina') 執行時，建立了一個環境：

const sina = {
    sayHi: function() { 記住 name='Sina' },
sayHowYouFeel: function() { 記住 name='Sina' }
}

而 qoli 也是同樣的結構，但它們記住的是另一份 name='Qoli'：
const qoli = {
    sayHi: function() { 記住 name='Qoli' },
sayHowYouFeel: function() { 記住 name='Qoli' }
}
所以 sina 和 qoli 看起來結構一樣，但各自閉包了不同的資料，互不干擾。
這也是為什麼閉包常被用來模擬「私有變數」——name 從外面是改不到的，
只能透過 sayHi 和 sayHowYouFeel 來讀取。
*/