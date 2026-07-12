//for(初始值; 終止條件; 每次執行的語句)
console.log("正序")
for (let i = 0; i < 10; i++) {
    console.log(i)
}
console.log("倒序")
for (let i = 10; i > -1; i--) {
    console.log(i)
    if (i === 0)
        console.log("結束")
}
var x = 0
while (x < 10){
    x++
    if (x === 4)
        continue
    console.log("第" + x + "隻綿羊")
}

/*
什麼是 do while ?
do while 會先做一遍 do {...} 中的內容, 再用 while(...) 決定迴圈要不要繼續下去
*/

let y = 0
do {
    y++
    console.log("第" + y + "本書")
}while (y < 10)

/*
For of 迭代可迭代的物件
適用對象: 陣列（Array）、字串（String）、Map、Set、arguments 物件、DOM NodeList 等。 
https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Statements/for...of
*/

let myString = 'hello'
let Iterator = myString[Symbol.iterator]()  

for(let str of myString){
    console.log(str)
}

/*
For in 迭代可列舉的屬性
主要用來循環遍歷物件（Object）的屬性名稱（property keys）。
https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Statements/for...in
*/

let user = {
    name: "Tracy",
    age: 25,
    role: "Manager"
};

for (let key in user){
    console.log(key)
    console.log(user[key])
}
/*
與其他熱門語言的直接對比：

1. Python

fruits = ["apple", "banana"]
for fruit in fruits:
    print(fruit)  # 輸出: apple, banana

2. Java

String[] fruits = {"apple", "banana"};
for (String fruit : fruits) {
    System.out.println(fruit); // 輸出: apple, banana
}

3. go

func main() {
    fruits := []string{"apple", "banana", "orange"}

    for i, fruit := range fruits {
        fmt.Printf("索引: %d, 數值: %s\n", i, fruit)
    }
}
    
#補充
var是什麼, let是什麼 ?
LanguagePractice/JSPractice/Compare4Language/var vs let變數作用範圍.md
*/