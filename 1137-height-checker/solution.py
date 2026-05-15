"""
自己寫的, 官方解答見java, go版本
"""
from typing import List
class Solution1137:
    def heightChecker(self, heights: List[int]) -> int:
        sorted_heights = sorted(heights)
        print(sorted_heights, heights)
        result = 0
        for idx, val in enumerate(heights):
            if val != sorted_heights[idx]:
                result += 1
        print("result = ", result)
        return result

if __name__ == "__main__":
    students = [1, 1, 4, 2, 1, 3]
    print(Solution1137().heightChecker(students))