/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
var containsNearbyDuplicate = function(nums, k) {
    const m = new Map()
    let index = 0
    for(let num of nums){
        if(m.has(num) && Math.abs(index - m.get(num)) <= k){
            return true
        }
        m.set(num, index)
        index++
    }
    return false
};
if(require.main === module){
    nums = [1, 2, 3, 1]
    console.log("Ans = ", containsNearbyDuplicate(nums, 3))
}
/*
python再簡化成js版本
*/