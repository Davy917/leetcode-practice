/**
 * @param {number[][]} mat1
 * @param {number[][]} mat2
 * @return {number[][]}
 */
var multiply = function(mat1, mat2) {
    const rows = mat1.length
    const cols = mat2[0].length
    let result = Array(rows).fill(null).map(() => Array(cols).fill(0))
    for (let m = 0; m < rows; m++)
        for (let n = 0; n < cols; n++)
            for (let k = 0; k < mat1[0].length; k++)
                result[m][n] += mat1[m][k] * mat2[k][n]
    return result
};

if (require.main === module){
    const mat1 = [[1,-5]]
    const mat2 = [[12],[-1]]
    console.log("Ans = ", multiply(mat1, mat2))
}
/*
自己寫的
一開始完全不懂矩陣乘法

先定義矩陣乘法的公式:
若矩陣 mat1 為 m × k，矩陣 mat2 為 k × n，則結果 為 m × n：

假設result是結果矩陣, 可知 result 是一個 m * n 矩陣
其中 m 是 mat1 貢獻的, n 是 mat2 貢獻的, k是共同邊
rows = mat1.length 一定大於 m
cols = mat2[0].length 一定大於 n

可以大概抽象程式碼
for (let m = 0; m < rows; m++)
    for (let n = 0; n < cols; n++)
        .......
            result[m][n] += ......    

上面的推論先記著

快速範例:
A = [[a, b],      B = [[e, f],
     [c, d]]           [g, h]]

C[0][0] = a×e + b×g
C[0][1] = a×f + b×h
C[1][0] = c×e + d×g
C[1][1] = c×f + d×h    


帶入數值:
mat1 = [[1, -5]]
mat2 = [[12], [-1]]
result[0][0] = 1 * 12 + (-5) * (-1) = 17
Ans = [[17]]


試著把數值用代碼的方式寫
result[0][0] = mat1[0][0] * mat2[0][0] + mat1[0][1] * mat2[1][0]

再進一步把共用邊填成k
result[0][0] = mat1[0][k] * mat2[k][0] + mat1[0][k] * mat2[k][0]

接著看上面的抽象程式碼, 並把 m, n 填上
result[m][n] = mat1[m][k] * mat2[k][n] + mat1[m][k] * mat2[k][n]

用三個迴圈就寫完了, m, n, k各一個迴圈

for (let m = 0; m < rows; m++)
    for (let n = 0; n < cols; n++)
        console.log("m = %d, n = %d", m, n) 建議寫到這邊先打印確認, 再往下
        
看不懂可以搭配go版本的測資再看一次
*/