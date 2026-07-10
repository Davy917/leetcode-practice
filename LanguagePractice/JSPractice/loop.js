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

let y = 0
do {
    y++
    console.log("第" + y + "本書")
}while (y < 10)

/*
var是什麼, let是什麼 ?
LanguagePractice/JSPractice/MDLibrary/var vs let變數作用範圍.md

什麼是 do while ?
do while 會先做一遍 do {...} 中的內容, 再用 while(...) 決定迴圈要不要繼續下去
*/