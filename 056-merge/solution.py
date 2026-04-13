from typing import List

class Solution1122:
    def P0056_merge(self, intervals: List[List[int]]) -> List[List[int]]:

        intervals.sort()
        merged = []

        for i in intervals:
            if not merged or i[0] > merged[-1][1]:#如果merged裡面是空的, 或i[0] > merged中最後一個數,代表此列表不重複, 直接塞 
                merged.append(i)				
            else:#如果merged中, 最後一位比當前i[0]大, 則代表有重複, 需做比較
                merged[-1][1] = max(merged[-1][1], i[1])
        return merged								
sol = Solution1122()
list1 = [[1, 3], [2, 6], [8, 10], [15, 18]]
result = sol.P0056_merge(list1)
print(result)

"""
i = 0
intervals.sort[[1, 3], [2, 6], [8, 10], [15, 18]]
merged[[1, 3]]

i = 1
intervals.sort[[1, 3], [2, 6], [8, 10], [15, 18]]
merged[[1, 6]]

i = 2
intervals.sort[[1, 3], [2, 6], [8, 10], [15, 18]]
merged[[1, 6], [8, 10]]

i = 3
intervals.sort[[1, 3], [2, 6], [8, 10], [15, 18]]
merged[[1, 6], [8, 10], [15, 18]]
"""