from typing import List
class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        
        main_list = [[1]]
        #print("layer1", main_list)

        for i in range(1, numRows):

            sublist = [1]
            j = 1
            
            while j < i:
                sublist.append(main_list[i - 1][j - 1] + main_list[i - 1][j])
                j += 1

            sublist.append(1)
            main_list.append(sublist)
            #print(print("layer",i + 1 , main_list))

        return main_list


print(Solution().generate(7))

"""
输入: numRows = 5
输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]


金字塔逐層
1 0
1 1
-------
2 0
2 1

2 1
2 2
-------
3 0
3 1

3 1
3 2

3 2
3 3
"""