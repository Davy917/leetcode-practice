class InsertSort:
    @classmethod
    def sort(cls, arr: list[int]) -> list[int]:
        for index in range(1, len(arr)):
            visitor = index - 1
            cur_val = arr[index]
            while visitor >= 0 and arr[visitor] > cur_val:
                arr[visitor + 1] = arr[visitor]
                visitor -= 1
            arr[visitor + 1] = cur_val
        return arr

if __name__ == "__main__":
    nums = [5, 8, 6, 3, 9, 1]
    print(InsertSort.sort(nums))