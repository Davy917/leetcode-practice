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