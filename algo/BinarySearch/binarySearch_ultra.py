class binarySearch_ultra:
    def search(self, nums, target):
        left = -1
        right = len(nums)

        while left + 1 != right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if nums[middle] < target:
                left = middle
            elif nums[middle] > target:
                right = middle
            else:
                return middle

        return -1
    
if __name__ == "__main__":
    arr = 1, 3, 4, 5, 6, 7, 9, 10, 11
    target = 2
    test = binarySearch_ultra()
    test.search(arr, target)