/*
自己寫的js
官方解答java, python
*/
/**
 * @param {character[][]} board
 * @return {boolean}
 */
var isValidSudoku = function(board) {
    let num
    const exist = new Set()
    for (let row = 0; row < 9; row++) {
        for (let col = 0; col < 9; col++) { 
            num = board[row][col]
            if(num == ".")
                continue
            if(exist.has(num))
                return false
            else
                exist.add(num)
        }
        exist.clear()
    }

    for (let col = 0; col < 9; col++) {
        for (let row = 0; row < 9; row++) { 
            num = board[row][col]
            if(num == ".")
                continue
            if(exist.has(num))
                return false
            else
                exist.add(num)
        }
        exist.clear()
    }

    for (let row = 0; row < 9; row += 3) {
        for (let col = 0; col < 9; col += 3) {
            for (let i = 0; i < 3; i++) {
                for(let j = 0; j < 3; j++){
                    newRow = row + i
                    newCol = col + j
                    num = board[newRow][newCol]
                    if(num == ".")
                        continue
                    if(exist.has(num))
                        return false
                    else
                        exist.add(num)
                }
            }
            exist.clear()
        }
    }
    return true
};
if(require.main === module){
    board = 
    [["5","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    console.log("Ans = ", isValidSudoku(board))
}
/*
每一行1~9最多只能出現一次
每一列1~9最多只能出現一次
每一格1~9最多只能出現一次

把上面三個檢查都做完, 一定可以找到答案

有辦法更優嗎?
暫時沒想法, 先把暴力解寫出來

暴力解:
1. 檢查行
2. 檢查列
3. 檢查格

這種寫法的執行用時已經很優秀了, 為了可讀性沒必要再進一步修改
細講一下檢查格的部分, 如果不把圖畫出來, 真的會想很久
把圖畫出來, 座標標記出來, 再逐步實現就會很簡單了
*/