/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect = function(nums1, nums2) {
    let[i, j] = [0, 0]
    nums1.sort((a, b) => a - b)
    nums2.sort((a, b) => a - b)
    let result = []
    while(i < nums1.length && j < nums2.length){
        if(nums1[i] === nums2[j]){
            result.push(nums1[i])
            i++
            j++
        } else if(nums1[i] > nums2[j]){
            j++
        } else {
            i++
        }
    }
    return result
};
if (require.main === module){
    const nums1 = [1,2,2,1]
    const nums2 = [2,2]
    console.log("Ans = ", intersect(nums1, nums2))
}
/*
自己寫的
思路
兩數組都排序
[1,1,2,2], 用i遍歷
[2,2], 用j遍歷

處理三種情況:
nums[i] == nums[j]   加入result
nums[i] > nums[j]    j往前 
nums[i] < nums[j]    i往前

寫的時候要在紙上畫狀態圖
*/