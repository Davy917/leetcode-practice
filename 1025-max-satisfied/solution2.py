from typing import List
class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], minutes: int) -> int:
        base_satisfied = sum(customers[i] for i in range(len(customers)) if grumpy[i] == 0)
        add_satisfied = sum(customers[i] for i in range(minutes) if grumpy[i] == 1)
        max_add_satisfied = add_satisfied

        left = 1  #這裡的left, right指的就是sliding window 的左右邊界
        for right in range(minutes, len(customers)):
            add_satisfied = sum(customers[i] for i in range(left, right+1) if grumpy[i] == 1)#left是閉區間, right是開區間
            max_add_satisfied = max(max_add_satisfied, add_satisfied)
            print(f"left = {left}, right = {right+1}, max_add_satisfied = {max_add_satisfied}\n")
            left += 1
        return max_add_satisfied + base_satisfied

    def maxSatisfied(self, customers: List[int], grumpy: List[int], minutes: int) -> int:
        base_satisfied = sum(customers[i] for i in range(len(customers)) if grumpy[i] == 0)
        add_satisfied = sum(customers[i] for i in range(minutes) if grumpy[i] == 1)
        max_add_satisfied = add_satisfied

        left = 0  #這裡的left, right指的就是sliding window 的左右邊界
        for right in range(minutes, len(customers)):
            if grumpy[left] == 1:
                add_satisfied -= customers[left]
            if grumpy[right] == 1:
                add_satisfied += customers[right]
            print(f"left = {left}, right = {right}, add_satisfied = {add_satisfied}\n")
            max_add_satisfied = max(max_add_satisfied, add_satisfied)
            left += 1
        return max_add_satisfied + base_satisfied

if __name__ == "__main__":
    customers = [1,0,1,2,1,1,7,5]
    happy =     [1,0,1,0,1,0,1,0]
    grumpy =    [0,1,0,1,0,1,0,1]
    minutes = 3
    print("Ans = ", Solution().maxSatisfied(customers, grumpy, minutes))

"""
add_satisfied 全名是 additional_satisfied 代表額外滿意度
它的算法乍看之下違反直覺, 因為 if grumpy[i] == 1
實際上是意思是, grumpy[i] == 1 原本不應該得分, 但老闆用了技巧, 所以最後還是得分了
"""