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
Array.map() 介紹
https://developer.mozilla.org/zh-TW/docs/Web/JavaScript/Reference/Global_Objects/Array/map

map() 會回傳新陣列，所以可以賦值
*/

let fruitsMap = fruits.map(function (element){
    return fruits + ' is healthy'
})

console.log(fruitsMap)

/*
Array.foreach() 介紹
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
Array.filter() 介紹
返回 Array 中全部符合條件的元素
 */

let arr = [1,2,3,4,5,6,6,6,6,7,8,9]
let newArr = arr.filter(function (element){
    return element > 5
})
console.log("newArr = " + newArr)

/*
Array.find() 介紹
就算 Array 中有多筆符合條件, 也只會返回一筆
*/

let found = arr.find(function (num){
    return num === 6
})
console.log("found = " + found)

/*
Array.reduce() 介紹
*/
let numbers = [1,2,3,4,5,6]
let result = numbers.reduce(function(sum, current){
    sum += current
    return sum
}, 0)
console.log(result)