const steven = {
    name: 'Steven',
    phoneBattery: 70,
    charge: function(level1, level2){
        this.phoneBattery = level1 + level2
    }
}
const becky = {
    name: 'Becky',
    phoneBattery: 30,
}

steven.charge(50, 50)
console.log("Steven charge", steven)
steven.charge.call(becky, 30, 30)
console.log("Becky use call", becky)
steven.charge.apply(becky, [40, 40]) //apply差別在於它接收陣列
console.log("Becky use apply", becky)
const beckyCharge = steven.charge.bind(becky) //bind差別在於它不立即執行, 而是回傳一個綁好this的新函式
beckyCharge(50, 50) //想使用時再調用
console.log("Becky use bind", becky)
/*
執行:
node ./LanguagePractice/JSPractice/CallApplyBind.js

教學影片:
https://www.youtube.com/watch?v=fYaoJLDuVdg

call實戰:
datastructure\PriorityQueue\Heap.js
*/