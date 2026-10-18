/*
官方解看js
自己寫的看python
*/
/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function(nums) {
    let s = 0
    for(num of nums){
        s ^= num
        console.log(s)
    }
    return s
};
if(require.main === module){
    nums = [1,2,1,2,4]
    console.log("Ans = ", singleNumber(nums))
}

/*

總結：為什麼 XOR 是唯一的標準答案？

XOR 利用了硬體層級的位元操作，它不需要額外的記憶體（只需一個變數存結果），且只需走訪陣列一次。

初始：s = 0
第 1 次：0 ^ 1 = 1 → 打印 1
第 2 次：1 ^ 2 = 3 → 打印 3
第 3 次：3 ^ 1 = 2 → 打印 2
第 4 次：2 ^ 2 = 0 → 打印 0
第 5 次：0 ^ 4 = 4 → 打印 4

XOR 的特性是：
a ^ a = 0
a ^ 0 = a

所以重複的數字會互相抵消掉，最後只剩下唯一出現一次的值。
*/