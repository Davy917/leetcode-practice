from typing import Optional
class solution:
    def relativeSortArray(self, arr1, arr2) -> Optional[list]:
        max_val = float('-inf') #哨兵模式最小值
        for val in arr1:
            if val > max_val:
                max_val = val
              
        counting = [0] * (max_val + 1)
        for val in arr1:
            counting[val] += 1
        print(counting)
      
        result = [0] * len(arr1)
        index = 0
        for val in arr2:
            for i in range(0, counting[val]):
                result[index] = val
                index += 1
            counting[val] = 0
        print("counting = ", counting)
        print("result = ", result)
      
        for val in range(0, len(counting)):
            for i in range(0, counting[val]):
                result[index] = val
                index += 1
              
        arr1[0 : len(arr1)] = result[0 : len(result)]
        return result
      
if __name__ == "__main__":
    arr1 = [28,6,22,8,44,17]
    arr2 = [22,28,8,6]
    solution().relativeSortArray(arr1, arr2)
    print(arr1)
