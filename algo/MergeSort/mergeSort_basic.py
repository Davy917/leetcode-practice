class mergeSort:
    @classmethod
    def Sort(cls, arr):
        if arr is None:
            return
        result = mergeSort().mergeSort(arr, 0, len(arr) - 1)
        for i in range(0, len(result)):
            arr[i] = result[i]
          
    def mergeSort(self, arr, start, end):
        if start >= end:
            return [arr[start]] #注意, 不是arr[start]
        middle = (start + end) // 2
        left: list = self.mergeSort(arr, start, middle)
        right: list = self.mergeSort(arr, middle + 1, end)
        return self.merge(left, right)
      
    #推倒merge, 先想著把[2, 6, 1][3, 5, 4]變成[1, 2, 3, 4, 5 ,6]
    def merge(self, arr1, arr2):
        result = []
        index1 = 0
        index2 = 0
        while(index1 < len(arr1) and index2 < len(arr2)):
            if arr1[index1] <= arr2[index2]:
                result.append(arr1[index1])
                index1 += 1
            else:
                result.append(arr2[index2])
                index2 += 1
        result.extend(arr1[index1:]) 
        result.extend(arr2[index2:])
        return result
if __name__ == "__main__":
    arr = [2, 6, 1, 3, 5, 4]
    mergeSort.Sort(arr)
    print(arr)
  
    """
    different between [arr[start]] and arr[start]

    return [arr[start]]:
    arr = [10, 20, 30]
    start = 0
    result = [arr[start]] # result [10]
    type(result)         # <class 'list'>
    return arr[start]:
    arr = [10, 20, 30]
    start = 0
    result = arr[start]  # result 10
    type(result)         # <class 'int'>
    """
    """
    what is extend method??
    add an list into another list 
    
    For example:
    my_list = ['a', 'b']
    my_tuple = ('c', 'd', 'e')
    my_list.extend(my_tuple)
    print(my_list) # output: ['a', 'b', 'c', 'd', 'e']
    """
