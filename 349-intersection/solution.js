/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersection = function(nums1, nums2) {
    const s1 = new Set(nums1)
    const s2 = new Set(nums2)
    const result = []
    for(let num of s1)
        if(s2.has(num))
            result.push(num)
    return result
};

if (require.main === module){
    const nums1 = [1,2,2,1]
    const nums2 = [2,2]
    console.log("Ans = ", intersection(nums1, nums2))
}
//自己寫的