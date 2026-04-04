class countingSort:
    @classmethod
    def countingSort_advance(cls, arr):
        if len(arr) == 0 or len(arr) == 1:
            return arr
        max = arr[0]
        min = arr[0]
        for i in range(len(arr)):
            if arr[i] > max:
                max = arr[i]
            elif arr[i] < min:
                min = arr[i]
        total = max - min + 1

        counting = [0] * total
        for element in arr:
            idx = element - 1
            counting[idx] += 1
        print(counting)

        precount = 0
        for i in range(0, total): #why total??
            #注意, 自己想不出來
            precount += counting[i]
            counting[i] = precount - counting[i]

        # To Figure
        result = [0] * len(arr)
        for element in arr:
            position = counting[element - min]
            result[position] = element
            counting[element - min] += 1

        for i in range(len(arr)):
            arr[i] = result[i]

if __name__ == "__main__":
    arr = [8, 7, 1, 2, 8, 6, 8, 2]
    countingSort.countingSort_advance(arr)
    print(arr)