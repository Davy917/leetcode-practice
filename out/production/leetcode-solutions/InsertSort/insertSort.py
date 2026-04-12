class insertSort:
    @staticmethod
    def insertSort(arr):
        for index in range(1, len(arr)):
            visitor = index - 1
            cur_val = arr[index]
            while visitor >= 0 and arr[visitor] > cur_val:
                arr[visitor + 1] = arr[visitor]
                visitor -= 1
            arr[visitor + 1] = cur_val

if __name__ == "__main__":
    arr = [5, 8, 6, 3, 9, 1]
    insertSort.insertSort(arr)
    print(arr)