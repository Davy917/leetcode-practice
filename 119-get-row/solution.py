from typing import List
class Solution1122:
    def getRow(self, rowIndex: int) -> List[int]:

        mainList = [1]
        if rowIndex == 0:
            return mainList
        
        for i in range (1, rowIndex + 1):
            subList = [1]
            for j in range(1, i):
                subList.append(mainList[i-1][j-1]+mainList[i-1][j])

            subList.append(1)
            mainList.append(subList)

        return mainList[rowIndex]
                
print(Solution1122().getRow(3))