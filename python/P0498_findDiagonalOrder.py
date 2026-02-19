from typing import List
class Solution:
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        m = len(mat)#行
        n = len(mat[0])#列
        diagonal_mat = []

        for i in range(m + n -1):
            if i % 2 == 0:#偶數,下--->上
                if i < m: #為什麼是 i < m, 因為Pivot point發生在i = 3時
                    x = i
                    y = 0

                else:#Pivot point發生時,走進else
                    x = m - 1
                    y = i - m + 1

                while y < n and x >= 0:
                    print(x," ", y)
                    diagonal_mat.append(mat[x][y])
                    x -= 1
                    y += 1

            else:#奇數,上--->下
                if i < n: #為什麼是 i < n, 因為Pivot point發生在i = 4時
                    x = 0
                    y = i

                else:#Pivot point發生時,走進else
                    x = i-n+1
                    y = n-1

                while x < m and y >= 0:
                    print(x," ", y)
                    diagonal_mat.append(mat[x][y])
                    x += 1
                    y -= 1
                    
        return diagonal_mat
    
sol = Solution()
mat = [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12]
]
result = sol.findDiagonalOrder(mat)
print(result)

"""
===================Condition 1
m = 3
n = 3
i = 5

1 2 3
4 5 6
7 8 9

0 0--->i = 0(x = i, y = 0)
0 1<---i = 1(x =0 , y = i)
1 0<---
2 0--->i = 2(x = i, y = 0)
1 1--->
0 2--->
1 2<<===i = 3(x = i-n+1, y = n-1)
2 1<---
2 2--->i = 4(x = i-m+1, y = m - 1)

===================Condition 2
m = 3
n = 4
i = 6

1 2 3 4
4 5 6 5
7 8 9 9

0 0--->i = 0(x = i, y = 0)
0 1<---i = 1(x = 0, y = i)
1 0<---
2 0--->i = 2(x = i, y = 0)
1 1--->
0 2--->
0 3<<===i = 3(x = i-n+1, y = n-1)
1 2<---
2 1<--- 
2 2--->i = 4(x = i-m+1, y = m - 1)
1 3--->
2 3<---


===================Condition 3

m = 4
n = 3
i = 6

1 2 3
4 5 6
7 8 9
1 2 3

0 0--->i = 0(x = i, y = 0)
0 1<---i = 1(x = 0, y = i)
1 0<---
2 0--->i = 2(x = i, y = 0)
1 1--->
0 2--->
1 2<<===i = 3(x = i-n+1, y = n-1)
2 1<---
3 0<---
3 1--->i = 4(x = i-m+1, y = m - 1)
2 2--->
3 2<---
"""