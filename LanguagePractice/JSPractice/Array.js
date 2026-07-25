var fruits = ['apple', 'orange', 'banana']

/*
map     批量改變陣列數值
pop     從陣列末尾彈出, 會改變陣列
push    從陣列末尾加入, 會改變陣列
slice   陣列切片 (]
reverse 陣列反轉
indexOf 想知道某個元素的下標

剩下的到這邊看
https://www.oxxostudio.tw/articles/201908/js-array.html
 */
fruits.pop()
fruits.push('pineapple')
console.log(fruits)
console.log(fruits.slice(0, 2))

/*
Array.map()
https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Global_Objects/Array/map

map() 會回傳新陣列，所以可以賦值
*/

let fruitsMap = fruits.map(function (element){
    return fruits + ' is healthy'
})

console.log(fruitsMap)

/*
Array.foreach()
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/forEach

forEach() 只執行循環，不回傳任何值（void）

等價
for (let i = 0; i < fruits.length; i++){
    console.log(fruits[i] + "is good")
}
*/

fruits.forEach(function (element) {
    console.log(element + " is good")
})

/*
Array.filter()
返回 Array 中全部符合條件的元素
 */

let arr = [1,2,3,4,5,6,6,6,6,7,8,9]
let newArr = arr.filter(function (element){
    return element > 5
})
console.log("newArr = " + newArr)

/*
Array.find()
就算 Array 中有多筆符合條件, 也只會返回一筆
*/

let found = arr.find(function (num){
    return num === 6
})
console.log("found = " + found)

/*
Array.reduce()
*/
let numbers = [1,2,3,4,5,6]
let result = numbers.reduce(function(sum, current){
    sum += current
    return sum
}, 0)
console.log(result)

/*
Array.indexOf() — 查找元素的索引
 */
arr = [1, 2, 3, 4];
arr.indexOf(3); // 2
arr.indexOf(5); // -1（找不到）

/*
includes() — 判斷是否存在
 */
arr.includes(3); // true
arr.includes(5); // false

/*
findIndex() — 根據條件找到索引
findIndex() 會遍歷陣列，回傳第一個符合條件的元素的索引；找不到則回傳 -1。

arr.findIndex(callback(當前元素, 當前索引, 陣列本身))
 */

    // 1. 找第一個偶數
    [1, 3, 4, 6].findIndex(n => n % 2 === 0); // 2（元素 4 的索引）

    // 2. 找第一個大於 10 的數
    [5, 8, 12, 15].findIndex(n => n > 10); // 2（元素 12）

    // 3. 找不到回傳 -1
    [1, 2, 3].findIndex(n => n > 10); // -1

    // 4. 搭配物件陣列
    const nodes = [
        { val: 1, depth: 0 },
        { val: 2, depth: 1 },
        { val: 3, depth: 1 },
    ];
    nodes.findIndex(n => n.val === 3); // 2

    // 5. 用 index 參數
    [10, 20, 30].findIndex((_, i) => i === 1); // 1

/*
some() / every() — 判斷是否有/全部符合
 */
arr.some(n => n > 3);  // true（至少一個）
arr.every(n => n > 0); // true（全部）

/*
Array.from(), Array.fill()
Array.from() 用來「製造陣列」的工具
Array.fill() 用來「填充陣列」的工具

實戰用法看
655-print-tree/solution.js

底下那一行可以建出這樣的陣列
[["","",""],["","",""]]
*/
let [m, n] = [2, 3]
let Ans = Array.from({ length: m }, () => Array(n).fill(""));
console.log(Ans)