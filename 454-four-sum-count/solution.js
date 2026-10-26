/*
完整思路看js
*/
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @param {number[]} nums3
 * @param {number[]} nums4
 * @return {number}
 */
var fourSumCount = function(nums1, nums2, nums3, nums4) {
    const n = nums1.length
    const comb1 = new Map()
    const comb2 = [(n ** 2)]
    for (let i = 0; i < n; i++){
        for (let j = 0; j < n; j++){
            num = nums1[i] + nums2[j]
            if(comb1.has(num)){
                comb1.set(num, comb1.get(num)+1)
            } else{
                comb1.set(num, 1)
            }
            comb2[n*i+j] = nums3[i] + nums4[j]
        }
    }
    let result = 0
    for(num of comb2)
        if(comb1.has(-num))
            result += comb1.get(-num)
    return result
};
if (require.main === module){
    const nums1 = [1, 2]
    const nums2 = [-2, -1]
    const nums3 = [-1, 2]
    const nums4 = [0, 2]
    console.log("Ans = ", fourSumCount(nums1, nums2, nums3, nums4))
}
/*
自己寫的
每個數組等長, n = nums.length
兩個數組會有, n ** 2 種可能
四個數組會有, n ** 4 種可能

可以逐個擊破:
先把nums1, nums2所有的組合列出來形成comb1, 把nums3, nums4所有的組合列出來形成comb2
再把comb1跟comb2所有的組合都嘗試過, 等於0就把result++

題目沒有要求返回座標, 所以座標不重要, 數值才重要

帶入數值
const nums1 = [1, 2]
const nums2 = [-2, -1]
comb1 = [-1, 0, 0, 1]

const nums3 = [-1, 2]
const nums4 = [0, 2]
comb2 = [-1, 1, 2, 4]

最後:
comb1 = [-1, 0, 0, 1]
comb2 = [-1, 1, 2, 4]
一共兩組答案
comb1[0] + comb2[1] = 0
comb1[3] + comb2[0] = 0

沒有一定要用哈希表, 但用哈希表可以提速, 否則可能超時

comb1直接存成字典, comb2跟comb1比對會快很多
*/