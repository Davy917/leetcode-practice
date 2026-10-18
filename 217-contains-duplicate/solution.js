/**
 * @param {number[]} nums
 * @return {boolean}
 */
var containsDuplicate = function(nums) {
    const s = new Set()
    for (let num of nums){
        if (s.has(num)) {
            return true
        }
        s.add(num)
    }
    return false
};
if(require.main === module){
    nums = [1,7,9,8,3,4,5,6,4,2]
    console.log("Ans = ", containsDuplicate(nums))
}