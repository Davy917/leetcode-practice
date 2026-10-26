
var TwoSum = function() {
    this.nums = new Map()
};

/** 
 * @param {number} number
 * @return {void}
 */
TwoSum.prototype.add = function(number) {
    if(this.nums.has(number)){
        const appear = this.nums.get(number)
        this.nums.set(number, appear+1)
        return
    }
    this.nums.set(number, 1)
};

/** 
 * @param {number} value
 * @return {boolean}
 */
TwoSum.prototype.find = function(value) {
    for (const [num, _] of this.nums){
        let appear = this.nums.get(num)-1
        this.nums.set(num, appear)
        const target = value - num
        if(this.nums.has(target) && this.nums.get(target) > 0){
            this.nums.set(num, appear+1)
            return true
        }
        this.nums.set(num, appear+1)
    }
    return false
};

if(require.main === module){
    const ts = new TwoSum()
    ts.add(3)
    ts.add(2)
    ts.add(1)
    console.log(ts.find(2))
    console.log(ts.find(3))
    console.log(ts.find(4))
    console.log(ts.find(5))
    console.log(ts.find(6))
}
/*
直接拿Go版本來改的
*/