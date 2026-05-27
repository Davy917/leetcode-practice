"""
自己想的暴力解, 提交會超時, 官方解法看solution2.py
"""
from typing import List
class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], minutes: int) -> int:
        window_sum = sum(customers[:minutes])    
        left = 0
        max_val = 0
        happy = [1 - num for num in grumpy]
        for i in range(minutes-1, len(customers)):
            if(i > minutes - 1):
                window_sum += customers[i]

            total_sum = window_sum
            total_sum += sum(customers[j] * happy[j] for j in range(i+1, len(customers)))
            total_sum += sum(customers[K] * happy[K] for K in range(0, i-(minutes-1)))

            max_val = max(max_val, total_sum)
            window_sum -= customers[left]
            left += 1
        return max_val
if __name__ == "__main__":
    customers = [1,0,1,2,1,1,7,5]
    happy =     [1,0,1,0,1,0,1,0]
    grumpy =    [0,1,0,1,0,1,0,1]
    minutes = 3
    print("Ans = ", Solution().maxSatisfied(customers, grumpy, minutes))


    """
    16, 17行拆解
        1.首先這是生成器表達式 (Generator Expression)。
        2.它看起來像一個列表推導式，但使用了圓括號 () 而不是方括號 []。
        3.作用： 它不會立即創建一個完整的列表並將所有計算結果存儲在記憶體中。
            相反，它會「惰性地」生成值。當 sum() 函數需要一個值時，它就計算一個 customers[j] * happy[j] 的結果並將其「產出」(yield) 給 sum()。
            這對於處理大量數據時可以節省記憶體。
        4.你可以把它想像成一個「生產線」，每次生產一個 (customers[j] * happy[j]) 的值，然後傳給 sum()。

    sum(...):  
        作用： 它會從生成器表達式中一個接一個地獲取計算結果（例如，customers[j] * happy[j] 的值），並將它們累加起來。最終，它返回這些值的總和。
    """