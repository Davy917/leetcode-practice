/*
    var 物件名 = { 鍵: 值(屬性) }
    var 物件名 = new Object()
 */
var dog = {
    name: "Tracy",
    age: 12,
    bow: function () {
        console.log('bow bow')
    }
}
// 原型鏈dog.prototype ---> Object.prototype ---> null

console.log("My dog's name is", dog.name)
console.log("My dog's age is", dog.age)
dog.bow()

//擴增屬性 (注意要用字串)
dog['size'] = 'small'
console.log("My dog size is", dog.size)

//改變function
dog.bow = function (){
    console.log('bow!! bow!!')
}
dog.bow()

/*
閱讀文檔
LanguagePractice/JSPractice/MDLibrary/class and Prototype.md

dog 是 Object的實例而 Object 是 JavaScript 內建的全域建構子，是整個原型鏈的最頂端。

Object        // JS 內建的建構子函式
Object.create // 用 Object 來建物件的方法
Object.keys   // Object 提供的靜態方法

如何驗證?
dog instanceof Object === true
dog.__proto__ === Object.prototype
 */