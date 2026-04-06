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
"""
遞歸過程:
mergeSort(arr, 0, 5)  (處理 [2, 6, 1, 3, 5, 4])
    - middle = 2
    - 呼叫 left = mergeSort(arr, 0, 2)  (處理 [2, 6, 1])
        - middle = 1
        - 呼叫 left = mergeSort(arr, 0, 1)  (處理 [2, 6])
            - middle = 0
            - 呼叫 left = mergeSort(arr, 0, 0)  (處理 [2])
            
                - **條件滿足**: start == end (0 == 0)
                - **執行**: `return [arr[0]]`
                - `arr[0]` 是 `2`。
                - **返回 `[2]`**
                
        - 這個 `[2]` 被賦值給 `mergeSort(arr, 0, 1)` 中的 `left` 變數。
            (`left` 現在是 `[2]`)
        - 呼叫 right = mergeSort(arr, 1, 1) (處理 [6])
                - 返回 `[6]`
        - 這個 `[6]` 被賦值給 `mergeSort(arr, 0, 1)` 中的 `right` 變數。
            (`right` 現在是 `[6]`)
        - **執行**: `return self.merge(left, right)` (即 `merge([2], [6])`)
            - `merge` 函數比較 `2` 和 `6`，將 `2` 加入結果列表。
            - **返回 `[2, 6]`**
            
    - 這個 `[2, 6]` 被賦值給 `mergeSort(arr, 0, 2)` 中的 `left` 變數。
    (`left` 現在是 `[2, 6]`)
    - 呼叫 right = mergeSort(arr, 2, 2) (處理 [1])
    - 返回 `[1]`
    - 這個 `[1]` 被賦值給 `mergeSort(arr, 0, 2)` 中的 `right` 變數。
    (`right` 現在是 `[1]`)
    
    - **執行**: `return self.merge(left, right)` (即 `merge([2, 6], [1])`)
    - `merge` 函數比較 `2` 和 `1`，將 `1` 加入結果列表。
    - 接著將 `2` 加入結果列表。
    - **返回 `[1, 2, 6]`**
- 這個 `[1, 2, 6]` 被賦值給 `mergeSort(arr, 0, 5)` 中的 `left` 變數。
    (`left` 現在是 `[1, 2, 6]`)
- 呼叫 right = mergeSort(arr, 3, 5) (處理 [3, 5, 4])
    - ... (這條分支最終會返回 `[3, 4, 5]`)
- 這個 `[3, 4, 5]` 被賦值給 `mergeSort(arr, 0, 5)` 中的 `right` 變數。
    (`right` 現在是 `[3, 4, 5]`)
- **執行**: `return self.merge(left, right)` (即 `merge([1, 2, 6], [3, 4, 5])`)
- `merge` 函數比較 `1` 和 `3`，將 `1` 加入結果列表。
- 接著比較 `2` 和 `3`，將 `2` 加入結果列表。
- ... (繼續合併)
- **返回 `[1, 2, 3, 4, 5, 6]`**
3. 最終結果 `[1, 2, 3, 4, 5, 6]` 被返回給 `Sort.Sort` 方法中的 `result` 變數。
   - `result` 現在是 `[1, 2, 3, 4, 5, 6]`。
"""
