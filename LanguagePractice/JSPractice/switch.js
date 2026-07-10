const score = '全部通過';
switch (score) {
    case '全部通過':
        console.log('帶你出國玩')
        break
    case '全部未通過':
        console.log('禁足')
        break
    default:
        console.log('不處分')
        break
}
console.log("總過通過多少科目 ? \n" + score)