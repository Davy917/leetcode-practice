/*
官方解答看java
自己寫的看js
*/
var RandomizedSet = function() {
    this.map = new Map()
    this.list = []
};

/** 
 * @param {number} val
 * @return {boolean}
 */
RandomizedSet.prototype.insert = function(val) {
    if(this.map.has(val))
        return false
    const n = this.list.length
    this.map.set(val, n)
    this.list.push(val)
    return true
};

/** 
 * @param {number} val
 * @return {boolean}
 */
RandomizedSet.prototype.remove = function(val) {
    if(!this.map.has(val))
        return false
    const n = this.list.length-1
    let [val_index, last_index] = [this.map.get(val), this.map.get(this.list[n])]
    console.log(val_index, last_index)
    this.map.set(val, last_index)
    this.map.set(this.list[n], val_index);
    [this.list[last_index], this.list[val_index]] = [this.list[val_index], this.list[last_index]]
    this.list.pop()
    this.map.delete(val)
    return true
};

/**
 * @return {number}
 */
RandomizedSet.prototype.getRandom = function() {
    const randomIndex = Math.floor(Math.random() * this.list.length);
    return this.list[randomIndex]
};

if (require.main === module){
    const randomizedSet = new RandomizedSet();
    randomizedSet.insert(1)
    randomizedSet.insert(3)
    randomizedSet.insert(6)
    randomizedSet.insert(2)
    randomizedSet.insert(7)
    randomizedSet.remove(6)
    console.log(randomizedSet.getRandom())
}
/*
自己寫的
這題主要考察的是 getRandom 的實現, 最直覺的就是用set()然後寫到getRandom就會發現卡住寫不下去

insert都沒問題, 主要是remove, getRandom 很難做到O(1)
一般陣列[1, 3, 6, 2, 7], 直接拔掉[6]變成[1, 3, 2, 7] 這種操作不算O(1)

真正的O(1)是
[1, 3, 6, 2, 7]
[1, 3, 7, 2, 6] <---6跟最後一位交換
[1, 3, 7, 2] <---把6彈出來

有想到這一層才有機會寫出來, 而且只用Set + list很難做到上面的操作, 要用Map + list
map[數值: 該數值在list的下標]

remove時list要做上面的O(1)操作, 同時map也要跟著改動, 代碼直接看RandomizedSet.prototype.remove
*/