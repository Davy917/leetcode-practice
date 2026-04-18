class solution(object):
    def search(self, nums : list, target : int):
        min_val = float('inf')
        min_val_index = 0
        for index in range(0, len(nums)):
            if min_val > nums[index]:
                min_val = nums[index]
                min_val_index = index
        sorted_nums = sorted(nums)
        left = 0
        right = len(sorted_nums) - 1
        while left <= right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if target > sorted_nums[middle]:
                left = middle + 1
            elif target < sorted_nums[middle]:
                right = middle - 1
            else:
                ans = (middle + min_val_index) % len(nums)
                print("ans = ", ans)
                return ans
        return - 1
if __name__ == "__main__":
    nums = [4,5,6,7,0,1,2]
    target = 6
    sol = solution()
    sol.search(nums, target)
"""
在 Python 中，sort() 方法和 sorted() 函數都用於排序，但它們在使用方式、作用對象和返回結果上存在顯著的區別。
list.sort() 方法
    1. 作用對象： 只能用於 列表 (list)。
    2. 操作方式： 原地 (in-place) 排序。它會直接修改原始列表，而不會創建新的列表。
    3. 返回值： 返回 None。這是一個重要的特點，因為它表示方法直接修改了列表本身。
    4. 用法： 作為列表對象的方法調用。
    
sorted() 函數
    1. 作用對象： 可以用於 任何可迭代對象 (iterable)，包括列表 (list)、元組 (tuple)、字典 (dictionary)、集合 (set)、字串 (string) 等。
    2. 操作方式： 非原地排序。它總是創建並返回一個新的已排序的列表，而不會修改原始的可迭代對象。
    3. 返回值： 返回一個 新的列表，其中包含所有已排序的元素。
    4. 用法： 作為一個內建函數調用。

FAQ:
為何 (middle + min_val_index) % len(nums) 可以映射出 middle 在原始nums中的真實位置

sorted_nums = 0, 1, 2, 4, 5, 6, 7
nums = 4, 5, 6, 7, 0, 1, 2
既然 sorted_nums 是「從最小值開始」的序列，而最小值在 nums 的位置是 min_val_index，那麼：
sorted_nums[0] 對應 nums[min_val_index]
sorted_nums[1] 對應 nums[min_val_index + 1]
...
sorted_nums[middle] 對應 nums[min_val_index + middle]（但可能超出尾端）
所以「對應回 nums」的 index 應該是：
middle + min_val_index

因為 min_val_index + middle 可能會 >= len(nums)，但旋轉陣列是「環狀」接回去的，所以要取模數做 wrap-around：
ans = (middle + min_val_index) % len(nums)
"""
