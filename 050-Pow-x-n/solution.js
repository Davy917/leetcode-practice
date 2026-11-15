/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function(x, n) {
    if(n === 0) return 1
    let N = Math.abs(n)
    let [k, m, sum] = [x, 1, 1]
    while(N > 0){
        while(m*2 <= N){
            k *= k
            m *= 2
        }
        N -= m
        m = 1
        sum *= k
        k = x
    }
    return n > 0 ? sum : 1 / sum
};
if(require.main === module){
    console.log("Ans = ", myPow(-1, 2147483647))
}
/*
有想到快速冪, 但底下這段邏輯沒想到, 所以還是沒寫出來
假設:
x = 2, n = 105, sum = 2 ** 105
2 ** 105 = (2 ** 64) * (2 ** 32) * (2 ** 8) * (2 ** 1)

快速冪:
64 * 2 > 105, 105 - 64 = 41
32 * 2 > 41, 41 - 32 = 9
8 * 2 > 9, 9 - 8 = 1
1 * 2 > 1, 1 - 1 = 0 迴圈結束
*/