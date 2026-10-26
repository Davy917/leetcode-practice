/**
 * @param {string} jewels
 * @param {string} stones
 * @return {number}
 */
var numJewelsInStones = function(jewels, stones) {
    const exist = new Set()
    let result = 0
    for(let jewel of jewels){
        exist.add(jewel)
    }
    for(let stone of stones){
        if(exist.has(stone))
            result++
    }
    return result
};
if(require.main === module){
    const jewels = "aA"
    const stones = "aAAbbbb"
    console.log("Ans = ", numJewelsInStones(jewels, stones))
}
/*
自己寫的
*/