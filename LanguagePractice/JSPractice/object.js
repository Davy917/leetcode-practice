//var 物件名 = { 鍵: 值(屬性) }
var dog = {
    name: "Tracy",
    age: 12,
    bow: function () {
        console.log('bow bow')
    }
}

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
 */