from typing import List
class Solution1122:
    rowMap = {}
    columnMap = {}
    gridMap = {}

    def isValidSudoku(self, board: List[List[str]]) -> bool:
        for row in range(0, 9):
            for column in range(0, 9):
                c = board[row][column]
                if c == '.':
                    continue
                if Solution1122().checkRow(row, c) == False:
                    return False
                if Solution1122().checkColumn(column, c) == False:
                    return False
                if Solution1122().checkGrid(row, column, c) == False:
                    return False
        return True
    
    def checkRow(self,row: int, c: str) -> bool:
        s = self.rowMap.get(row, set())
        if c in s:
            return False
        s.add(c)
        self.rowMap[row] = s
        #print("columnMap = ", self.rowMap)
        return True

    def checkColumn(self,column: int, c: str) -> bool:
        s = self.columnMap.get(column, set())#如果把set()改成()呢!?
        if c in s:
            return False    
        s.add(c)
        self.columnMap[column] = s
        #print("columnMap = ", self.columnMap)
        return True

    def checkGrid(self,row: int, column: int, c: str) -> bool:
        key = f"{row//3}-{column//3}" #設計哈希鍵
        s = self.gridMap.get(key, set())
        if c in s:
            return False
        s.add(c)
        self.gridMap[key] = s
        print("gridMap = ", self.gridMap)
        return True

sodoku = [["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]

print(Solution1122().isValidSudoku(sodoku))