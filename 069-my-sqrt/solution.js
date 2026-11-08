/*
第一次寫的版本python
主思路看js
變化看java
*/
/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function(x) {
    let [l, m, r] = [0, 0, 46341]
    while(l + 1 < r){
        m = Math.floor((l + r) / 2)
        if(m * m > x){
            r = m
        } else {
            l = m
        }
    }
    return l
};
if(require.main === module){
    console.log("Ans = ", mySqrt(26))
    console.log(Math.pow((2 ** 31 - 1), 0.5))
}
/*
提示:
0 <= x <= 2 ** 31 - 1
Math.pow((2 ** 31 - 1), 0.5) 取整可得到46340
這題最大的答案是46340, 不可能超過它

自己寫的
思路:
畫狀態圖
假設要找26的平方根, 它實際落在5, 6之間, return 5
0, 1, 2, 3, 4, 5, 6, 7, 8, 9....

找規律:
m * m > x, m已經不可能是答案
m * m <= x, m還有可能是答案

根據這個pattern設計l, r初始值
l = 0       l本身或它右邊某數值是答案
r = 46341   r不是答案, 它左邊某數值是答案

while l, r不相鄰
    把l放到可能是答案的位置, r則用來篩選掉不可能的

最後l, r相鄰的時候, 就代表l一定是解答
*/